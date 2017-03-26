
public class ExTeamExists extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExTeamExists(){super("Team already exists!");}
	public ExTeamExists(String message){super(message);}
}
