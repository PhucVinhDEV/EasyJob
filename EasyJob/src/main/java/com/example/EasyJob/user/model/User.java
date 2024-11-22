package com.example.EasyJob.user.model;

import com.example.EasyJob.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
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
@Table(name = User.UserEntity.TABLE_NAME )
public class User extends BaseEntity {

    @Column(name = UserEntity.EMAIL, nullable = false, unique = true)
    private String email;

    @Column(name = UserEntity.PASSWORD, nullable = false)
    private String password;

    @Column(name = UserEntity.FULLNAME, nullable = false)
    private String fullname;

    @Column(name = UserEntity.PHONE)
    private String phone;

    @Column(name = UserEntity.AVATAR)
    private String avatar;

    @Column(name = UserEntity.EXPERIENCE)
    private String experience;

    @Enumerated(EnumType.STRING) // Lưu dưới dạng chuỗi trong DB
    @Column(name = UserEntity.GENDER, nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING) // Lưu dưới dạng chuỗi trong DB
    @Column(name = UserEntity.STATUS_VERIFIED, nullable = false)
    private StatusVerified statusVerified;


    public enum Gender {
        MALE,
        FEMALE,
        OTHER
    }

    public enum StatusVerified{
        NONE,
        PENDING,
        VERIDIED
    }

//    Set<String> skills = new HashSet<>();

    @UtilityClass
    static class UserEntity {
        public static final String TABLE_NAME = "J_USER";
        public static final String EMAIL = "J_EMAIL";
        public static final String PASSWORD = "J_PASSWORD";
        public static final String FULLNAME = "J_FULLNAME";
        public static final String PHONE = "J_PHONE";
        public static final String GENDER = "J_GENDER";
        public static final String AVATAR = "J_AVATAR";
        public static final String EXPERIENCE = "J_EXPERIENCE";
        public static final String STATUS_VERIFIED = "J_STATUS_VERIFIED";
    }
}
