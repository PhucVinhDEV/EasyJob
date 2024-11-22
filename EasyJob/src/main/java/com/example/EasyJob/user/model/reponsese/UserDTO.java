package com.example.EasyJob.user.model.reponsese;

import lombok.*;

import java.util.UUID;



@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String email;
    private String password;


}
