package t1.java.demo.registerAndTransaction.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import t1.java.demo.registerAndTransaction.dto.ClientDto;

import java.util.UUID;
@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaClientProducer {
    private final KafkaTemplate template;



    //TODO:
    public void send(Long id) {
        try {
            template.sendDefault(UUID.randomUUID().toString(), new ClientDto()).get();
            template.flush();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public void sendTo(String topic, Object o) {
        try {
            template.send(topic, o).get();
            template.flush();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }
}
