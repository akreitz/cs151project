package simStation;

import mvc.*;

/*
 * Edit History 
 * 3/27 - Created
 * 
 */

public class StatsCommand extends Command {

	public StatsCommand(Model model) {
		super(model);
	}

	@Override
	public void execute() {
		Simulation sim = (Simulation) model;
		String[] stats = sim.stats();
		Utilities.inform(stats);
	}

}
