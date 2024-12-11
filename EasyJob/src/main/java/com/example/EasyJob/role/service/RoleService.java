package com.example.EasyJob.role.service;

import com.example.EasyJob.common.mapper.GenericMapper;
import com.example.EasyJob.common.service.GenericService;
import com.example.EasyJob.role.mapper.RoleMapper;
import com.example.EasyJob.role.model.Role;
import com.example.EasyJob.role.model.RoleDTO;
import com.example.EasyJob.role.model.RoleRecord;
import com.example.EasyJob.role.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleService extends GenericService<Role , RoleRecord, RoleDTO, UUID> {
}

@AllArgsConstructor
class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public JpaRepository<Role, UUID> getRepository() {
        return roleRepository;
    }

    @Override
    public GenericMapper<RoleRecord, Role, RoleDTO> getMapper() {
        return roleMapper;
    }
}