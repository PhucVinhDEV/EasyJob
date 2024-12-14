package com.example.EasyJob.permission.repository;

import com.example.EasyJob.permission.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {

    boolean existsByName(String name);
}
