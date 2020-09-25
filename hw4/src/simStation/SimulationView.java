package simStation;

import mvc.*;
import java.awt.*;

/*
 * Edit History 
 * 3/27 - Created
 * 
 * 4/1  - added logic to check for initialized array
 *      - changed implementation of for loop to use ArrayList features
 * 	   
 * 4/10 - moved all color logic into Agent class
 */

public class SimulationView extends View{
	
	public SimulationView(Model model) {
		super(model);
	}

	public void paintComponent(Graphics gc) {
		
		Simulation sim = (Simulation) model;
		Color oldColor = gc.getColor();
		
		for (Agent a : sim.getAgents()) {
			if (a != null) {
				gc.setColor(a.getColor());
				gc.fillOval(a.getLocation().x, a.getLocation().y, Simulation.SIZE, Simulation.SIZE);
			} else {
				break;
			}
		}
		gc.setColor(oldColor);
	}
}
