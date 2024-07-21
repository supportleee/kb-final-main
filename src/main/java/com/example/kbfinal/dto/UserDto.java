package com.example.kbfinal.dto;

import com.example.kbfinal.entity.User;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phonenumber;

    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .address(user.getAddress())
                .phonenumber(user.getPhonenumber())
                .build();

    }
}
