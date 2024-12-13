package com.example.EasyJob.user.service;


import com.example.EasyJob.common.mapper.GenericMapper;
import com.example.EasyJob.common.service.GenericService;

import com.example.EasyJob.user.mapper.UserMapper;
import com.example.EasyJob.user.model.User;

import com.example.EasyJob.user.model.record.UserRecord;
import com.example.EasyJob.user.model.reponsese.UserDTO;
import com.example.EasyJob.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService extends GenericService<User,UserRecord, UserDTO, UUID> {
    @Override
    public UserDTO save(UserRecord record);
}


