package com.example.EasyJob.user.controller;


import com.example.EasyJob.user.model.record.UserRecord;
import com.example.EasyJob.user.model.reponsese.UserDTO;
import com.example.EasyJob.user.repository.UserRepository;
import com.example.EasyJob.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/User")
@AllArgsConstructor
public class Controller {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserRecord record) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(record));

    }

    @PutMapping
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserRecord record) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(record.id(),record));
    }
}
