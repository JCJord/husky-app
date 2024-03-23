package com.HuskyBank.HuskyBank.api.services;

import com.HuskyBank.HuskyBank.api.dto.RegisterDTO;
import com.HuskyBank.HuskyBank.api.models.UserEntity;
import com.HuskyBank.HuskyBank.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity save(RegisterDTO registerDTO) {
        UserEntity user = new UserEntity();

        user.setName(registerDTO.getName());
        user.setCpf(registerDTO.getCpf());
        user.setEmail(registerDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setBalance((long) 0);

        return userRepository.save(user);
    }

    public Long getUserIdByEmail(String email) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        return userOptional.map(UserEntity::getId).orElse(null);
    }
}
