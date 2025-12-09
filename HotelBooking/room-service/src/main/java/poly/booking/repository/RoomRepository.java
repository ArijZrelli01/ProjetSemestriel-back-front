package poly.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.booking.entities.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHotelId(Long hotelId);
    Optional<Room> findByNumber(String number);



}

