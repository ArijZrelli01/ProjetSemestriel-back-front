package poly.booking.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import poly.booking.models.Hotel;

import java.util.List;
import java.util.Map;

@FeignClient(name = "hotel-service")
public interface HotelRestClient {

    @GetMapping("/hotels")
    List<Map<String, Object>> getHotelById(@RequestParam("id") Long id);


}
