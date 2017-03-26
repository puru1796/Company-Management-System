import java.util.Collections;

public class CmdSetupTeam extends RecordedCommand {
	private Team team;
	private Company company;
	private Employee e;
	
	public CmdSetupTeam(){
		company = Company.getInstance();
	}
	
	@Override
	public void execute(String[] cmdParts){
		try {
			if(cmdParts.length<3){
				throw new ExInsufficientArguments(); //Throws exception if insufficient arguments are provided in the command
			}
			boolean employee_exists = false;
			for(Employee e: company.getEmployeeList()){
				if(cmdParts[2].equals(e.getName()))
						employee_exists = true; //If given employee exists, employee_exists is set to true
			}
			if(employee_exists == false)
				throw new ExEmployeeNotFound(); //Throws exception is employee_exists is still set to false, i.e. the given employee which is to be the head of the team doesn't exist
			for(Team t: company.getTeamsList()){
				if(t.getName().equals(cmdParts[1]))
					throw new ExTeamExists(); //Throws exception if team already exists
			}
			e = Employee.searchEmployee(company.getEmployeeList(), cmdParts[2]);
			team = new Team(cmdParts[1], e);
			company.addTeam(team); //Adds team to teams record of the company
			team.getHead().addRole(team); //Adds team to the roles record of the head of this newly created team
			
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
			
			System.out.println("Done.");
		} catch (ExInsufficientArguments e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ExEmployeeNotFound e){
			System.out.println(e.getMessage());
		} catch (ExTeamExists e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void undoMe(){
		company.removeTeam(team); //undo: removes team from teams record of the company
		e.removeRole(team); //undo: removes team from roles record of the head of this team
		Collections.sort(company.getTeamsList());
		addRedoCommand(this);
	}
	
	@Override
	public void redoMe(){
		company.addTeam(team); //redo: adds back removed team to teams record of the company
		e.addRole(team); //redo: adds back removed team to roles record of the head of this team
		Collections.sort(company.getTeamsList());
		addUndoCommand(this);
	}
}
