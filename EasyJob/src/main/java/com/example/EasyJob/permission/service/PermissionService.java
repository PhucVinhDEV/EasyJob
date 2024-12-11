package com.example.EasyJob.permission.service;

import com.example.EasyJob.common.mapper.GenericMapper;
import com.example.EasyJob.common.service.GenericService;
import com.example.EasyJob.permission.mapper.PermissionMapper;
import com.example.EasyJob.permission.model.Permission;
import com.example.EasyJob.permission.model.PermissionDTO;
import com.example.EasyJob.permission.model.PermissionRecord;
import com.example.EasyJob.permission.repository.PermissiomRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PermissionService extends GenericService<Permission, PermissionRecord, PermissionDTO, UUID> {
}

@AllArgsConstructor
class PermissionServiceImpl implements PermissionService {
    private final PermissiomRepository repository;
    private final PermissionMapper mapper;

    @Override
    public JpaRepository<Permission, UUID> getRepository() {
        return repository;
    }

    @Override
    public GenericMapper<PermissionRecord, Permission, PermissionDTO> getMapper() {
        return mapper;
    }
}