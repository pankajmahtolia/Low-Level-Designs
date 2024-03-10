package Models;

import java.util.List;

public class Signal {
    private String id;

    List<Light> lights;

    public Signal(String id, List<Light> lights) {
        this.id = id;
        this.lights = lights;
    }

    public void turnOff()  {
        try {
            System.out.println("Changing state");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lights.replaceAll(Light::changeState);
    }

    public void turnOn() {
        try {
            System.out.println("Changing state");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        lights.replaceAll(Light::changeState);
    }
}
