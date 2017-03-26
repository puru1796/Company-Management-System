public class ExMemberExists extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExMemberExists(){super("Employee has already joined the team!");}
	public ExMemberExists(String message){super(message);}
}
