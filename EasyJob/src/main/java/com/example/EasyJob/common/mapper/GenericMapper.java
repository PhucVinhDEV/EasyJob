package com.example.EasyJob.common.mapper;

import com.example.EasyJob.common.model.BaseEntity;

import org.mapstruct.MappingTarget;

public interface GenericMapper<R ,E extends BaseEntity, D> {

    E maptoEntity(R record);

    D maptoDto(E entity);

//    // Cập nhật Entity từ Record
//    void updateEntityFromRecord(R record, @MappingTarget E entity);
}
