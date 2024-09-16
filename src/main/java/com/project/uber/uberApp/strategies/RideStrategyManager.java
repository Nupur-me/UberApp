package com.project.uber.uberApp.strategies;

import com.project.uber.uberApp.strategies.impl.DriverMatchingHighestRatedDriverStrategy;
import com.project.uber.uberApp.strategies.impl.DriverMatchingNearestDriverStrategy;
import com.project.uber.uberApp.strategies.impl.RideFairDefaultFareCalculationStrategy;
import com.project.uber.uberApp.strategies.impl.RideFairSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {

    private final DriverMatchingHighestRatedDriverStrategy driverMatchingHighestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy driverMatchingNearestDriverStrategy;
    private final RideFairDefaultFareCalculationStrategy rideFairDefaultFareCalculationStrategy;
    private final RideFairSurgePricingFareCalculationStrategy rideFairSurgePricingFareCalculationStrategy;

    public DriverMatchingStrategy driverMatchingStrategy(double riderRating){

        if(riderRating >= 4.8){
            return driverMatchingHighestRatedDriverStrategy;
        }
        else {
            return driverMatchingNearestDriverStrategy;
        }
    }

    public RideFairCalculateStrategy rideFairCalculateStrategy(){

        LocalTime surgeStartTime = LocalTime.of(18,0);
        LocalTime surgeEndTime = LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);

        if(isSurgeTime){
            return rideFairSurgePricingFareCalculationStrategy;
        } else {
            return rideFairDefaultFareCalculationStrategy;
        }

    }
}
