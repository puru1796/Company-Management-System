import java.util.Collections;

public class CmdAddTeamMember extends RecordedCommand {
	private Team team;
	private Company company;
	private Employee e;
	
	public CmdAddTeamMember(){
		company = Company.getInstance();
	}
	
	@Override
	public void execute(String[] cmdParts){
		try {
			if(cmdParts.length<3){
				throw new ExInsufficientArguments(); //Throws exception if insufficient arguments are provided in the command
			}
			boolean employeeFound = false;
			for(Employee e: company.getEmployeeList()){
				if(e.getName().equals(cmdParts[2]))
					employeeFound = true; //If employee exists, employeeFound is set to true
			}
			if(employeeFound == false)
				throw new ExEmployeeNotFound(); //Throws exception if employee isn't found, i.e. if employeeFound is still set to false
			boolean teamFound = false;
			for(Team t: company.getTeamsList()){
				if(t.getName().equals(cmdParts[1]))
					teamFound = true; //If team exists, teamFound is set to true
			}
			if(teamFound == false)
				throw new ExTeamNotFound(); //Throws exception if team isn't found, i.e. if teamFound is still set to false
	
			team = Team.searchTeam(company.getTeamsList(), cmdParts[1]);
			e = Employee.searchEmployee(company.getEmployeeList(), cmdParts[2]);
			for(Employee emp: team.getMembersList())	{
				if(emp.equals(e)){
					throw new ExMemberExists(); //Throws exception if employee is already a member
				}
			}

			team.addMember(e); //Adds given employee as a member in the members record of the given team
			e.addRole(team); //Adds given team as a role in the roles record of the given employee
					
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
				
			System.out.println("Done.");
		} catch (ExInsufficientArguments e){
			System.out.println(e.getMessage());
		} catch (ExMemberExists e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ExEmployeeNotFound e){
			System.out.println(e.getMessage());
		} catch (ExTeamNotFound e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void undoMe(){
		team.removeMember(e); //undo: removes member from team record of members
		e.removeRole(team); //undo: removes role from employee record of roles
		addRedoCommand(this);
	}
	
	@Override
	public void redoMe(){
		team.addMember(e); //redo: adds removed member back to team record of members
		e.addRole(team); //redo: adds removed role back to employee record of roles
		addUndoCommand(this);
	}
}
