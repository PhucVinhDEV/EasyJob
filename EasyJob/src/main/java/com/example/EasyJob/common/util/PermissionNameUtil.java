package com.example.EasyJob.common.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PermissionNameUtil {
    public static final String VIEW_USER = "VIEW_USER"; // Xem danh sách người dùng
    public static final String CREATE_USER = "CREATE_USER"; // Tạo người dùng
    public static final String UPDATE_USER = "UPDATE_USER"; // Sửa thông tin người dùng
    public static final String DELETE_USER = "DELETE_USER"; // Xóa người dùng

    public static final String VIEW_TASK = "VIEW_TASK"; // Xem công việc
    public static final String CREATE_TASK = "CREATE_TASK"; // Tạo công việc
    public static final String UPDATE_TASK = "UPDATE_TASK"; // Sửa công việc
    public static final String DELETE_TASK = "DELETE_TASK"; // Xóa công việc

    public static final String VIEW_REPORT = "VIEW_REPORT"; // Xem báo cáo
    public static final String MANAGE_PERMISSION = "MANAGE_PERMISSION"; // Quản lý quyền
}
