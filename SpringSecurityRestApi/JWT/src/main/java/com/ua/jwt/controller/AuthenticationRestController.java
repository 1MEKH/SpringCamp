package com.ua.jwt.controller;

import com.ua.jwt.domain.User;
import com.ua.jwt.dto.AuthenticationRequestDto;
import com.ua.jwt.dto.AuthenticationResponseDto;
import com.ua.jwt.security.jwt.JwtTokenProvider;
import com.ua.jwt.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider,
                                        UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponseDto> login(@RequestBody AuthenticationRequestDto requestDto){

        String username = requestDto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, requestDto.getPassword()
        ));

        User user = userService.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        String token = jwtTokenProvider.createToken(username, user.getRoles());

        return ResponseEntity.ok(new AuthenticationResponseDto(username,token));
    }
}
