package poly.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import poly.booking.entities.Hotel;
import poly.booking.feign.RoomRestClient;
import poly.booking.models.Room;
import poly.booking.repository.HotelRepository;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class HotelController {
    private final HotelRepository repository;
    private final RoomRestClient roomRestClient;



    @GetMapping
    public List<Hotel> getAllHotels() {
        return repository.findAll();
    }

    @PostMapping
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return repository.save(hotel);
    }

    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hotel not found with id " + id));
    }

     @GetMapping("/rooms/{hotelId}")
    public List<Room> getRoomsByHotel(@PathVariable Long hotelId) {
        return roomRestClient.getRoomsByHotel(hotelId);
    }
}
