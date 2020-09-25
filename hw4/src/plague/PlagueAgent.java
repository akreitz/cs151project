package plague;

import java.awt.*;
import mvc.*;
import simStation.*;

/*
 * Edit History 
 * 4/1  - Created
 * 
 * 4/2  - added move logic
 * 
 * 4/10 - added infection logic
 * 
 */

public class PlagueAgent extends Agent {
	
	private PlagueSim plagueWorld;
	private boolean infected;

	public PlagueAgent(String name, PlagueSim sim) {
		super(name, sim);
		plagueWorld = sim;
		this.infected = false;
	}
	
	public PlagueAgent(String name, PlagueSim sim, boolean inf) {
		super(name, sim);
		plagueWorld = sim;
		this.infected = inf;
	}
	
	public Color getColor() {
		if (infected) {
			return Color.RED;
		} else {
			return Color.GREEN;
		}
	}
	
	/*
	 * Look at all the neighbors of the current agent
	 * if there is an infected neighbor, there is a
	 * VIRULENCE percent chance that the current agent
	 * will become infected (if not already infected).
	 * 
	 * This better represents the simulated transmission
	 * of an airborne disease like CoVID-19 rather than
	 * a disease that can only be transmitted directly from
	 * one person to another (ie HIV).
	 */
	public void infect() {
		PlagueAgent current = this;
		if (!current.infected) {
			for (PlagueAgent a : plagueWorld.getNeighbors(current, 5)) {
				if (a.infected) {
					int luck = Utilities.rng.nextInt(100);
					if (luck < plagueWorld.VIRULENCE) {
						this.infected = true;
						plagueWorld.newInfection();
					}
				}
			}
		}
		plagueWorld.changed();
	}
	
	/*
	 * Movement in this sim is similar to that in random walk
	 * in that agents are drunks, and will wander relatively
	 * aimlessly. Added the caveat that they can move no more than
	 * 1/10 of the size of the world.
	 */
	
	@Override
	public void move() {
		heading = Heading.randHeading();
		if (heading == Heading.NORTH) {
			this.location.y -= Utilities.rng.nextInt(Simulation.WORLD_SIZE/10);
		} else if (heading == Heading.EAST) {
			this.location.x += Utilities.rng.nextInt(Simulation.WORLD_SIZE/10);
		} else if (heading == Heading.SOUTH) {
			this.location.y += Utilities.rng.nextInt(Simulation.WORLD_SIZE/10);
		} else if (heading == Heading.WEST) {
			this.location.x -= Utilities.rng.nextInt(Simulation.WORLD_SIZE/10);
		}
		adjustPos();
		infect();
	}

	@Override
	public void update() {
		move();
		plagueWorld.changed();
	}

}
