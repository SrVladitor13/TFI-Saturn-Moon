package com.saturnmoon.service;

import com.saturnmoon.dto.LoginRequest;
import com.saturnmoon.dto.LoginResponse;
import com.saturnmoon.dto.RegisterRequest;
import com.saturnmoon.model.Role;
import com.saturnmoon.model.User;
import com.saturnmoon.repository.RoleRepository;
import com.saturnmoon.repository.UserRepository;
import com.saturnmoon.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenProvider tokenProvider;
    
    @Transactional
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        Role customerRole = roleRepository.findByName("CUSTOMER")
                .orElseThrow(() -> new RuntimeException("Role CUSTOMER no encontrado. Por favor ejecuta el script de inicialización."));
        
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setRole(customerRole);
        user.setIsActive(true);
        
        User savedUser = userRepository.save(user);
        userRepository.flush();
        
        return savedUser;
    }
    
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        
        String token = tokenProvider.generateToken(authentication);
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        return LoginResponse.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().getName())
                .build();
    }
}
