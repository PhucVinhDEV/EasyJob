package com.example.EasyJob.user.model.record;


import com.example.EasyJob.common.validate.annotation.UUIDConstraint;
import com.example.EasyJob.common.validate.group.InsertInfo;
import com.example.EasyJob.common.validate.group.UpdateInfo;
import com.example.EasyJob.user.model.User;
import com.example.EasyJob.user.valiation.anomation.UniqueUser;
import jakarta.validation.constraints.*;

import java.util.UUID;

@UniqueUser(groups = {InsertInfo.class, UpdateInfo.class})
public record UserRecord(

        @UUIDConstraint()
        UUID id,

        @Email(message = "{user.email.invalid}")
        @NotBlank(message = "{user.email.not-blank}")
        String email,

        @NotBlank(message = "{user.password.not-blank}")
        @Size(min = 8, max = 20, message = "{user.password.size}")
        String password,

        @NotBlank(message = "{user.username.not-blank}")
        @Size(min = 8, max = 20, message = "{user.username.not-blank}}")
        String fullName,

        @Size(max = 15, message = "{user.phone.size}") // Example size constraint
        String phone,

        @Size(max = 255, message = "{user.avatar.size}") // Example size constraint
        String avatar,

        @Size(max = 1000, message = "{user.experience.size}") // Example size constraint
        String experience,

        @NotNull(message = "{user.gender.not-null}")
        User.Gender gender,

        @NotNull(message = "{user.status-verified.not-null}")
        User.StatusVerified statusVerified
) {
}
