public class ExActingHeadOnLeave extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExActingHeadOnLeave(Employee e, LeaveRecord leave){super(e.getName() + " is on leave during " + leave.getLeave() + "!");}
	public ExActingHeadOnLeave(String message){super(message);}
}
