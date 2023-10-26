package com.plan.todo.controller;

import com.plan.todo.dto.ResponseDTO;
import com.plan.todo.dto.UserDTO;
import com.plan.todo.model.UserEntity;
import com.plan.todo.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    // 유저 가져오기
    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO)
    {
        try {
            if (userDTO == null || userDTO.getPassword() == null) {
                throw new RuntimeException("Invalid Password value");
            }

            UserEntity user = UserEntity.builder()
                    .username(userDTO.getUsername())
                    .password(userDTO.getPassword())
                    .build();
            UserEntity registerdUser = userService.create(user);
            UserDTO responseUserDTO = UserDTO.builder()
                    .id(registerdUser.getId())
                    .username(registerdUser.getUsername())
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        } catch (Exception e) {
            ResponseDTO responseDTO = ResponseDTO.builder().error(e.getMessage()).build();
            return ResponseEntity.badRequest().body(responseDTO);

        }
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody UserDTO userDTO)
    {
        UserEntity user = userService.getByCredential(userDTO.getUsername(), userDTO.getPassword());

        if(user != null){
            final UserDTO responseUserDTO = UserDTO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .build();
            return ResponseEntity.ok().body(responseUserDTO);
        } else {
            ResponseDTO responseDTO = ResponseDTO.builder().error("Login failed").build();
            return ResponseEntity.badRequest().body(responseDTO);
        }

    }
}
