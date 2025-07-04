package service;

import models.Location;
import models.User;

import java.util.HashMap;
import java.util.Map;

public class UserService {
    Map<String, User> userMap = new HashMap<>();

    public void registerUser(String name, Location location){
        if (userMap.containsKey(name)) {
            System.out.println("User Already Registered");
        }else {
            userMap.put(name, new User(name, location));
            System.out.println("User: " + name + " Registered");
        }
    }

    public Map<String, User> getAllUsers(){
        return userMap;
    }

    public User getUser(String userName) {
        return userMap.get(userName);
    }
}
