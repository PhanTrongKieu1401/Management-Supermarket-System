package vn.edu.ptit.supermarket.core_authentication.service;

import vn.edu.ptit.supermarket.core_authentication.entity.Account;

public interface AccountService {

  Account create(String username, String password);

  void save(Account account);

  Account findByMemberId(String memberId);

  Account findByUsername(String username);

  Account findByEmail(String email);

  String findUsernameByMemberId(String memberId);

  void updateLockAccount(String accountId, boolean isLocked);
}
