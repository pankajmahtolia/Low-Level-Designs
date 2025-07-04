package pricing;

import enums.CarType;
import models.Location;

public class DefaultPricingStrategy implements PricingStrategy{
    private double base;

    @Override
    public double calculateFare(Location source, Location destination, CarType carType) {
        double distance = source.distanceTo(destination);
        double base = switch (carType){
            case MINI -> 50;
            case SEDAN -> 75;
            case SUV -> 85;
        };

        double perKm = switch (carType){
            case MINI -> 25;
            case SEDAN -> 28;
            case SUV -> 31;
        };

        return base + distance*perKm;
    }
}
