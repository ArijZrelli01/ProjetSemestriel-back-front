package poly.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RoomServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoomServiceApplication.class, args);
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