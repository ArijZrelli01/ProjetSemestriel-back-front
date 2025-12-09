package poly.booking.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import poly.booking.models.Room;

import java.util.List;

@FeignClient(name = "room-service")
public interface RoomRestClient {




    @GetMapping("/rooms/hotel/{hotelId}")
    List<Room> getRoomsByHotel(@PathVariable("hotelId") Long hotelId);
}
