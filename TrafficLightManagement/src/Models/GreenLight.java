package Models;

import enums.Direction;
import enums.State;

public class GreenLight extends Light{

    public GreenLight(String id, Direction direction) {
        super(id, direction, State.GREEN);
    }
    @Override
    public Light changeState() {
        System.out.println("Light " + this.id + " changed form " + this.lightState + " to " + State.YELLOW);
        return new YellowLight(this.id, this.direction);
    }
}
