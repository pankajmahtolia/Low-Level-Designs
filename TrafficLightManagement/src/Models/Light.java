package Models;

import enums.Direction;
import enums.State;

public abstract class Light {
    protected String id;
    protected Direction direction;
    protected State lightState;
    public Light(String id, Direction direction, State lightState) {
        this.id = id;
        this.direction = direction;
        this.lightState = lightState;
    }

    public abstract Light changeState();

}
