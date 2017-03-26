public class CmdListTeamMembers implements Command {
	private static Company company;
	
	public CmdListTeamMembers(){
		company = Company.getInstance();
	}
	
	@Override
	public void execute(String[] cmdParts){
		company.listTeamMembers(); //List members of each team. listTeamMembers is a function called from Company class
	}
}
