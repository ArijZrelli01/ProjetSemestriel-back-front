package poly.booking.models;

import lombok.Data;

@Data
public class Room {
    private Long id;
    private String number;
    private String type;
    private Long hotelId;
    private String available ;
}
