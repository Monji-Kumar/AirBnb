package com.project.airbnb.dto.guest;

import com.project.airbnb.dto.user.UserDto;
import com.project.airbnb.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GuestDto {

    private Long id;
    private UserDto user;
    private String name;
    private LocalDateTime createdAt;
    private Gender gender;
}
