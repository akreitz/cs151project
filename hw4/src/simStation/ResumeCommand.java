package simStation;

import mvc.*;

/*
 * Edit History 
 * 3/27 - Created
 * 
 */

public class ResumeCommand extends Command {

	public ResumeCommand(Model model) {
		super(model);
	}

	@Override
	public void execute() {
		Simulation sim = (Simulation) model;
		sim.resume();
	}

}
