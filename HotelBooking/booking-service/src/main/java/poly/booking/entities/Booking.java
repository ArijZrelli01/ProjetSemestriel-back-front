package poly.booking.entities;


import jakarta.persistence.*;
import lombok.*;
import poly.booking.models.Client;
import poly.booking.models.Hotel;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    private Long hotelId;
    private LocalDate  startDate;
    private LocalDate  endDate;

    private String roomNumber;
}
