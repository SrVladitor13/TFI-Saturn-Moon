package com.saturnmoon.dto;

import com.saturnmoon.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role;
    
    public static UserProfileDto fromUser(User user) {
        return new UserProfileDto(
            user.getId(),
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPhone(),
            user.getRole().getName()
        );
    }
}
