package com.HuskyBank.HuskyBank.api.controllers;

import com.HuskyBank.HuskyBank.api.dto.AuthResponseDTO;
import com.HuskyBank.HuskyBank.api.dto.LoginDTO;
import com.HuskyBank.HuskyBank.api.repository.UserRepository;
import com.HuskyBank.HuskyBank.api.security.JwtGenerator;
import com.HuskyBank.HuskyBank.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private JwtGenerator jwtGenerator;
    private UserRepository userRepository;

    private UserService userService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtGenerator jwtGenerator, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        String email = authentication.getName();
        System.out.println(email);
        Long userId = userService.getUserIdByEmail(email);

        return new ResponseEntity<>(new AuthResponseDTO(token, email, userId), HttpStatus.OK);
    }
}
