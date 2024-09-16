package com.project.uber.uberApp.entities;


import com.project.uber.uberApp.entities.enums.PaymentMethod;
import com.project.uber.uberApp.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity // Marks the class as a JPA entity
@Getter // Generates getter methods
@Setter // Generates setter methods
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "idx_ride_rider", columnList = "rider_id"),
        @Index(name = "idx_ride_driver", columnList = "driver_id")

})
public class Ride {

    @Id // Defines the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the primary key value
    private Long id;

    @Column(columnDefinition = "Geometry(Point, 4326)") // Stores pickup location as a geographical point
    private Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point, 4326)")// Stores drop-off location as a geographical point
    private Point dropOfLocation;

    //when driver accepts the ride
    @CreationTimestamp // Automatically sets the creation timestamp
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY) // Many ride requests to one rider, loaded lazily, //one rider can have multiple ride request, first day or second day, so we need to store all in db, it is in DB Sense
    private Rider rider;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @Enumerated(EnumType.STRING) // Stores payment method as a string
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING) // Stores ride status as a string
    private RideStatus rideStatus;

    private Double fare;

    private String otp;

    //when the ride starts
    private LocalDateTime startedAt;

    //when the ride ended
    private LocalDateTime endedAt;
}
