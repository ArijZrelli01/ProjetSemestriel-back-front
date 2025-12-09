package poly.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import poly.booking.entities.Room;
import poly.booking.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RoomController {

    private final RoomRepository roomRepository;



    @GetMapping
    public List<Room> getRooms(@RequestParam(required = false) Long hotelId) {
        if (hotelId != null) {
            return roomRepository.findByHotelId(hotelId);
        }
        return roomRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Room> getRoom(@PathVariable Long id) {
        return roomRepository.findById(id);
    }


    @GetMapping("/number/{number}")
    public Optional<Room> getRoomByNumber(@PathVariable String number) {
        return roomRepository.findByNumber(number);
    }


    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomRepository.save(room);
    }

    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomRepository.save(room);
    }

    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomRepository.deleteById(id);
    }

    @GetMapping("/hotel/{hotelId}")
    public List<Room> getRoomsByHotel(@PathVariable Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }
}
