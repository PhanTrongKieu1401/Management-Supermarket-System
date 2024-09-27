package vn.edu.ptit.supermarket.core_authentication.controller;

import static vn.edu.ptit.supermarket.core_authentication.util.SecurityUtil.getMemberId;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.edu.ptit.supermarket.core_authentication.model.request.UpdateMemberRequest;
import vn.edu.ptit.supermarket.core_authentication.model.response.BaseResponse;
import vn.edu.ptit.supermarket.core_authentication.service.MemberService;

@Slf4j
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@Tag(name = "Member", description = "Member API")
public class MemberController {

  private final MemberService memberService;

  @PutMapping("/update")
  @Operation(summary = "Update member", description = "Update member API")
  public BaseResponse<?> update(
      @Valid @RequestBody UpdateMemberRequest updateMemberRequest) {
    log.info("(update)updateMemberRequest: {}", updateMemberRequest);
    return BaseResponse.of(HttpStatus.OK.value(), LocalDateTime.now().toString(),
        memberService.update(getMemberId(), updateMemberRequest));
  }
}
