package com.project.uber.uberApp.strategies;


import com.project.uber.uberApp.entities.RideRequest;
import org.springframework.stereotype.Service;

@Service
public interface RideFairCalculateStrategy {

    double RIDE_FARE_MULTIPLIER = 10;

    double calculateFare(RideRequest rideRequest);

}
