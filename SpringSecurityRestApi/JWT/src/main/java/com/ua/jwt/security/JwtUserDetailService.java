package com.ua.jwt.security;

import com.ua.jwt.domain.User;
import com.ua.jwt.security.jwt.JwtUser;
import com.ua.jwt.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailService implements UserDetailsService {

    private final UserService userService;

    public JwtUserDetailService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUsername(username);
        if(user == null){
            throw  new UsernameNotFoundException("User with username: " + username + " not found");
        }
        return new JwtUser(user);
    }
}
