package prisoner;

import simStation.*;
import mvc.*;

/*
 * Edit History (Keven Lam)
 * 
 * 4/11 - Created Prisoner.
 */
public class Prisoner extends Agent {
	
	private int fitness;
	private PrisonersDilemmaStrat strategy;
	private boolean previousCoOpStatus;
	
	public Prisoner(String name, Simulation world) {
		super(name, world);
		fitness = 0;
		strategy = null;
		previousCoOpStatus = false;
	}

	public boolean cooperate() {
		return strategy.execute();
	}
	
	public void setStrategy(PrisonersDilemmaStrat strat) {
		strategy = strat;
	}
	
	public PrisonersDilemmaStrat getStrategy() {
		return strategy;
	}
	
	public void increaseFitness(int amt) {
		fitness += amt;
	}
	
	public int getFitness() {
		return fitness;
	}
	
	public void setPreviousCoOpStatus(boolean status) {
		previousCoOpStatus = status;
	}
	
	public boolean wasPreviouslyCoOp() {
		return previousCoOpStatus;
	}
	
	@Override
	public synchronized void update() {
		Prisoner neighbor = (Prisoner)getWorld().getNeighbor(this, PrisonersDilemma.NEIGHBOR_RADIUS);
		
		if (neighbor != null) {
			// Both cooperate
			if (this.cooperate() && neighbor.cooperate()) {
				this.increaseFitness(3);
				neighbor.increaseFitness(3);
				this.setPreviousCoOpStatus(true);
				neighbor.setPreviousCoOpStatus(true);
			}
			// 1 cheats, 2 cooperates 
			else if (!this.cooperate() && neighbor.cooperate()) {
				this.increaseFitness(5);
				neighbor.increaseFitness(0);
				this.setPreviousCoOpStatus(true);
				neighbor.setPreviousCoOpStatus(false);
			}
			// 1 cooperates, 2 cheats
			else if (this.cooperate() && !neighbor.cooperate()) {
				this.increaseFitness(0);
				neighbor.increaseFitness(5);
				this.setPreviousCoOpStatus(false);
				neighbor.setPreviousCoOpStatus(true);
			}
			// Both cheat
			else if (!this.cooperate() && !neighbor.cooperate()) {
				this.increaseFitness(1);
				neighbor.increaseFitness(1);
				this.setPreviousCoOpStatus(false);
				neighbor.setPreviousCoOpStatus(false);
			}
		}
		super.setDirection(Heading.randHeading());
		super.move(Utilities.rng.nextInt(PrisonersDilemma.MOVE_SPEED));
		
		//System.out.println(super.getName() + " - " + fitness);
	}
}