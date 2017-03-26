public class ExInsufficientActingHeadsAppointed extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExInsufficientActingHeadsAppointed(Team team){super("Please name a member to be the acting head of " + team.getName());}
	public ExInsufficientActingHeadsAppointed(String message){super(message);}
}
