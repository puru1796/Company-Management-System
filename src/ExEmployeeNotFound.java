public class ExEmployeeNotFound extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ExEmployeeNotFound(){super("Employee not found!");}
	public ExEmployeeNotFound(String message){super(message);}
}
