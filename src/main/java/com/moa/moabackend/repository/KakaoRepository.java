package com.moa.moabackend.repository;

import com.moa.moabackend.entity.user.Kakao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KakaoRepository extends JpaRepository<Kakao, Long> {

    Optional<Kakao> findByUsername(String userName);
}