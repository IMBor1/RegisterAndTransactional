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
    public void registerClients(List<Client> clients) {
        repository.saveAll(clients)
                .stream()
                .map(Client::getId)
                .forEach(kafkaClientProducer::send);

    }
}
