package simStation;

import mvc.*;

/*
 * Edit History 
 * 3/27 - Created
 * 
 */

public enum Heading {
	NORTH, EAST, SOUTH, WEST;
	
	public static Heading randHeading() {
		
		switch(Utilities.rng.nextInt(4)) {
		case 0:
			return NORTH;
		case 1:
			return EAST;
		case 2:
			return SOUTH;
		case 3:
			return WEST;
		}
		
		return null;
	}
}
