package simStation;

import java.util.*;
import mvc.*;

/*
 * Edit History 
 * 3/27 - Created
 * 
 * 4/1  - changed from array to arraylist for agents
 * 
 * 4/3  - added getNeighbors implementation
 * 
 * 4/4  - forgot changed() flags, added
 * 
 */

public class Simulation extends Model {

	public static final int WORLD_SIZE = 250;
	public static final int NUM_AGENTS = 100;
	public static final int SIZE = 4;
	
	protected ArrayList<Agent> agents;
	protected int clock = 0;
	protected Timer timer;
	
	public Simulation() {
		agents = new ArrayList<>();
	}
	

	public ArrayList<Agent> getNeighbors(Agent current, int radius) {
		ArrayList<Agent> neighbors = new ArrayList<>();

		for (Agent a : agents) {
			if (Math.hypot(current.getLocation().x - a.getLocation().x, current.getLocation().y - a.getLocation().y) <= radius) {
				neighbors.add(a);
			}
		}
		
		neighbors.remove(current);
		
		return neighbors;
	}
	
	public Agent getNeighbor(Agent current, int radius) {
		for (Agent a : agents) {
			if (Math.hypot(current.getLocation().x - a.getLocation().x, current.getLocation().y - a.getLocation().y) <= radius) {
				return a;
			}
		}
		return null;
	}
	
	public ArrayList<Agent> getAgents() {
		return agents;
	}
	
	public int getClock() {
		return clock;
	}
	
	public void start() {
		startTimer();
		populate();
		for (Agent a : agents) {
			Thread thread = new Thread(a);
			thread.start();
		}
		changed();
	}
	
	public void suspend() {
		for (Agent a : agents) {
			a.suspend();
		}
	}
	
	public void resume() {
		for (Agent a : agents) {
			a.resume();
		}
	}
	
	public void stop() {
		for (Agent a : agents) {
			a.stop();
		}
		stopTimer();
		changed();
	}
	
	public String[] stats() {
		String[] stats = new String[2];
		stats[0] = "#agents: " + agents.size();
		stats[1] = "clock: " + clock;
		return stats;
	}
	
	public void populate() { /* override me  */ }

	private void startTimer() {
		timer = new Timer();
		timer.scheduleAtFixedRate(new ClockUpdater(), 1000, 1000);
    }

    private void stopTimer() {
    	timer.cancel();
	  	timer.purge();
    }

 	private class ClockUpdater extends TimerTask {
 		public void run() {
 			clock++;
      	}
 	}
}
