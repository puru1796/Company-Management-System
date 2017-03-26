public class ExAnnualLeavesOutOfRange extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExAnnualLeavesOutOfRange(){super("Annual leaves out of range (0-300)!");}
	public ExAnnualLeavesOutOfRange(String message){super(message);}
}
