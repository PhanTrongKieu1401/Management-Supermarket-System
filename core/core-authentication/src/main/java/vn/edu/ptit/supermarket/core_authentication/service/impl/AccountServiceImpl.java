package vn.edu.ptit.supermarket.core_authentication.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.ptit.supermarket.core_authentication.entity.Account;
import vn.edu.ptit.supermarket.core_authentication.exception.UsernameAlreadyExistedException;
import vn.edu.ptit.supermarket.core_authentication.repository.AccountRepository;
import vn.edu.ptit.supermarket.core_authentication.service.AccountService;

@Slf4j
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;

  @Override
  @Transactional
  public Account create(String username, String password) {
    log.info("(create)username: {}, password: {}", username, password);
    if(accountRepository.existsByUsername(username)) {
      log.error("(create)username: {} already exists", username);
      throw new UsernameAlreadyExistedException(username);
    }
    return accountRepository.save(Account.of(username, password));
  }

  @Override
  @Transactional
  public void save(Account account) {
    log.info("(save)account: {}", account);
    accountRepository.save(account);
  }

  @Override
  public Account findByMemberId(String memberId) {
    log.info("(findByMemberId)memberId: {}", memberId);
    return accountRepository.findByMemberId(memberId);
  }

  @Override
  public Account findByUsername(String username) {
    log.info("(findByUsername)username: {}", username);
    return accountRepository.findByUsername(username);
  }

  @Override
  public Account findByEmail(String email) {
    log.info("(findByEmail)email: {}", email);
    return accountRepository.findByEmail(email);
  }

  @Override
  public String findUsernameByMemberId(String memberId) {
    log.info("(findUsernameByMemberId)memberId: {}", memberId);
    return accountRepository.findUsernameByMemberId(memberId);
  }

  @Override
  public void updateLockAccount(String accountId, boolean isLocked) {
    log.info("(updateLockAccount)accountId: {}, isLocked: {}", accountId, isLocked);
    accountRepository.updateLockAccount(accountId, isLocked);
  }
}
