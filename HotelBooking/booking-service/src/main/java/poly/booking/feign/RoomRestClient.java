package poly.booking.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import poly.booking.models.Room;

import java.util.List;
import java.util.Map;

@FeignClient(name = "room-service")

public interface RoomRestClient {

    @GetMapping("/rooms/{id}")
    Room getRoomById(@PathVariable("id") Long id);


    @GetMapping("/rooms/number/{number}")
    Room getRoomBynumber(@PathVariable("number") String number);

    @GetMapping("/rooms/hotel/{hotelId}")
    List<Room> getRoomsByHotel(@PathVariable("hotelId") Long hotelId);


    @PutMapping("/rooms/{id}")
    Room updateRoom(@PathVariable("id") Long id, @RequestBody Room room);
}
