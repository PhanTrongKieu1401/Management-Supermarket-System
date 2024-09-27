package vn.edu.ptit.supermarket.core_authentication.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import vn.edu.ptit.supermarket.core_authentication.facade.AuthFacadeService;
import vn.edu.ptit.supermarket.core_authentication.facade.impl.AuthFacadeServiceImpl;
import vn.edu.ptit.supermarket.core_authentication.repository.AccountRepository;
import vn.edu.ptit.supermarket.core_authentication.repository.AddressRepository;
import vn.edu.ptit.supermarket.core_authentication.repository.MemberRepository;
import vn.edu.ptit.supermarket.core_authentication.service.AccountService;
import vn.edu.ptit.supermarket.core_authentication.service.AddressService;
import vn.edu.ptit.supermarket.core_authentication.service.AuthTokenService;
import vn.edu.ptit.supermarket.core_authentication.service.MemberService;
import vn.edu.ptit.supermarket.core_authentication.service.OtpService;
import vn.edu.ptit.supermarket.core_authentication.service.impl.AccountServiceImpl;
import vn.edu.ptit.supermarket.core_authentication.service.impl.AddressServiceImpl;
import vn.edu.ptit.supermarket.core_authentication.service.impl.MemberServiceImpl;
import vn.edu.ptit.supermarket.core_email.configuration.EnableCoreEmail;
import vn.edu.ptit.supermarket.core_email.healper.EmailHelper;
import vn.edu.ptit.supermarket.core_redis.configuration.EnableCoreRedis;
import vn.edu.ptit.supermarket.core_redis.service.RedisService;

@Configuration
@EntityScan(basePackages = {"vn.edu.ptit.supermarket.core_authentication.entity"})
@EnableJpaRepositories(basePackages = {"vn.edu.ptit.supermarket.core_authentication.repository"})
@EnableCoreEmail
@EnableCoreRedis
public class CoreAuthenticationConfiguration {

  @Bean
  public AuthFacadeService authFacadeService(
      AccountService accountService,
      MemberService memberService,
      AddressService addressService,
      AuthTokenService authTokenService,
      OtpService otpService,
      RedisService redisService,
      EmailHelper emailHelper) {
    return new AuthFacadeServiceImpl(accountService, memberService, addressService, authTokenService, otpService,
        redisService, emailHelper);
  }

  @Bean
  public AccountService accountService(
      AccountRepository accountRepository) {
    return new AccountServiceImpl(accountRepository);
  }

  @Bean
  public MemberService memberService(
      MemberRepository memberRepository) {
    return new MemberServiceImpl(memberRepository);
  }

  @Bean
  public AddressService addressService(
      AddressRepository addressRepository) {
    return new AddressServiceImpl(addressRepository);
  }
}
