public class CmdListTeams implements Command {
	private static Company company;
	
	public CmdListTeams(){
		company = Company.getInstance();
	}
	
	@Override
	public void execute(String[] cmdParts){
		company.listTeams(); //Lists all the teams created. listTeams is a function called from Company class
	}
}
