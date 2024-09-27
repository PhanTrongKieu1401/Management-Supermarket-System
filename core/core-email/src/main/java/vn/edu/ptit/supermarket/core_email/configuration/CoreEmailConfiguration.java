package vn.edu.ptit.supermarket.core_email.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.spring6.SpringTemplateEngine;
import vn.edu.ptit.supermarket.core_email.healper.EmailHelper;
import vn.edu.ptit.supermarket.core_email.healper.impl.EmailHelperImpl;

@Configuration
public class CoreEmailConfiguration {

  @Bean
  public EmailHelper emailService(JavaMailSender emailSender, SpringTemplateEngine templateEngine) {
    return new EmailHelperImpl(emailSender, templateEngine);
  }
}
