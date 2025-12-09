package poly.booking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import poly.booking.entities.Hotel;
import poly.booking.repository.HotelRepository;

@SpringBootApplication
@EnableFeignClients
public class HotelServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelServiceApplication.class, args);
    }


    // http://localhost:8081/swagger-ui/index.html


//    @Bean
//    CommandLineRunner start(HotelRepository hotelRepository) {
//        return args -> {
//            hotelRepository.save(new Hotel(null, "Golden Tulip", "Tunis", 180.0));
//            hotelRepository.save(new Hotel(null, "Laico Hotel", "Sousse", 250.0));
//        };
//    }
}