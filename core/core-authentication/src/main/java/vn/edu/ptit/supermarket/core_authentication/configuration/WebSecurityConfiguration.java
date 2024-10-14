package vn.edu.ptit.supermarket.core_authentication.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import vn.edu.ptit.supermarket.core_authentication.constant.Role;
import vn.edu.ptit.supermarket.core_authentication.error_handle.AuthenticationErrorHandle;
import vn.edu.ptit.supermarket.core_authentication.filter.TokenAuthenticationFilter;

@ComponentScan(basePackages = {
    "vn.edu.ptit.supermarket.core_authentication.filter",
    "vn.edu.ptit.supermarket.core_authentication.error_handle",
})
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(jsr250Enabled = true, securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfiguration{

  private final TokenAuthenticationFilter tokenAuthenticationFilter;
  private final AuthenticationErrorHandle authenticationErrorHandle;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.cors(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers("/api/v1/manage/**").hasRole(Role.MANAGER.toString())
            .requestMatchers("/api/v1/cashier/**").hasRole(Role.CASHIER.toString())
            .requestMatchers("/api/v1/customer/**").hasRole(Role.CUSTOMER.toString())
            .requestMatchers("/api/v1/members/**").hasAnyRole(Role.MANAGER.toString(), Role.CASHIER.toString(), Role.CUSTOMER.toString())
            .requestMatchers("/api/v1/public/**").permitAll()
            .requestMatchers("/ws/**").permitAll()
            .requestMatchers("/swagger**", "/swagger-ui**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
            .anyRequest().authenticated()
        )
        .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling(
            httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(
                authenticationErrorHandle))
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .build();
  }
}
