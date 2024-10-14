package vn.edu.ptit.supermarket.core_web_socket.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

  @Value("${ws.applicationPrefix}")
  private String applicationPrefix;

  @Value("${ws.topicPrefix}")
  private String topicPrefix;

  @Value("${ws.userDestinationPrefix}")
  private String userDestinationPrefix;

  @Value("${ws.registerEndpoint}")
  private String registerEndpoint; //end point mà client connect

  @Bean
  WebMvcConfigurer corsConfig() {
    return new WebMvcConfigurer() {

      @Override
      public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/ws/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*");
      }
    };
  }

  @Override
  public void configureMessageBroker (MessageBrokerRegistry registry) {
    registry.enableSimpleBroker(topicPrefix, userDestinationPrefix);//các đưòng đi mà server sẽ gửi tin nhắn cho client
    registry.setApplicationDestinationPrefixes(applicationPrefix);//tiền tố của message mapping mà server nhận ti
    registry.setUserDestinationPrefix(userDestinationPrefix);
  }

  @Override
  public void registerStompEndpoints (StompEndpointRegistry registry) {
    registry.addEndpoint(registerEndpoint).setAllowedOrigins("*");
    registry.addEndpoint(registerEndpoint).setAllowedOrigins("*").withSockJS();
  }
}
