package com.ua.jwt.service;

import com.ua.jwt.domain.Role;
import com.ua.jwt.domain.Status;
import com.ua.jwt.domain.User;
import com.ua.jwt.repository.RoleRepository;
import com.ua.jwt.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService{

    private final Logger log = Logger.getLogger(UserService.class.getName());

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);

        User registeredUser = userRepository.save(user);
        log.info("IN register - user: " + registeredUser.getEmail()  + " successfully registered");
        return registeredUser;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN getAll - " + result.size() + " users found");
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);

        if(result == null){
            log.warning("IN findById - no user found bu username " + username);
            return null;
        }

        log.info("IN findByUsername - user found by username: " + username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if(result == null){
            log.warning("IN findById - no user found bu id " + id);
            return null;
        }

        log.info("IN findById - user found by id: " + id);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN delete - user with id:" + id + " successfully deleted");
    }
}
