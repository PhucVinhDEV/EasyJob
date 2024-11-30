package com.example.EasyJob.user.mapper;

import com.example.EasyJob.common.mapper.GenericMapper;
import com.example.EasyJob.user.model.User;
import com.example.EasyJob.user.model.record.UserRecord;
import com.example.EasyJob.user.model.reponsese.UserDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper extends GenericMapper<UserRecord, User, UserDTO> {
}
