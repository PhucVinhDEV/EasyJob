package com.example.EasyJob.common.model;

import com.example.EasyJob.common.util.DateTimeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import lombok.experimental.UtilityClass;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * BaseEntity class là lớp cơ sở chung cho các thực thể trong ứng dụng,
 * cung cấp các trường chung như ID, version, createdAt, lastModifiedAt, lastModifiedBy.
 * Nó sử dụng cơ chế audit của Spring Data JPA để tự động cập nhật thời gian tạo
 * và thời gian chỉnh sửa gần nhất của đối tượng.
 */
@SuperBuilder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable, Comparable<BaseEntity>  {

    /**
     * ID duy nhất của thực thể, kiểu UUID, tự động sinh.
     */
    @Id
    @JdbcTypeCode(SqlTypes.UUID)
    @GeneratedValue
    @Column(name = Columns.ID)
    protected UUID id;

    /**
     * Trường version dùng cho cơ chế optimistic locking, giúp đảm bảo tính toàn vẹn dữ liệu.
     */
    @Version
    @Column(name = Columns.VERSION)
    protected Integer version;

    /**
     * Thời gian tạo của thực thể, được tự động gán khi thực thể được tạo mới.
     * Sử dụng @JsonFormat để định dạng khi chuyển đổi sang JSON.
     */
    @CreatedDate
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtil.DATE_TIME_FORMAT)
    @Column(name = Columns.CREATED_AT)
    protected LocalDateTime createdAt;

    /**
     * Thời gian chỉnh sửa lần cuối của thực thể, tự động cập nhật khi thực thể được sửa đổi.
     * Sử dụng @JsonFormat để định dạng khi chuyển đổi sang JSON.
     */
    @LastModifiedDate
    @DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeUtil.DATE_TIME_FORMAT)
    @Column(name = Columns.LAST_MODIFIED_AT)
    protected LocalDateTime lastModifiedAt;

    /**
     * Người tạo thực thể, tự động gán khi thực thể được tạo.
     */
    @CreatedBy
    @Column(name = Columns.CREATED_BY)
    protected String createdBy;

    /**
     * Người chỉnh sửa lần cuối của thực thể, tự động cập nhật khi thực thể được sửa đổi.
     */
    @LastModifiedBy
    @Column(name = Columns.LAST_MODIFIED_BY)
    protected String lastModifiedBy;

    /**
     * Phương thức so sánh `compareTo` để so sánh thời gian tạo (`createdAt`) giữa các thực thể.
     * Phương thức này sắp xếp các thực thể theo thứ tự thời gian giảm dần.
     * @param entity thực thể để so sánh
     * @return -1 nếu `entity` mới hơn, 1 nếu `entity` bằng hoặc cũ hơn
     */
    @Override
    public int compareTo(BaseEntity entity) {
        LocalDateTime entityTime = entity.getCreatedAt();
        if (entityTime.isAfter(createdAt)) {
            return -1;
        } else { // equal or before
            return 1;
        }
    }

    @Column(name = Columns.IS_DELETED)
    protected Boolean isDeleted = false;

    /**
     * Utility class chứa các tên cột cố định sử dụng trong bảng,
     * giúp tránh lỗi khi sử dụng tên cột trực tiếp trong các annotation.
     */
    @UtilityClass
    static class Columns {
        public static final String ID = "ID";
        public static final String VERSION = "VERSION";
        public static final String CREATED_AT = "CREATED_AT";
        public static final String CREATED_BY = "CREATED_BY";
        public static final String LAST_MODIFIED_AT = "LAST_MODIFIED_AT";
        public static final String LAST_MODIFIED_BY = "LAST_MODIFIED_BY";
        public static final String IS_DELETED = "IS_DELETED";
    }
}
