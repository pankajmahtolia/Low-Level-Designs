package models;

import enums.CarType;
import enums.DriverStatus;

public class Driver {
    private String name;
    private Location location;
    private CarType carType;
    private DriverStatus driverStatus;

    public Driver(String name, Location location, CarType carType) {
        this.name = name;
        this.location = location;
        this.carType = carType;
        this.driverStatus = DriverStatus.AVAILABLE;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public CarType getCarType() {
        return carType;
    }

    public DriverStatus getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(DriverStatus driverStatus) {
        this.driverStatus = driverStatus;
    }
}
