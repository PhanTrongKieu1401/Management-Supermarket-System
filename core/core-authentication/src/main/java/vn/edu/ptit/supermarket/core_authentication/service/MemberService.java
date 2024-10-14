package vn.edu.ptit.supermarket.core_authentication.service;

import vn.edu.ptit.supermarket.core_authentication.entity.Account;
import vn.edu.ptit.supermarket.core_authentication.entity.Member;
import vn.edu.ptit.supermarket.core_authentication.model.request.RegisterRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.UpdateMemberRequest;
import vn.edu.ptit.supermarket.core_authentication.model.response.InfoMemberHeaderResponse;
import vn.edu.ptit.supermarket.core_authentication.model.response.InfoMemberResponse;

public interface MemberService {

  Member create(RegisterRequest registerRequest, Account account, String addressId);
  Member update(String memberId, UpdateMemberRequest updateMemberRequest);
  Member findById(String id);
  Member findByAccountId(String accountId);
  boolean existsByEmail(String email);
  InfoMemberHeaderResponse getFullName(String memberId);
  InfoMemberResponse getInfo(String memberId);

}
