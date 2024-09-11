/**
 * @Date : 2024. 09. 07.
 * @author : jieun(je-pa)
 */
package com.ecommerceuser.api.controller.member;

import com.ecommerceuser.api.service.member.MemberFeignService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/v0/members")
@RequiredArgsConstructor
public class MemberFeignController {

  private final MemberFeignService memberFeignService;

  /**
   * db에 memberId의 존재여부를 확인합니다.
   * @param memberId 확인할 memberId
   * @return 있으면 true 없으면 false
   */
  @GetMapping("/{memberId}/exists")
  public boolean exists(@PathVariable("memberId") Long memberId) {
    return memberFeignService.existsMemberId(memberId);
  }

}
