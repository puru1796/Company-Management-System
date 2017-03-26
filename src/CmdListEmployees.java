public class CmdListEmployees implements Command {
	private static Company company;
	
	public CmdListEmployees(){
		company = Company.getInstance();
	}
	
	@Override
	public void execute(String[] cmdParts){
		company.listEmployees(); //Lists the employees in a company
	}
}
