package service;

import enums.CarType;
import enums.DriverStatus;
import models.Driver;
import models.Location;
import models.User;
import pricing.PricingStrategy;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RideBookingService {
    private UserService userService;
    private DriverService driverService;
    private PricingStrategy pricingStrategy;
    private double radius;

    public RideBookingService(UserService userService, DriverService driverService, PricingStrategy pricingStrategy, double radius) {
        this.userService = userService;
        this.driverService = driverService;
        this.pricingStrategy = pricingStrategy;
        this.radius = radius;
    }

    public void showAvailableRides(String userName, Location destination){
        User user = userService.getUser(userName);
        if(user == null) return;
        Map<String, Driver> driverMap = driverService.getAllDrivers();
        Set<CarType> seen = new HashSet<>();
        double distanceUserAndDestination = user.getLocation().distanceTo(destination);

        for(Map.Entry<String, Driver> entry: driverMap.entrySet()){
            String driverName = entry.getKey();
            Driver driver = entry.getValue();
            double distanceUserAndDriver = user.getLocation().distanceTo(driver.getLocation());

            // nearByDriver
            if(distanceUserAndDriver <= radius){
                if(!seen.contains(driver.getCarType()) && driver.getDriverStatus() == DriverStatus.AVAILABLE){
                    double ridePrice = pricingStrategy.calculateFare(user.getLocation(), destination, driver.getCarType());
                    System.out.println(driver.getCarType() + " Available, Price: " + ridePrice);
                    seen.add(driver.getCarType());
                }
            }
        }
    }


    public void bookRide(String userName, Location destination, CarType carType){
        User user = userService.getUser(userName);
        if(user == null)
            return;
        Driver driver = findNearestAvailableDriver(user.getLocation(),  carType);

        if(driver == null) return;
        driverService.setDriverStatus(driver.getName(), DriverStatus.BOOKED);
        double distance = Math.round( user.getLocation().distanceTo(destination) * 100) / 100.00;
        double ridePrice = Math.round( pricingStrategy.calculateFare(user.getLocation(), destination, driver.getCarType()) * 100) / 100;
        System.out.println("Ride booked with driver " + driver.getName() + " for distance " + distance + " . Fare: ₹" + ridePrice);
    }

    private Driver findNearestAvailableDriver(Location userLocation, CarType carType) {
        Map<String, Driver> driverMap = driverService.getAllDrivers();
        double bestDistance = Integer.MAX_VALUE;
        Driver closestDriver = null;
        for(Map.Entry<String, Driver> entry : driverMap.entrySet()){
            String driverName = entry.getKey();
            Driver driver = entry.getValue();
            if(driver.getCarType().equals(carType) && driver.getDriverStatus().equals(DriverStatus.AVAILABLE)){
                double distance = userLocation.distanceTo(driver.getLocation());
                if(distance <= bestDistance){
                    bestDistance = distance;
                    closestDriver = driver;
                }
            }
        }
        return closestDriver;
    }


}
