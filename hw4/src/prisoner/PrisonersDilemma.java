package prisoner;

import simStation.*;

import java.util.*;

import mvc.*;

/*
 * Edit History (Keven Lam)
 * 
 * 4/11 - Created PrisonersDilemma.
 */

public class PrisonersDilemma extends Simulation {
	
	public static int NEIGHBOR_RADIUS = 10;
	public static int MOVE_SPEED = 20;
	public static int NUM_PRISONERS = NUM_AGENTS - (NUM_AGENTS % 4);
	
	private String[] stats;
	
	public PrisonersDilemma() {
		super();
		stats = new String[7];
		stats[0] = super.stats()[0];
		stats[1] = super.stats()[1];
		stats[2] = "Average Fitness per Strategy:";
		stats[3] = "Always Cooperate: 0";
		stats[4] = "Always Cheat: 0";
		stats[5] = "Randomly Cooperate: 0";
		stats[6] = "Tit for Tat: 0";
	}
	
	@Override
	public void populate() {
		List<Agent> agents = super.getAgents();
		for (int i = 0; i < NUM_PRISONERS; i++) {
			int ith = i % 4;
			Prisoner p = new Prisoner("Prisoner " + (i + 1), this);
			if (ith == 0) {
				p.setStrategy(new AlwaysCooperate(p));
				agents.add(p);
			}
			else if (ith == 1) {
				p.setStrategy(new AlwaysCheat(p));
				agents.add(p);
			}
			else if (ith == 2) {
				p.setStrategy(new Chaos(p));
				agents.add(p);
			}
			else if (ith == 3) {
				p.setStrategy(new TitForTat(p));
				agents.add(p);
			}
		}
	}
	
	@Override
	public String[] stats() {
		int clock = super.getClock();
		String[] simStats = super.stats();
		
		stats[0] = simStats[0];
		stats[1] = simStats[1];
		
		//if (clock % 100 == 0 && clock != 0) {
			stats[3] = "Always Cooperate: "+ this.getAverageFitness(new AlwaysCooperate(null));
			stats[4] = "Always Cheat: " + this.getAverageFitness(new AlwaysCheat(null));
			stats[5] = "Randomly Cooperate: " + this.getAverageFitness(new Chaos(null));
			stats[6] = "Tit for Tat: " + this.getAverageFitness(new TitForTat(null));
		//}
		return stats;
	}
	
	public double getAverageFitness(PrisonersDilemmaStrat strat) {
		double total = 0;
		int count = 0;
		for (Agent a : super.getAgents()) {
			Prisoner p = (Prisoner) a;
			if (p.getStrategy().getClass() == strat.getClass()) {
				total += p.getFitness();
				count++;
			}
		}
		return total / count;
	}
}