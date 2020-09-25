package flocking;

import mvc.Model;
import simStation.*;

public class FlockingFactory extends SimulationFactory {
    @Override
    public Model makeModel() {
        return new Flocking();
    }
}
