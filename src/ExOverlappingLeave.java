public class ExOverlappingLeave extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExOverlappingLeave(LeaveRecord existing_l){super("Overlap with leave from " + existing_l.getLeave() + "!");}
	public ExOverlappingLeave(String message){super(message);}
}
