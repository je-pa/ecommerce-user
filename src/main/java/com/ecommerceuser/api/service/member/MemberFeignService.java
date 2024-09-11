/**
 * @Date : 2024. 09. 07.
 * @author : jieun(je-pa)
 */
package com.ecommerceuser.api.service.member;

public interface MemberFeignService {

  boolean existsMemberId(Long memberId);
}
