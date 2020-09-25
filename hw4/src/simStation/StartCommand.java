package simStation;

import mvc.*;

/*
 * Edit History 
 * 3/27 - Created
 * 
 */

public class StartCommand extends Command {

	public StartCommand(Model model) {
		super(model);
	}

	@Override
	public void execute() {
		Simulation sim = (Simulation) model;
		sim.start();
	}

}
