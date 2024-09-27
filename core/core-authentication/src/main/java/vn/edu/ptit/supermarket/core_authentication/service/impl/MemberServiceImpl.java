package vn.edu.ptit.supermarket.core_authentication.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.ptit.supermarket.core_authentication.entity.Member;
import vn.edu.ptit.supermarket.core_authentication.exception.AccountExistedException;
import vn.edu.ptit.supermarket.core_authentication.exception.AccountNotFoundException;
import vn.edu.ptit.supermarket.core_authentication.exception.EmailExistedException;
import vn.edu.ptit.supermarket.core_authentication.model.request.RegisterRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.UpdateMemberRequest;
import vn.edu.ptit.supermarket.core_authentication.repository.MemberRepository;
import vn.edu.ptit.supermarket.core_authentication.service.MemberService;

@Slf4j
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  private final MemberRepository memberRepository;

  @Override
  @Transactional
  public Member create(RegisterRequest registerRequest, String accountId, String addressId) {
    log.info("(create)email: {}, accountId: {}, addressId: {}", registerRequest.getEmail(), accountId, addressId);

    if(memberRepository.existsByEmail(registerRequest.getEmail())){
      log.error("(create)email: {} already exists", registerRequest.getEmail());
      throw new EmailExistedException(registerRequest.getEmail());
    }

    if(memberRepository.existsByAccountId(accountId)) {
      log.error("(create)accountId: {} already exists", accountId);
      throw new AccountExistedException(accountId);
    }
    return memberRepository.save(Member.from(registerRequest, accountId, addressId));
  }

  @Override
  @Transactional
  public Member update(String memberId, UpdateMemberRequest updateMemberRequest) {
    log.info("(create)member: {}", updateMemberRequest);
    return memberRepository.save(Member.from(memberId, updateMemberRequest));
  }

  @Override
  public Member findById(String id) {
    log.info("(findById)id: {}", id);
    return memberRepository.findById(id).orElseThrow(() -> {
      log.error("(findById)id: {} not found", id);
      throw new AccountNotFoundException(id);
    });
  }

  @Override
  public Member findByAccountId(String accountId){
    log.info("(findByAccountId)accountId: {}", accountId);
    return memberRepository.findByAccountId(accountId).orElseThrow(() -> {
      log.error("(findByAccountId)accountId: {} not found", accountId);
      throw new AccountNotFoundException(accountId);
    });
  }

  @Override
  public boolean existsByEmail(String email) {
    log.info("(existsByEmail)email: {}", email);
    return memberRepository.existsByEmail(email);
  }
}
