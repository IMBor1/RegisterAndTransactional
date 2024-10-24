package t1.java.demo.registerAndTransaction.service.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import t1.java.demo.registerAndTransaction.kafka.KafkaClientProducer;
import t1.java.demo.registerAndTransaction.model.Client;
import t1.java.demo.registerAndTransaction.repository.ClientRepository;
import t1.java.demo.registerAndTransaction.service.ClientService;

import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class ClientServiceImpl implements ClientService {
    private final ClientRepository repository;
    private final KafkaClientProducer kafkaClientProducer;

    @Override
    public Client registerClients(Client client) {
        kafkaClientProducer.send(client.getId());
        return repository.save(client);

    }
}
