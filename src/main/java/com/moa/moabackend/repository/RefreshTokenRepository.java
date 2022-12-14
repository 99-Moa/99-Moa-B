package com.moa.moabackend.repository;

import com.moa.moabackend.entity.user.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByAccountUserId(String userId);
    Optional<RefreshToken> deleteByAccountUserId(String userId);
}