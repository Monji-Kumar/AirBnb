package com.project.airbnb.dto.user;

import com.project.airbnb.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private List<Role> roles;
    private String name;
    private String email;
    private String password;
}
