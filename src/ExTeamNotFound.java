public class ExTeamNotFound extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExTeamNotFound(){super("Team not found!");}
	public ExTeamNotFound(String message){super(message);}
}