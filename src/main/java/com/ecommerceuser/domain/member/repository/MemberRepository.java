package com.ecommerceuser.domain.member.repository;

import com.ecommerceuser.domain.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByEmail(String username);

  boolean existsByEmail(String encryptedEmail);
}
