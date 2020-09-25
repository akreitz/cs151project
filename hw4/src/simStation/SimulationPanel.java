package simStation;

import java.awt.*;
import javax.swing.*;
import mvc.*;
import plague.*;
import prisoner.*;
import flocking.*;

/*
 * Edit History 
 * 3/27 - Created
 * 
 */

public class SimulationPanel extends AppPanel {

		private JButton start;
		private JButton suspend;
		private JButton resume;
		private JButton stop;
		private JButton stats;
		
		public SimulationPanel(AppFactory factory) {
			super(factory);
			SimulationView view = new SimulationView((Simulation) model);
			this.setLayout(new GridLayout(1, 2));
			
			start = new JButton("Start");
			start.addActionListener(this);
			
			suspend = new JButton("Suspend");
			suspend.addActionListener(this);
			
			resume = new JButton("Resume");
			resume.addActionListener(this);
			
			stop = new JButton("Stop");
			stop.addActionListener(this);
			
			stats = new JButton("Stats");
			stats.addActionListener(this);
			
			JPanel buttonPanel = new JPanel();
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
			
			Dimension minSize = new Dimension(5, 10);
			Dimension prefSize = new Dimension(5, 10);
			Dimension maxSize = new Dimension(20, 10);
			
			buttonPanel.add(new Box.Filler(minSize, prefSize, maxSize));
			buttonPanel.add(start);
			start.setAlignmentX(CENTER_ALIGNMENT);
			buttonPanel.add(new Box.Filler(minSize, prefSize, maxSize));
			buttonPanel.add(suspend);
			suspend.setAlignmentX(CENTER_ALIGNMENT);
			buttonPanel.add(new Box.Filler(minSize, prefSize, maxSize));
			buttonPanel.add(resume);
			resume.setAlignmentX(CENTER_ALIGNMENT);
			buttonPanel.add(new Box.Filler(minSize, prefSize, maxSize));
			buttonPanel.add(stop);
			stop.setAlignmentX(CENTER_ALIGNMENT);
			buttonPanel.add(new Box.Filler(minSize, prefSize, maxSize));
			buttonPanel.add(stats);
			stats.setAlignmentX(CENTER_ALIGNMENT);
			buttonPanel.add(new Box.Filler(minSize, prefSize, maxSize));
			
			add(buttonPanel, "West");
			add(view, "East");
		}
		
		public static void main(String[] args) {
			
//			AppFactory factory = new SimulationFactory();
//			AppPanel panel = new SimulationPanel(factory);
//			panel.display();
			
			
//			AppPanel panel = new SimulationPanel(new PlagueFactory());
//			panel.display();
			
//			AppPanel panel = new SimulationPanel(new PrisonersDilemmaFactory());
//			panel.display();
			
			AppPanel panel = new SimulationPanel(new FlockingFactory());
			panel.display();
			
		}
	}
