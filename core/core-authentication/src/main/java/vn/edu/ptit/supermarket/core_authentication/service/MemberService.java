package vn.edu.ptit.supermarket.core_authentication.service;

import vn.edu.ptit.supermarket.core_authentication.entity.Member;
import vn.edu.ptit.supermarket.core_authentication.model.request.RegisterRequest;
import vn.edu.ptit.supermarket.core_authentication.model.request.UpdateMemberRequest;

public interface MemberService {

  Member create(RegisterRequest registerRequest, String accountId, String addressId);
  Member update(String memberId, UpdateMemberRequest updateMemberRequest);

  Member findById(String id);
  Member findByAccountId(String accountId);

  boolean existsByEmail(String email);
}
