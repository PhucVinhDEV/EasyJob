package com.example.EasyJob.permission.model;

import com.example.EasyJob.common.model.BaseEntity;
import com.example.EasyJob.role.model.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

@Entity
@SuperBuilder
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@Table(name = Permission.PermissionEntity.TABLE_NAME)
public class Permission extends BaseEntity {

    @Column(name = PermissionEntity.NAME,nullable = false, unique = true)
    private String name;

    @Column(name = PermissionEntity.DESCRIPTION,nullable = false)
    private String description;

    @ManyToMany(mappedBy = "permissions") // Tên trường trong Role
    private Set<Role> roles = new HashSet<>();

    @UtilityClass
    static class PermissionEntity {
        public static final String TABLE_NAME = "J_PERMISSION";
        public static final String NAME = "J_NAME";
        public static final String DESCRIPTION = "J_DESCRIPTION";
    }
}
