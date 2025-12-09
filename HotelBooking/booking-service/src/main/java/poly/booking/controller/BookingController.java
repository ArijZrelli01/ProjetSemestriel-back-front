package poly.booking.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.booking.entities.Booking;
import poly.booking.feign.RoomRestClient;
import poly.booking.models.Room;
import poly.booking.repository.BookingRepository;
import poly.booking.feign.HotelRestClient;
import poly.booking.feign.ClientRestClient;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class BookingController {
    private final BookingRepository repository;

    private final HotelRestClient hotelClient;
    private final ClientRestClient client;
    private final RoomRestClient roomClient;


@PostMapping
public Booking createBooking(@RequestBody Booking booking) {
    Room room = roomClient.getRoomBynumber(booking.getRoomNumber());
    if (room == null) {
        throw new RuntimeException("Chambre non trouvée");
    }

    if (room.getAvailable().equals("false")) {
        throw new RuntimeException("Cette chambre n'est pas disponible");
    }

    room.setAvailable("false");
    roomClient.updateRoom(room.getId(), room);

    return repository.save(booking);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        Booking booking = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));

         Room room = roomClient.getRoomBynumber(booking.getRoomNumber());

         room.setAvailable("true");
        roomClient.updateRoom(room.getId(), room);

         repository.delete(booking);

        return ResponseEntity.ok().build();
    }



    @GetMapping
    public List<Map<String, Object>> getBookings(
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String date) {

        List<Booking> bookings = repository.findAll();

        if (phone != null && !phone.isEmpty()) {
            bookings = bookings.stream()
                    .filter(b -> phone.equals(b.getPhone()))
                    .collect(Collectors.toList());
        }


        if (date != null && !date.isEmpty()) {
            LocalDate filterDate = LocalDate.parse(date);
            bookings = bookings.stream()
                    .filter(b -> b.getStartDate().isEqual(filterDate))
                    .collect(Collectors.toList());
        }


        return bookings.stream().map(b -> {
            Map<String, Object> map = new HashMap<>();
            map.put("booking", b);
            map.put("customer", client.getClientByPhone(b.getPhone()));
            List<Map<String, Object>> hotels = hotelClient.getHotelById(b.getHotelId());
            map.put("hotel", (hotels != null && !hotels.isEmpty()) ? hotels.get(0) : null);
            map.put("rooms", roomClient.getRoomsByHotel(b.getHotelId()));

            System.out.println("Mapping booking: " + map);

            return map;
        }).collect(Collectors.toList());

    }

}
