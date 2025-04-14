package com.learningcrew.linkup.linker.command.domain.repository;

import com.learningcrew.linkup.linker.command.domain.aggregate.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    boolean existsByNickname(@Size(max = 30, message = "닉네임은 최대 30자까지 가능합니다.") @NotBlank(message = "닉네임은 필수입니다.") String nickname);
}
