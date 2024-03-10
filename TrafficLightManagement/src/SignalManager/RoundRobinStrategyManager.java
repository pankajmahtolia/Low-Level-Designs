package SignalManager;

import Models.Signal;

import java.util.Deque;

public class RoundRobinStrategyManager implements SignalStrategyManager {

    private Deque<Signal> signals;

    public RoundRobinStrategyManager(Deque<Signal> signals) {
        this.signals = signals;
    }

    // Using Round Robin Strategy, other can be priority based scheduling
    @Override
    public void start() {
        Signal prevSignal=null;
        while(true){
            Signal currSignal = signals.getFirst();
            signals.removeFirst();
            if(prevSignal!=null){
                prevSignal.turnOff();
            }
            prevSignal=currSignal;
            currSignal.turnOn();
            signals.addLast(currSignal);

        }
    }
}
