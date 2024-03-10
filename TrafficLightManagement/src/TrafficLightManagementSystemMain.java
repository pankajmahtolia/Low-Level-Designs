import Models.Light;
import Models.RedLight;
import Models.Signal;
import SignalManager.SignalStrategyManager;
import SignalManager.RoundRobinStrategyManager;
import enums.Direction;

import java.util.ArrayDeque;
import java.util.Arrays;

public class TrafficLightManagementSystemMain {
    public static void main(String[] args) {

        System.out.println("Traffic Light Management!");

        // Initial System has all RED Lights
        Light light1 = new RedLight("11", Direction.LEFT);
        Light light2 = new RedLight("12", Direction.STRAIGHT);
        Light light3 = new RedLight("13", Direction.RIGHT);


        Light light4 = new RedLight("21", Direction.LEFT);
        Light light5 = new RedLight("22", Direction.STRAIGHT);
        Light light6 = new RedLight("23", Direction.RIGHT);

        Light light7 = new RedLight("31", Direction.LEFT);
        Light light8 = new RedLight("32", Direction.STRAIGHT);
        Light light9 = new RedLight("33", Direction.RIGHT);

        Light light10 = new RedLight("41", Direction.LEFT);
        Light light11 = new RedLight("42", Direction.STRAIGHT);
        Light light12 = new RedLight("43", Direction.RIGHT);

        // Plane your signal as per need.
        Signal signal1 = new Signal("1", Arrays.asList(light1, light2, light3));
        Signal signal2 = new Signal("2", Arrays.asList(light4, light5, light6));
        Signal signal3 = new Signal("3", Arrays.asList(light7, light8, light9));
        Signal signal4 = new Signal("4", Arrays.asList(light10, light11, light12));

        ArrayDeque<Signal> signalList = new ArrayDeque<Signal>();
        signalList.add(signal1); signalList.add(signal2); signalList.add(signal3); signalList.add(signal4);

        // Calling Strategy 1 which is RoundRobin Scheduling Strategy
        SignalStrategyManager signalStrategyManager = new RoundRobinStrategyManager(signalList);
        signalStrategyManager.start();
    }
}