package vn.edu.ptit.supermarket.core_authentication.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

public class CorsClientConfiguration {

//  @Bean
//  public CorsFilter corsFilter(){
//    CorsConfiguration corsConfiguration = new CorsConfiguration();
//
//    corsConfiguration.addAllowedOriginPattern("http://*");
//    corsConfiguration.addAllowedOriginPattern("https://*");
//    corsConfiguration.addAllowedOriginPattern("ws://*");
//    corsConfiguration.addAllowedOriginPattern("wss://*");
//
//    corsConfiguration.addAllowedMethod("*");
//    corsConfiguration.addAllowedHeader("*");
//
//    UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//    urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
//
//    return new CorsFilter(urlBasedCorsConfigurationSource);
//  }
}
