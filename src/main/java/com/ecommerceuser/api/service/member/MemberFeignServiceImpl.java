/**
 * @Date : 2024. 09. 07.
 * @author : jieun(je-pa)
 */
package com.ecommerceuser.api.service.member;

import com.ecommerceuser.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberFeignServiceImpl implements MemberFeignService {
  private final MemberRepository memberRepository;

  @Override
  @Transactional(readOnly = true)
  public boolean existsMemberId(Long memberId) {
    return memberRepository.existsById(memberId);
  }
}
