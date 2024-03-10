package Models;

import enums.Direction;
import enums.State;

public class RedLight extends Light{

    public RedLight(String id, Direction direction) {
        super(id, direction, State.RED);
    }

    @Override
    public Light changeState() {
        System.out.println("Light " + this.id + " changed from " + this.lightState + " to " + State.GREEN);
        return new GreenLight(this.id, this.direction);
    }
}
