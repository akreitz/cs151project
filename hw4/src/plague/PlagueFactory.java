package plague;

import mvc.*;
import simStation.*;

/*
 * Edit History 
 * 4/1  - Created
 * 
 */

public class PlagueFactory extends SimulationFactory {
	
	@Override
	public Model makeModel() { return new PlagueSim(); }
	
	@Override
	public String about() {
		return "PlagueSim, CS 151 SP20 Project, Alex Kreitz";
	}

}
