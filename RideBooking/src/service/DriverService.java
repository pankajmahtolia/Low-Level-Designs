package service;

import enums.CarType;
import enums.DriverStatus;
import models.Driver;
import models.Location;

import java.util.HashMap;
import java.util.Map;

public class DriverService {

    Map<String, Driver> driverMap = new HashMap<>();

    public void registerDriver(String name, Location location, CarType carType){
        if (driverMap.containsKey(name)) {
            System.out.println("Driver Already Registered");
        }else {
            driverMap.put(name, new Driver(name, location, carType));
            System.out.println("Driver: " + name + " Registered");
        }
    }

    public Map<String, Driver> getAllDrivers(){
        return driverMap;
    }

    public void setDriverStatus(String name, DriverStatus driverStatus){
        if(driverMap.containsKey(name))
                driverMap.get(name).setDriverStatus(driverStatus);
    }

}
