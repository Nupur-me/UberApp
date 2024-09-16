package com.project.uber.uberApp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;

}


/*

@OneToOne
Annotation:
Defines a one-to-one relationship between two entities. Here, it indicates that each Rider is associated with exactly one User, and each User has exactly one Rider.
        Logic:
This enforces a one-to-one relationship at both the application level and the database level. In this case, every Rider is tied to a single User object.
In a ride-sharing application, this can represent the fact that a rider account (user) is mapped to a user profile in the system.

@JoinColumn(name = "user_id")
Annotation:
Specifies the foreign key column (user_id) in the Rider table that will refer to the primary key (id) in the User table.
Logic:
The user_id column in the Rider table acts as a foreign key, establishing a link to the User table. This allows the Rider entity to be associated with a User entity, which is essential for accessing user details when interacting with riders.*/
