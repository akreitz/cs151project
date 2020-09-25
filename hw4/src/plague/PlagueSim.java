package plague;

import simStation.*;
import java.util.*;

import mvc.Utilities;

/*
 * Edit History 
 * 4/1  - Created
 * 
 * 4/10 - updated getNeighbors()
 * 		- fixed percentage display in stats
 */

public class PlagueSim extends Simulation {
	
	public static final int INITIAL_INFECTED = 2;
	public static final int VIRULENCE = 5;
	
	private int infected = INITIAL_INFECTED;
	private String[] plagueStats;
	
	public PlagueSim() {
		super();
		plagueStats = new String[3];
		plagueStats[0] = super.stats()[0];
		plagueStats[1] = super.stats()[1];
		plagueStats[2] = "";
	}
	
	public int numInfected() {
		return infected;
	}
	
	public void newInfection() {
		infected++;
	}
	
	public double calcInfected() {
		return ( (double) infected / agents.size() ) * 100;
	}
	
	public ArrayList<PlagueAgent> getNeighbors (PlagueAgent current, int radius) {
		ArrayList<PlagueAgent> neighbors = new ArrayList<>();

		for (Agent a : agents) {
			PlagueAgent temp = (PlagueAgent) a;
			if (Math.hypot(current.getLocation().x - a.getLocation().x, current.getLocation().y - a.getLocation().y) <= radius) {
				neighbors.add(temp);
			}
		}
		
		neighbors.remove(current);
		
		return neighbors;
	}
	
	@Override
	public void populate() {
		for (int i = 0; i < Simulation.NUM_AGENTS; i++) {
			if (i < INITIAL_INFECTED) {
				agents.add(new PlagueAgent("Agent " + i, this, true));
			} else {
				agents.add(new PlagueAgent("Agent " + i, this));
			}
		}
	}
	@Override
	public String[] stats() {

		String[] stats = super.stats();
		plagueStats[0] = stats[0];
		plagueStats[1] = stats[1];
		plagueStats[2] = "%infected: " + String.format("%.2f", calcInfected());
		
		return plagueStats;
	}

}
