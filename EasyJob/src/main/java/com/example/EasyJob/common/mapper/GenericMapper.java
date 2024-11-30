package com.example.EasyJob.common.mapper;

import com.example.EasyJob.common.model.BaseEntity;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface GenericMapper<R ,E extends BaseEntity, D> {

    E maptoEntity(R record);

    D maptoDto(E entity);

    @Mapping(target = "id", ignore = true) // Không cho phép ghi đè ID
    @Mapping(target = "version", ignore = true) // Giữ nguyên version
    @Mapping(target = "createdAt", ignore = true) // Giữ nguyên createdAt
    @Mapping(target = "createdBy", ignore = true) // Giữ nguyên createdBy
    @Mapping(target = "lastModifiedAt", expression = "java(java.time.LocalDateTime.now())") // Cập nhật thời gian sửa đổi
    @Mapping(target = "isDeleted", ignore = true) // Trường này không được cập nhật trực tiếp
    void updateEntityFromRecord(R record, @MappingTarget E entity);
}
