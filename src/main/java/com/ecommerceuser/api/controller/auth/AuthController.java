/**
 * @Date : 2024. 08. 23.
 * @author : jieun(je-pa)
 */
package com.ecommerceuser.api.controller.auth;

import com.ecommerceuser.api.controller.ApiResponse;
import com.ecommerceuser.api.controller.auth.dto.request.MailRequest;
import com.ecommerceuser.api.controller.auth.dto.request.SignupRequest;
import com.ecommerceuser.api.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/send-email-auth-code")
  public ResponseEntity<ApiResponse<String>> sendAuthCode(
      @RequestBody @Valid MailRequest mailRequestDto) {
    return ResponseEntity.ok(authService.sendAuthCode(mailRequestDto));
  }

  @PostMapping("/signup")
  public ResponseEntity<ApiResponse<String>> signup(
      @RequestBody @Valid SignupRequest request
  ){
    return ResponseEntity.ok(authService.signup(request));
  }

}
