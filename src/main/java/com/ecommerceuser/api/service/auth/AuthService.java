/**
 * @Date : 2024. 08. 23.
 * @author : jieun(je-pa)
 */
package com.ecommerceuser.api.service.auth;


import com.ecommerceuser.api.controller.ApiResponse;
import com.ecommerceuser.api.controller.auth.dto.request.MailRequest;
import com.ecommerceuser.api.controller.auth.dto.request.SignupRequest;

public interface AuthService {

  ApiResponse<String> sendAuthCode(MailRequest mailRequestDto);

  ApiResponse<String> signup(SignupRequest request);
}
