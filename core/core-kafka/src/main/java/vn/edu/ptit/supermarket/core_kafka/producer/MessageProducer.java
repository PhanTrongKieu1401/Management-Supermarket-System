package vn.edu.ptit.supermarket.core_kafka.producer;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class MessageProducer {

  private final KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(String topic, String message) {
    log.info("Producing message {} to topic {}", message, topic);
    kafkaTemplate.send(topic, message);
  }
}
