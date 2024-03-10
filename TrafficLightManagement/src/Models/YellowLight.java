package Models;

import enums.Direction;
import enums.State;

public class YellowLight extends Light{

    public YellowLight(String id, Direction direction) {
        super(id, direction, State.YELLOW);
    }

    @Override
    public Light changeState() {
        System.out.println("Light " + this.id + " changed from " + this.lightState + " to " + State.RED);
        return new RedLight(this.id, this.direction);
    }
}
