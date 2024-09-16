package com.project.uber.uberApp.dto;

import com.project.uber.uberApp.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
        private Long id;
        private String name;
        private String email;
        private Set<Role> roles;

}
