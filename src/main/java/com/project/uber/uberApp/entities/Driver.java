package com.project.uber.uberApp.entities;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_driver_vehicle_id", columnList = "vehicleId")
})
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Double rating;

    private Boolean isAvailable;

    private String vehicleId;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point currentLocation;
}


/*
@OneToOne
Annotation:
Defines a one-to-one relationship between the Driver and User entities. Each Driver is associated with exactly one User.
Logic:
This represents the relationship between a Driver and a User. It implies that every driver has a corresponding user account, possibly storing personal details like name and contact info in the User entity.
4. @JoinColumn(name = "user_id")
Annotation:
Specifies the foreign key column (user_id) in the Driver table, linking the Driver to the User table.
Logic:
The user_id column acts as a foreign key that references the primary key (id) in the User table. This establishes the relationship between a driver and their associated user account.
5. @Column(columnDefinition = "Geometry(Point, 4326)")
Annotation:
Customizes the definition of the currentLocation column. The columnDefinition is used here to define a special column type in the database. In this case, it's defined as a geographical Point (for coordinates) using the SRID (Spatial Reference Identifier) 4326, which is a common coordinate system for GPS locations.
Logic:
This is used for storing geographical location data (latitude and longitude). The Point type, along with the SRID 4326, allows storing and querying the driver’s current location using geospatial queries, making it ideal for location-based services like ride-sharing.

*/
