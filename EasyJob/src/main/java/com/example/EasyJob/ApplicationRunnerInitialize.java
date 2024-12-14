package com.example.EasyJob;


import com.example.EasyJob.common.util.PermissionNameUtil;
import com.example.EasyJob.common.util.RoleNameUtil;
import com.example.EasyJob.permission.model.Permission;
import com.example.EasyJob.permission.repository.PermissionRepository;
import com.example.EasyJob.role.model.Role;
import com.example.EasyJob.role.repository.RoleRepository;
import com.example.EasyJob.user.model.User;
import com.example.EasyJob.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Configuration
@AllArgsConstructor
public class ApplicationRunnerInitialize {
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    @Bean
    ApplicationRunner applicationRunner() {
        return args -> {
            // Step 1: Create Permissions
            List<Permission> permissions = Arrays.asList(
                    new Permission(PermissionNameUtil.VIEW_USER, "View User", null),
                    new Permission(PermissionNameUtil.CREATE_USER, "Create User", null),
                    new Permission(PermissionNameUtil.DELETE_USER, "Delete User", null),
                    new Permission(PermissionNameUtil.UPDATE_USER, "Update User", null)
            );

            permissions.forEach(permission -> {
                if (!permissionRepository.existsByName(permission.getName())) {
                    permissionRepository.save(permission);
                }
            });

            // Step 2: Create Roles and Assign Permissions
            List<Role> roles = Arrays.asList(
                    new Role(RoleNameUtil.ROOT, "Super Admin => Root", new HashSet<>(permissions)),
                    new Role(RoleNameUtil.ADMIN, "Admin of department = director",
                            new HashSet<>(permissions.subList(0, 3))), // Admin can VIEW, CREATE, DELETE
                    new Role(RoleNameUtil.MANAGER, "Manager of department",
                            new HashSet<>(permissions.subList(0, 2))), // Manager can VIEW, CREATE
                    new Role(RoleNameUtil.EMPLOYEE, "Employee of department", new HashSet<>()) // Employee has no permissions
            );

            roles.forEach(role -> {
                if (!roleRepository.existsByRoleName(role.getRoleName())) {
                    roleRepository.save(role);
                }
            });

            // Step 3: Create Users and Assign Roles
            // Step 3: Create Users and Assign Roles
            roleRepository.findByRoleName(RoleNameUtil.ROOT).ifPresent(role -> {
                User rootUser = new User( "root@easyjob.com","Trần văn Root", passwordEncoder.encode("root123"), User.Gender.MALE, User.StatusVerified.PENDING, role);
                if (!userRepository.existsByEmail(rootUser.getEmail())) {
                    userRepository.save(rootUser);
                }
            });

            roleRepository.findByRoleName(RoleNameUtil.ADMIN).ifPresent(role -> {
                User adminUser = new User( "admin@easyjob.com","Trần Phúc Admin", passwordEncoder.encode("admin123"),User.Gender.MALE, User.StatusVerified.PENDING, role);
                if (!userRepository.existsByEmail(adminUser.getEmail())) {
                    userRepository.save(adminUser);
                }
            });

            roleRepository.findByRoleName(RoleNameUtil.MANAGER).ifPresent(role -> {
                User managerUser = new User( "manager@easyjob.com","Nguyễn Văn Manager", passwordEncoder.encode("manager123"),User.Gender.MALE, User.StatusVerified.PENDING, role);
                if (!userRepository.existsByEmail(managerUser.getEmail())) {
                    userRepository.save(managerUser);
                }
            });

            roleRepository.findByRoleName(RoleNameUtil.EMPLOYEE).ifPresent(role -> {
                User employeeUser = new User( "employee@easyjob.com","Trịnh trần User", passwordEncoder.encode("employee123"),User.Gender.MALE, User.StatusVerified.PENDING, role);
                if (!userRepository.existsByEmail(employeeUser.getEmail())) {
                    userRepository.save(employeeUser);
                }
            });

        };
    }
}
