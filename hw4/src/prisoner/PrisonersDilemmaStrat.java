package prisoner;

import mvc.Utilities;

/*
 * Edit History (Keven Lam)
 * 
 * 4/11 - Created PrisonersDilemmaStrat.
 * 
 * 4/11 - Created AlwaysCooperate class.
 * 
 * 4/11 - Created AlwaysCheat class.
 * 
 * 4/11 - Created Chaos class.
 * 
 * 4/11 - Created TitForTat class.
 * 
 */

public abstract class PrisonersDilemmaStrat {
	protected Prisoner prisoner;
	
	public PrisonersDilemmaStrat(Prisoner holder) {
		prisoner = holder;
	}
	
	public abstract boolean execute();
}

class AlwaysCooperate extends PrisonersDilemmaStrat {
	
	public AlwaysCooperate(Prisoner holder) {
		super(holder);
	}
	
	public boolean execute() {
		return true;
	}
}

class AlwaysCheat extends PrisonersDilemmaStrat {
	
	public AlwaysCheat(Prisoner holder) {
		super(holder);
	}
	
	public boolean execute() {
		return false;
	}
}

class Chaos extends PrisonersDilemmaStrat {

	public Chaos(Prisoner holder) {
		super(holder);
	}
	
	public boolean execute() {
		return Utilities.rng.nextBoolean();
	}	
}

class TitForTat extends PrisonersDilemmaStrat {

	public TitForTat(Prisoner holder) {
		super(holder);
	}
	
	@Override
	public boolean execute() {
		return prisoner.wasPreviouslyCoOp();
	}
}