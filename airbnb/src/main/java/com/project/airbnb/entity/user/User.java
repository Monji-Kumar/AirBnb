package com.project.airbnb.entity.user;

import com.project.airbnb.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(generator = "user_seq_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(initialValue = 1, sequenceName = "user_seq", allocationSize = 1, name = "user_seq_gen")
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    private List<Role> roles;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;
}
