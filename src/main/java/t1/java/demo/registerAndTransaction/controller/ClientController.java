package t1.java.demo.registerAndTransaction.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t1.java.demo.registerAndTransaction.model.Client;
import t1.java.demo.registerAndTransaction.service.ClientService;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {


    private final ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.registerClients(client));
    }
}

