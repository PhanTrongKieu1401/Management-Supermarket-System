package vn.edu.ptit.supermarket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import vn.edu.ptit.supermarket.core_kafka.producer.MessageProducer;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class Main implements CommandLineRunner {

  public void run(String... args) throws Exception {
    log.debug("---------------------Application started---------------------");
    System.out.println("Author: Phan Trong Kieu ");
    System.out.println("Date: 15/08/2024");
    System.out.println("Project name: Supermarket management system");
    log.debug("---------------------Application started---------------------");
  }

  public static void main(String[] args) {
    ApplicationContext applicationContext = SpringApplication.run(Main.class, args);
    MessageProducer producer = applicationContext.getBean(MessageProducer.class);
    producer.sendMessage("supermarket", "hello world");
  }
}
