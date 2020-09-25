package simStation;

import java.io.*;
import java.awt.*;
import java.util.*;
import mvc.*;

/*
 * Edit History 
 * 3/27 - Created
 * 
 * 4/1  - added helper methods adjustPos() and between()
 * 
 * 4/8  - added reference to world
 * 
 */

public abstract class Agent implements Runnable, Serializable {
	
	protected Simulation world;
	
	protected AgentState state;
	protected Thread thread;
	
	protected String name;
	protected Heading heading;
	protected Point location;
	protected Color color;

	public Agent(String name, Simulation sim) {
		this.name = name;
		this.state = AgentState.READY;
		this.heading = Heading.randHeading();	
		this.location = new Point(Utilities.rng.nextInt(Simulation.WORLD_SIZE), Utilities.rng.nextInt(Simulation.WORLD_SIZE));
		this.world = sim;
	}
	
	public void adjustPos() {
		while (!(between(location.x, 0, Simulation.WORLD_SIZE) && between(location.y, 0, Simulation.WORLD_SIZE))) {
			if (location.x > Simulation.WORLD_SIZE) {
				location.x -= Simulation.WORLD_SIZE;
			} else if (location.x < 0) {
				location.x += Simulation.WORLD_SIZE;
			}
			if (location.y > Simulation.WORLD_SIZE) {
				location.y -= Simulation.WORLD_SIZE;
			} else if (location.y < 0) {
				location.y += Simulation.WORLD_SIZE;
			}
		}
	}
	
	public boolean between(int n, int lowInc, int highInc) { return (n >= lowInc && n <= highInc); }
	public Color getColor() { return color; }
	public Heading getHeading() { return heading; }
	public Point getLocation() { return location; }
	public Simulation getWorld() { return world; }
	public String getName() { return name; }
	
	public synchronized void setDirection(Heading newHeading) {
		this.heading = newHeading;
	}
	public synchronized AgentState getState() { return state; }
	public synchronized String toString() { return name + ".state = " + state; }
	public synchronized void stop() { state = AgentState.STOPPED; }
	public synchronized boolean isStopped() { return state == AgentState.STOPPED; }
	public synchronized void suspend() { state = AgentState.SUSPENDED; }
	public synchronized boolean isSuspended() { return state == AgentState.SUSPENDED;  }
	
	public synchronized void join() throws InterruptedException {
		if (thread != null) thread.join();
	}
	
	public synchronized void move() { /* override me  */ }
	
	public synchronized void move(int steps) {
		if (heading == Heading.NORTH) {
			location.y -= steps;
		}
		else if (heading == Heading.SOUTH) {
			location.y += steps;
		}
		else if (heading == Heading.EAST) {
			location.x += steps;
		}
		else if (heading == Heading.WEST) {
			location.x -= steps;
		}
		adjustPos();
		world.changed();
	}
	
	public synchronized void resume() {
		if (!isStopped()) {
			notify();
			state = AgentState.RUNNING;
		}
	}

	public void run() {
		thread = Thread.currentThread(); // catch my thread
		while(!isStopped()) {
			state = AgentState.RUNNING;
			update();
			try {
				Thread.sleep(100); // be cooperative
				synchronized(this) {
					while(isSuspended()) { wait(); }
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public abstract void update();
}
