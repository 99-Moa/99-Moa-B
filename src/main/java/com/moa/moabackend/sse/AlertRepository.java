package com.moa.moabackend.sse;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findAllByReceiver(String userName);
    List<Alert> findAllBySender(String userName);
}
