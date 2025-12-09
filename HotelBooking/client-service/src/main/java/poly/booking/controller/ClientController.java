package poly.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import poly.booking.entities.Client;
import poly.booking.repository.ClientRepository;
import java.util.Optional;

import java.util.List;


@RestController
@RequestMapping("/clients")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")

public class ClientController {

    private final ClientRepository repository;

    @GetMapping
    public List<Client> getAllClients() {
        return repository.findAll();
    }

    @GetMapping("/search")
    public Optional<Client> findByPhone(@RequestParam String phone) {
        return repository.findByPhone(phone);
    }
    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return repository.save(client);
    }

}
