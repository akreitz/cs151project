package flocking;

import mvc.*;
import simStation.*;

public class Flocking extends Simulation {
    @Override
    public void populate() {
        super.populate();
        System.out.println("populate");
        for (int i = 0; i < Simulation.NUM_AGENTS; i++) {
            Heading randHeading = Heading.randHeading();
            int randSpeed = Utilities.rng.nextInt(Bird.MAX_SPEED);
            if (randSpeed == 0) { randSpeed = 1;}

            Agent newAgent = new Bird("alvin", Utilities.rng.nextInt(Simulation.WORLD_SIZE), Utilities.rng.nextInt(Simulation.WORLD_SIZE), randHeading, randSpeed, this);
            agents.add(newAgent);
        }
        changed();
    }
}
