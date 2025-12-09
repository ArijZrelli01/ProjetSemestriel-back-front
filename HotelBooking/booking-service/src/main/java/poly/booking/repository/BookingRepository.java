package poly.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.booking.entities.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {



}

