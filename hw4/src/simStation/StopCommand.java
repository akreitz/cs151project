package simStation;

import mvc.*;

/*
 * Edit History 
 * 3/27 - Created
 * 
 */

public class StopCommand extends Command {

	public StopCommand(Model model) {
		super(model);
	}

	@Override
	public void execute() {
		Simulation sim = (Simulation) model;
		sim.stop();
	}

}
