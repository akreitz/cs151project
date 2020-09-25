package prisoner;

import mvc.*;
import simStation.*;

/*
 * Edit History (Keven Lam)
 * 
 * 4/11 - Created PrisonersDilemmaFactory
 * 
 */

public class PrisonersDilemmaFactory extends SimulationFactory {
	
	public Model makeModel() {
		return new PrisonersDilemma();
	}
}
