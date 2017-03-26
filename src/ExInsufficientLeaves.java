public class ExInsufficientLeaves extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExInsufficientLeaves(Employee e){super("Insufficient balance. " + e.getRemainingLeaves() + " days left only!");}
	public ExInsufficientLeaves(String message){super(message);}
}
