package com.example.EasyJob.role.mapper;

import com.example.EasyJob.common.mapper.GenericMapper;
import com.example.EasyJob.role.model.Role;
import com.example.EasyJob.role.model.RoleDTO;
import com.example.EasyJob.role.model.RoleRecord;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper extends GenericMapper<RoleRecord, Role, RoleDTO> {
}
