package com.HuskyBank.HuskyBank.api.controllers;

import com.HuskyBank.HuskyBank.api.dto.RegisterDTO;
import com.HuskyBank.HuskyBank.api.models.UserEntity;
import com.HuskyBank.HuskyBank.api.repository.UserRepository;
import com.HuskyBank.HuskyBank.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    private final UserService userService;

    @Autowired
    public UserController(UserRepository userRepository,UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }
    @PostMapping("signup")
    public ResponseEntity<String> register (@RequestBody RegisterDTO registerDTO) {
        Optional<UserEntity> existingUserByEmail = userRepository.findByEmail(registerDTO.getEmail());
        Optional<UserEntity> existingUserByCpf = userRepository.findByCpf(registerDTO.getCpf());

        if(existingUserByEmail.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("email already exists");
        }

        if(existingUserByCpf.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cpf is already being used");
        }

        boolean isCpfValid = true;

        if(isCpfValid) {
            userService.save(registerDTO);
            return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cpf invalid");
        }
    }
}
