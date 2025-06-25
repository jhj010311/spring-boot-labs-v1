package com.captainyun7.ch502jwtbasedplainlogin.repository;

import com.captainyun7.ch502jwtbasedplainlogin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // TODO
    // (1) 쿼리 자동 생성 메서드
    // (2) JPQL
    // (3) QueryDSL
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
}