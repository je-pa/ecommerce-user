/**
 * @Date : 2024. 08. 23.
 * @author : jieun(je-pa)
 */
package com.ecommerceuser.api.service.auth;


import com.ecommerceuser.api.controller.ApiResponse;
import com.ecommerceuser.api.controller.auth.dto.request.MailRequest;
import com.ecommerceuser.api.controller.auth.dto.request.SignupRequest;
import com.ecommerceuser.domain.member.entity.Member;
import com.ecommerceuser.domain.member.repository.MemberRepository;
import com.ecommerceuser.domain.member.type.Role;
import com.ecommerceuser.domain.repository.EmailAuthCodeRepository;
import com.ecommerceuser.global.exception.CustomException;
import com.ecommerceuser.global.exception.ExceptionCode;
import com.ecommerceuser.global.mail.event.SendAuthCodeEmailEvent;
import com.ecommerceuser.global.util.MyEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final MyEncoder myEncoder;
  private final PasswordEncoder passwordEncoder;
  private final ApplicationEventPublisher eventPublisher;
  private final EmailAuthCodeRepository emailAuthCodeRepository;
  private final MemberRepository memberRepository;

  @Override
  public ApiResponse<String> sendAuthCode(MailRequest mailRequestDto) {
    SendAuthCodeEmailEvent event = SendAuthCodeEmailEvent.from(mailRequestDto.email());

    String encryptedEmail = myEncoder.encrypt(event.toEmail());
    emailAuthCodeRepository.setWithDurationByKey(encryptedEmail, event.authCode());

    if (memberRepository.existsByEmail(encryptedEmail)) {
      throw CustomException.from(ExceptionCode.EMAIL_ALREADY_EXISTS);
    }

    if (!(boolean) emailAuthCodeRepository.hasKey(encryptedEmail)) {
      throw CustomException.from(ExceptionCode.EMAIL_AUTH_CODE_SAVE_FAILED);
    }

    eventPublisher.publishEvent(event);
    String message = "인증코드를 이메일로 전송하였습니다. 인증코드의 유효시간은 1분입니다.";
    return ApiResponse.ok(message);
  }

  @Override
  public ApiResponse<String> signup(SignupRequest request) {
    String encryptedEmail = myEncoder.encrypt(request.email());

    if (!request.password().equals(request.confirmPassword())) {
      throw CustomException.from(ExceptionCode.PASSWORD_MISMATCH);
    }

    if (memberRepository.existsByEmail(encryptedEmail)) {
      throw CustomException.from(ExceptionCode.EMAIL_ALREADY_EXISTS);
    }

    if (!(boolean) emailAuthCodeRepository.hasKey(encryptedEmail)) {
      throw CustomException.from(ExceptionCode.CODE_EXPIRED_OR_INVALID);
    }

    if (!emailAuthCodeRepository.getByKey(encryptedEmail).equals(request.authCode())) {
      throw CustomException.from(ExceptionCode.VERIFICATION_CODE_MISMATCH);
    }

    emailAuthCodeRepository.deleteByKey(encryptedEmail);

    Member member = Member.builder()
        .address(myEncoder.encrypt(request.address()))
        .email(myEncoder.encrypt(request.email()))
        .name(myEncoder.encrypt(request.name()))
        .password(passwordEncoder.encode(request.password()))
        .password("Password123!")
        .tellNumber(myEncoder.encrypt(request.tellNumber()))
        .role(Role.GENERAL)
        .build();

    memberRepository.save(member);

    return ApiResponse.ok("회원가입이 완료되었습니다.");
  }
}
