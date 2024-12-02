package com.example.EasyJob.common.service;

import com.example.EasyJob.common.mapper.GenericMapper;
import com.example.EasyJob.common.model.BaseEntity;
import com.example.EasyJob.common.model.PageReponsese;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * T : Class Entity Extend  từ BaseEntity
 * U : Id của T Entity
 * R : Record cua T
 * D : DTO reponsese API
 * @return
 */
public interface GenericService<T extends BaseEntity, R, D, U>{




    JpaRepository<T, U> getRepository();

    GenericMapper<R, T, D> getMapper();

    default D findById(U id){
        T entity = getRepository().findById(id)
                .orElseThrow( () -> new RuntimeException("No entity found with id: " + id) );
        return getMapper().maptoDto(entity);
    }

    default List<D> findAll(){
        List<T> entityList = getRepository().findAll();
        return entityList.stream().map( getMapper()::maptoDto).toList();
    }

    default PageReponsese<D> findAllByPage(Pageable pageable){
        Page<T> entityList = getRepository().findAll(pageable);
        return PageReponsese.<D>builder()
                .totalElements(entityList.getTotalElements())
                .totalPages(entityList.getTotalPages())
                .first(entityList.isFirst())
                .last(entityList.isLast())
                .empty(entityList.isEmpty())
                .number(entityList.getNumber())
                .numberOfElements(entityList.getNumberOfElements())
                .size(entityList.getSize())
                .data(
                        entityList.stream().map( getMapper()::maptoDto).toList()
                )
                .build();
    }

    default D save(R record){
        T entity = getRepository().save(getMapper().maptoEntity(record));
        return getMapper().maptoDto(entity);
    }

    default D update(U id, R record){
        // Tìm entity trong database
        T existingEntity = getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
        // Map dữ liệu từ record vào entity cũ
        getMapper().updateEntityFromRecord(record, existingEntity);
        // Lưu entity đã cập nhật
        T updatedEntity = getRepository().save(existingEntity);

        // Trả về DTO
        return getMapper().maptoDto(updatedEntity);
    }

    default void isDeletedChange(U id, R record) {
        T entity = getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found"));
        entity.setIsDeleted(!entity.getIsDeleted());
        getRepository().save(entity);  // Không cần ánh xạ lại record ở đây
    }

    default void deleteById(U id){
        T entity = getRepository().findById(id)
                .orElseThrow( () -> new RuntimeException("Entity not found"));
        getRepository().delete(entity);
    }

}
