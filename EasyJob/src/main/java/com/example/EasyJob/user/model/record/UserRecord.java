package com.example.EasyJob.user.model.record;


import com.example.EasyJob.common.validate.annotation.UUIDConstraint;
import com.example.EasyJob.common.validate.group.InsertInfo;
import com.example.EasyJob.common.validate.group.UpdateInfo;
import com.example.EasyJob.user.model.User;
import com.example.EasyJob.user.valiation.anomation.UniqueUser;
import jakarta.validation.constraints.*;
import org.springframework.validation.annotation.Validated;


import java.util.UUID;

@UniqueUser(groups = {InsertInfo.class, UpdateInfo.class})
@Validated
public record UserRecord(

        @UUIDConstraint(groups = {UpdateInfo.class})
        @Null( groups = InsertInfo.class , message = "ID must be null when creating a new entity")
        @NotNull(groups = UpdateInfo.class, message = "ID is required for updates")
        UUID id,

        @Email(groups = {InsertInfo.class, UpdateInfo.class},message = "{user.email.invalid}")
        @NotBlank(groups = {InsertInfo.class, UpdateInfo.class},message = "{user.email.not-blank}")
        String email,

        @NotBlank(groups = {InsertInfo.class, UpdateInfo.class},message = "{user.password.not-blank}")
        @Size(groups = {InsertInfo.class, UpdateInfo.class},min = 8, max = 20, message = "{user.password.size}")
        String password,

        @NotBlank(groups = {InsertInfo.class, UpdateInfo.class},message = "{user.username.not-blank}")
        @Size(groups = {InsertInfo.class, UpdateInfo.class},min = 8, max = 20, message = "{user.username.not-blank}")
        String fullName,

        @Size(groups = {InsertInfo.class, UpdateInfo.class},max = 15, message = "{user.phone.size}") // Example size constraint
        String phone,

        @Size(groups = {InsertInfo.class, UpdateInfo.class},max = 255, message = "{user.avatar.size}") // Example size constraint
        String avatar,

        @Size(groups = {InsertInfo.class, UpdateInfo.class},max = 1000, message = "{user.experience.size}") // Example size constraint
        String experience,

        @NotNull(groups = {InsertInfo.class, UpdateInfo.class},message = "{user.gender.not-null}")
        User.Gender gender,

        @NotNull(groups = {InsertInfo.class, UpdateInfo.class},message = "{user.status-verified.not-null}")
        User.StatusVerified statusVerified
) {

}
