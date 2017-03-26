public class ExActingHeadNotMember extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExActingHeadNotMember(Team team){super("Please name a member to be the acting head of " + team.getName());}
	public ExActingHeadNotMember(String message){super(message);}
}
