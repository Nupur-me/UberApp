package com.project.uber.uberApp.entities;


import com.project.uber.uberApp.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "app_user", indexes = {
        @Index(name = "idx_user_email", columnList = "email")
})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public String getUsername() {
        return "";
    }
}

/*
Here's a brief explanation of the annotations used in your User class:

@Entity: Marks the class as a JPA entity, which means it's mapped to a database table.
@Table(name = "app_user"): Specifies the table name as app_user instead of the default class name (User), aligning with the database table.
@Id: Defines the primary key of the entity.
@GeneratedValue(strategy = GenerationType.IDENTITY): Specifies the primary key generation strategy. IDENTITY tells JPA to rely on the database to generate the primary key automatically.
@Column(unique = true): Ensures that the email column has unique values in the database to prevent duplicate entries.
@ElementCollection(fetch = FetchType.LAZY): Defines a collection of elements (e.g., roles) as part of the entity and is used to map a collection of non-entity values (like enums or basic types) in a separate table. LAZY loading ensures that the roles are fetched only when needed.
@Enumerated(EnumType.STRING): Specifies that the enum values (e.g., Role) should be stored as strings in the database rather than their ordinal values such as 0,1, etc.*/
