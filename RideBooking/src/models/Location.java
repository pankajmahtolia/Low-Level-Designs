package models;

public class Location {
    private double x;
    private double y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // calucte the distance between this and other
    public double distanceTo(Location other){
        return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
    }
}
