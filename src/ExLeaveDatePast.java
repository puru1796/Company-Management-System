public class ExLeaveDatePast extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExLeaveDatePast(){super("Wrong date. System date is already " + SystemDate.getInstance().toString() + "!");}
	public ExLeaveDatePast(String message){super(message);}
}
