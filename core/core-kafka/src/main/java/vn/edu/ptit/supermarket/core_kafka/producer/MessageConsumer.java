package vn.edu.ptit.supermarket.core_kafka.producer;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MessageConsumer {

  @KafkaListener(topics = "supermarket", groupId = "supermarket")
  public void listen(String message) {
    System.out.println("Received message: " + message);
  }
}
