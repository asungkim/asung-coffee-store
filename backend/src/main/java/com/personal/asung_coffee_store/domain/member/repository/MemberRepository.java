package com.personal.asung_coffee_store.domain.member.repository;

import com.personal.asung_coffee_store.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
