package com.ecommerceuser.global.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

  // BAD_REQUEST:400:잘못된요청
  PASSWORD_MISMATCH(BAD_REQUEST, "두 비밀번호가 일치하지 않습니다."),
  VERIFICATION_CODE_MISMATCH(BAD_REQUEST, "인증코드가 일치하지 않습니다."),
  CODE_EXPIRED_OR_INVALID(BAD_REQUEST, "인증 코드가 만료되었거나 존재하지 않습니다."),

  // CONFLICT:409:충돌
  EMAIL_ALREADY_EXISTS(CONFLICT, "중복된 이메일 입니다."),

  // INTERNAL_SERVER_ERROR:500:서버 문제 발생
  EMAIL_SENDING_FAILED(INTERNAL_SERVER_ERROR, "이메일 전송에 실패했습니다."),
  EMAIL_AUTH_CODE_SAVE_FAILED(INTERNAL_SERVER_ERROR, "이메일 인증 코드 저장에 실패했습니다."),
  NO_SUCH_ALGORITHM(INTERNAL_SERVER_ERROR, "지정된 알고리즘을 찾을 수 없습니다."),
  NO_SUCH_PADDING(INTERNAL_SERVER_ERROR, "지정된 패딩 방식을 찾을 수 없습니다."),
  INVALID_KEY(INTERNAL_SERVER_ERROR, "잘못된 키입니다."),
  ILLEGAL_BLOCK_SIZE(INTERNAL_SERVER_ERROR, "잘못된 블록 크기입니다."),
  BAD_PADDING(INTERNAL_SERVER_ERROR, "잘못된 패딩입니다."),
  ;
  private final HttpStatus status;
  private final String message;

}