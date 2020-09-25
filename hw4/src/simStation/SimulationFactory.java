package simStation;

import mvc.*;

/*
 * Edit History 
 * 3/27 - Created
 * 
 * 4/8  - altered to accomomdate SimFactory Interface
 * 
 */

public class SimulationFactory implements AppFactory {

	@Override
	public Model makeModel() { return new Simulation(); }

	@Override
	public String[] getEditCommands() {
		return new String[] { "Start", "Suspend", "Resume", "Stop", "Stats" };
	}

	@Override
	public Command makeEditCommand(Model model, String type) {
		switch(type) {
			case "Start":
				return new StartCommand(model);
			case "Suspend":
				return new SuspendCommand(model);
			case "Resume":
				return new ResumeCommand(model);
			case "Stop":
				return new StopCommand(model);
			case "Stats":
				return new StatsCommand(model);
		}
		return null;
	}

	@Override
	public String getTitle() {
		return "SimStation";
	}

	@Override
	public String[] getHelp() {
		return new String[] { 
							  "Start: starts the simulation\n",
							  "Suspend: suspends the simulation (can resume)\n",
							  "Resume: resumes the simulation\n",
							  "Stop: stops the simulation (kills all threads)\n",
							  "Stats: (simulation specific)" 
							 };
	}

	@Override
	public String about() {
		return "SimStation, CS 151 SP20 Project, Alex Kreitz";
	}

}
