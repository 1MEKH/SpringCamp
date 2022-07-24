package com.ua.jwt.controller;

import com.ua.jwt.domain.User;
import com.ua.jwt.dto.UserDto;
import com.ua.jwt.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminRestController {

    private final UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        User user = userService.findById(id);
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("users")
    public ResponseEntity<UserDto> createUser(@RequestBody AuthenticationRequestDto userDto){
        User user = AuthenticationRequestDto.toUser(userDto);
        userService.register(user);
        UserDto result = UserDto.fromUser(user);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
