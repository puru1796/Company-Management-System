public class ExActingHeadNotFound extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExActingHeadNotFound(String s, Team team){super("Employee (" + s + ") not found for " + team.getName() + "!");}
	public ExActingHeadNotFound(String message){super(message);}
}
