package com.example.EasyJob.user.controller;


import com.example.EasyJob.common.validate.group.InsertInfo;
import com.example.EasyJob.common.validate.group.UpdateInfo;
import com.example.EasyJob.user.model.record.UserRecord;
import com.example.EasyJob.user.model.reponsese.UserDTO;
import com.example.EasyJob.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/User")
@AllArgsConstructor
public class Controller {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Validated(InsertInfo.class) @RequestBody UserRecord record) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(record));

    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@Validated(UpdateInfo.class) @RequestBody UserRecord record) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(record.id(),record));
    }
}
