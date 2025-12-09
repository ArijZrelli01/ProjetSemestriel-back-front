package poly.booking.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import poly.booking.models.Client;

import java.util.Map;
import java.util.Optional;

@FeignClient(name = "client-service")
public interface ClientRestClient {

    @GetMapping("/clients/search")
    Map<String, Object> getClientByPhone(@RequestParam("phone") String phone);
    @PostMapping("/clients")
    Map<String, Object> createClient(@RequestBody Map<String, String> client);
}
