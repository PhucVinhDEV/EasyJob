package com.example.EasyJob.role.model;


import com.example.EasyJob.common.model.BaseEntity;
import com.example.EasyJob.common.util.JoinTableUtil;
import com.example.EasyJob.permission.model.Permission;
import com.example.EasyJob.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.util.HashSet;
import java.util.Set;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = Role.RoleEntity.TABLE_NAME)
public class Role extends BaseEntity {

    @Column(name = RoleEntity.NAME,nullable = false, unique = true)
    private String roleName;

    @Column(name = RoleEntity.DESCRIPTION,nullable = false)
    private String description;

    @ManyToMany
    @JoinTable(
            name = JoinTableUtil.ROLE_AND_PERMISSION, // Tên bảng trung gian
            joinColumns = @JoinColumn(name = JoinTableUtil.ROLE_ID), // Khóa ngoại đến Role
            inverseJoinColumns = @JoinColumn(name = JoinTableUtil.PERMISSION_ID) // Khóa ngoại đến Permission
    )
    @Column(name = RoleEntity.PERMISSION)
    private Set<Permission> permissions = new HashSet<>();

    @UtilityClass
    static class RoleEntity {
        public static final String TABLE_NAME = "J_ROLE";
        public static final String NAME = "J_NAME";
        public static final String DESCRIPTION = "J_DESCRIPTION";
        public static final String PERMISSION = "J_PERMISSION";
    }
}
