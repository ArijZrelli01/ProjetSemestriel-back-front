package poly.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import poly.booking.entities.Client;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {


        Optional<Client> findByPhone(String phone);


}
