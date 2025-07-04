import enums.CarType;
import models.Location;
import pricing.DefaultPricingStrategy;
import pricing.PricingStrategy;
import service.DriverService;
import service.RideBookingService;
import service.UserService;

public class RideBookingSystemMain {
    public static void main(String[] args) {
        UserService userService = new UserService();
        DriverService driverService = new DriverService();
        PricingStrategy pricingStrategy = new DefaultPricingStrategy();
        double radius = 10;

        RideBookingService rideBookingService = new RideBookingService(userService, driverService, pricingStrategy, radius);

        userService.registerUser("Pankaj", new Location(0, 0));
        driverService.registerDriver("Tripti", new Location(2, 2), CarType.SUV);
        driverService.registerDriver("Shiva", new Location(4, 4), CarType.SEDAN);

        Location destination = new Location(10, 10);
        rideBookingService.showAvailableRides("Pankaj", destination);
        rideBookingService.bookRide("Pankaj", destination, CarType.SUV);

    }

}