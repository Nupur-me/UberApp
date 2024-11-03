# Uber Like Backend - Ride Booking App(Spring Boot)

#### This project is a ride booking platform built with Spring Boot, designed to connect riders with drivers efficiently.

## Live Demo | API Documentation(Swagger)
https://uberapp-production.up.railway.app/swagger-ui/index.html

# Key Features:

+ **Ride Booking:** Users can book rides and can manage their profiles. 
+ **Driver Management:** Drivers can manage their profiles, view ride requests, and accept or decline bookings.
+ **Role-based Access Control:** Securely manage different user roles **(admin, rider, driver)** ensuring a controlled and safe environment.
+ **Ride Fare:** Ride fare charges is calculated depending on distance and surge factor.
+ **Review System:** Empowering both restaurant owners and customers to provide and view ratings and feedback.

## Security Features:
+ **Authentication:** JWT tokens are used for secure authentication and session management.
+ **Authorization:** Role-based access control is implemented to manage permissions, ensuring that only authorized users can perform specific actions.
+ **Data Encryption:** Sensitive data, including passwords, is encrypted to protect user information from unauthorized access.

# Technologies Used:
+ **Back-End:** Spring Boot, Spring Security, Spring Data JPA
+ **Database:** MySQL
+ **Authentication:** JWT, Spring Security
+ **Deployment:** Railway App

# Controllers:
### Rider Controller
- `/riders/requestRide`
- `/riders/rateDriver`
- `/riders/cancelRide/{rideId}`
- `/riders/getMyRides`
- `/riders/getMyProfile`

### Driver Controller
- `/drivers/startRide/{rideRequestId}`
- `/drivers/rateRider`
- `/drivers/endRide/{rideId}`
- `/drivers/cancelRide/{rideId}`
- `/drivers/acceptRide/{rideRequestId}`
- `/drivers/getMyRides`
- `/drivers/getMyProfile`

### Auth Controller
- `/auth/signup`
- `/auth/refresh`
- `/auth/onBoardNewDriver/{userId}`
- `/auth/login`
