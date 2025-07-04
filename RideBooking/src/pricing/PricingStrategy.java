package pricing;

import enums.CarType;
import models.Location;

public interface PricingStrategy {
    public double calculateFare(Location source, Location destination, CarType carType);
}
