package com.example.EasyJob.common.util;

import com.example.EasyJob.user.model.User;
import lombok.experimental.UtilityClass;
import org.springframework.context.MessageSource;

import java.util.Arrays;
import java.util.Locale;

@UtilityClass
public class MessageUtil {
    public static final String EMAIL_NOT_EXIST = "Tài khoản không tồn tại";
    public static final String PASSWORD_NOT_CORRECT = "Mật khẩu Không khớp";
    public static final String JWT_AUTHENTICATION_FAILED="JWT Authentication Failed";
    public static final String INVALID_UUID_FORMAT = "Mã ID(UUID) không hợp lệ";
    public static final String TOKEN_EXPIRED="Token Expired";
    public static final String REFRESH_TOKEN_INVALID="RefreshToken Invalid";
    public static final String ACCESS_TOKEN_INVALID="AccessToken Invalid";
    public static final String UUID_NOT_FOUND = "Không tìm thấy mã ID(UUID) trùng khớp";

    public static final String UNAUTHEN_REFRESHTOKEN_NOT_CORRECT = "RefreshToken Không hợp lệ";
    public static final String INVALID_DATE_FORMAT
            = "Date Format không hợp lệ. Date Format yêu cầu: " + DateTimeUtil.DATE_FORMAT;
    public static final String INVALID_USER_ACCOUNT_STATUS
            = "Trạng thái người dùng không hợp lệ. Trạng thái gồm: " + Arrays.toString(User.StatusVerified.values());
    public static final String INVALID_USER_GENDER
            = "Giới tính người dùng không hợp lệ. Giới tính gồm: " + Arrays.toString(User.Gender.values());
//    public static final String INVALID_PROJECT_STATUS
//            = "Trạng thái dự án không hợp lệ. Trạng thái gồm: " + Arrays.toString(Project.Status.values());
//    public static final String INVALID_TASK_STATUS
//            = "Trạng thái công việc không hợp lệ. Trạng thái công việc gồm: " + Arrays.toString(Task.Status.values());
//    public static final String INVALID_NOTIFICATION_STATUS
//            = "Trạng thái thông báo không hợp lệ. Trạng thái thông báo gồm: " + Arrays.toString(Notification.Status.values());
    public static String getMessage(MessageSource messageSource, String messageCode) {
        return messageSource.getMessage(messageCode, null, Locale.getDefault());
    }
    public static String getMessage(MessageSource messageSource, Object[] params, String messageCode) {
        return messageSource.getMessage(messageCode, params, Locale.getDefault());
    }
}
