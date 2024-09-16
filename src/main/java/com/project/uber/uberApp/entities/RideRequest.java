package com.project.uber.uberApp.entities;


import com.project.uber.uberApp.entities.enums.PaymentMethod;
import com.project.uber.uberApp.entities.enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;


@Entity // Marks the class as a JPA entity
@Getter // Generates getter methods
@Setter // Generates setter methods
@Table(indexes = {
        @Index(name = "idx_ride_request_rider", columnList = "rider_id")
})
public class RideRequest {

    @Id // Defines the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the primary key value
    private Long id;

    @Column(columnDefinition = "Geometry(Point, 4326)") // Stores pickup location as a geographical point
    private Point pickupLocation;

    @Column(columnDefinition = "Geometry(Point, 4326)") // Stores drop-off location as a geographical point
    private Point dropOfLocation;

    @CreationTimestamp // Automatically sets the creation timestamp
    private LocalDateTime requestedTime;

    @ManyToOne(fetch = FetchType.LAZY) // Many ride requests to one rider, loaded lazily, //one rider can have multiple ride request, first day or second day, so we need to store all in db, it is in DB Sense
    private Rider rider;

    @Enumerated(EnumType.STRING) // Stores payment method as a string
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING) // Stores ride request status as a string
    private RideRequestStatus rideRequestStatus;

    private Double fare;
}

