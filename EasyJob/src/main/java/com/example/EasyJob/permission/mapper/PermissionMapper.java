package com.example.EasyJob.permission.mapper;

import com.example.EasyJob.common.mapper.GenericMapper;
import com.example.EasyJob.permission.model.Permission;
import com.example.EasyJob.permission.model.PermissionDTO;
import com.example.EasyJob.permission.model.PermissionRecord;
import org.mapstruct.Mapper;

import java.util.UUID;
@Mapper(componentModel = "spring")
public interface PermissionMapper extends GenericMapper<PermissionRecord, Permission, PermissionDTO> {
}
