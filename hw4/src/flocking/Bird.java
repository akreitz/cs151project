package flocking;

import simStation.*;


public class Bird extends Agent {
    private Heading heading;
    private int speed;
    public static Integer MAX_SPEED = 5;
    public Bird(String name, int xc, int yc, Heading heading, int speed, Simulation world) {
        super(name, world);
        this.location.x = xc;
        this.location.y = yc;
        this.heading = heading;
        this.speed = speed;
        this.world = world;
    }

    @Override
    public void update() {
        Bird neighbor = (Bird) world.getNeighbor(this, 5);
        this.heading = neighbor.heading;
        this.speed = neighbor.speed;
        move(speed);
    }
}
