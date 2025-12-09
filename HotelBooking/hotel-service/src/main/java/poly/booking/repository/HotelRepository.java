package poly.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import poly.booking.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {


}

