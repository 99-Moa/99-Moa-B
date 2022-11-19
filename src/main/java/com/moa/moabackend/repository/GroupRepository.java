package com.moa.moabackend.repository;

import com.moa.moabackend.entity.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
