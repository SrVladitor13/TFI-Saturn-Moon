package com.saturnmoon.service;

import com.saturnmoon.dto.UserProfileDto;
import com.saturnmoon.dto.UserUpdateDto;
import com.saturnmoon.model.User;
import com.saturnmoon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
    }
    
    public User findById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }
    
    @Transactional
    public User updateProfile(Integer userId, UserUpdateDto updateDto) {
        User user = findById(userId);
        
        if (updateDto.getFirstName() != null) {
            user.setFirstName(updateDto.getFirstName());
        }
        if (updateDto.getLastName() != null) {
            user.setLastName(updateDto.getLastName());
        }
        if (updateDto.getPhone() != null) {
            user.setPhone(updateDto.getPhone());
        }
        
        return userRepository.save(user);
    }
    
    @Transactional
    public void changePassword(Integer userId, String newPassword) {
        User user = findById(userId);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
    
    public UserProfileDto getUserProfile(Integer userId) {
        User user = findById(userId);
        return UserProfileDto.fromUser(user);
    }
}
