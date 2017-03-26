public class ExLeaveDuringActingHead extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExLeaveDuringActingHead(Employee e, Team team, Day from, Day to){super("Cannot take leave. " + e.getName() + " is the acting head of " + team.getName() + " during " + from.toString() + " to " + to.toString() + "!");}
	public ExLeaveDuringActingHead(String message){super(message);}
}
