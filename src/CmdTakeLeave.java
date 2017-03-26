import java.util.Collections;
import java.util.ArrayList;

public class CmdTakeLeave extends RecordedCommand {
	private Employee e;
	private Company company = Company.getInstance();
	private Team team;
	private Employee actingHeadEmp;
	private LeaveRecord leave;
	private String[] command;
	private ActingHead actingHead;
	private ArrayList<ActingHead> actingHeadsList;
	private ArrayList<Team> actingHeadsRequired;
	
	
	@Override
	public void execute(String[] cmdParts){
			
			try {
				command = cmdParts.clone(); //command clones cmdParts so it can be used in undo/redo
				e = Employee.searchEmployee(company.getEmployeeList(), cmdParts[1]); 
				leave = new LeaveRecord(new Day(cmdParts[2]), new Day(cmdParts[3]));
				actingHeadsList = new ArrayList<ActingHead>(); //instantiates an ArrayList of acting heads
				actingHeadsRequired = new ArrayList<Team>(); //instantiates and ArrayList of teams that require acting heads
				
				for(LeaveRecord l: e.getLeaveList()){
					if((l.getStartDay().toInteger() <= leave.getStartDay().toInteger() && leave.getStartDay().toInteger() <= l.getEndDay().toInteger())||(l.getStartDay().toInteger() <= leave.getEndDay().toInteger() && leave.getEndDay().toInteger() <= l.getEndDay().toInteger())) 
						throw new ExOverlappingLeave(l); //Throws exception if new leave overlaps with any existing leave of the given employee
				}
				
				int leave_length = Day.dateDifference(leave.getStartDay(), leave.getEndDay());
				if(leave_length > e.getRemainingLeaves())
					throw new ExInsufficientLeaves(e); //Throws exception if employee has insufficient amount of leave left to be able to take the current leave applied for
				
				if(leave.getStartDay().toInteger() < SystemDate.getInstance().toInteger())
					throw new ExLeaveDatePast(); //Throws exception if leave dates have already past, i.e. are prior to the current system date
				
				for(int i=4; i<cmdParts.length; i+=2){
					team = Team.searchTeam(company.getTeamsList(), cmdParts[i]);
					actingHeadEmp = Employee.searchEmployee(company.getEmployeeList(), cmdParts[i+1]); //Instantiates actingHeadEmp to the employee that is to become the acting head, of type Employee
					actingHead = new ActingHead(actingHeadEmp, leave.getStartDay(), leave.getEndDay()); //Instantiates actingHead to the employee that is to become the acting head, by creating new object of type ActingHead
					
					int count = 0;
					ArrayList<Team> teamsHeadOf = new ArrayList<Team>(); //Creates ArrayList of all teams the employee is a head of
					for(Team team: company.getTeamsList()){
						if(team.getHead().getName().equals(cmdParts[1])){
							count++; //Count stores the number of teams the employee is a head of
							teamsHeadOf.add(team);
							Collections.sort(teamsHeadOf);
						}
					}
					if(cmdParts.length<(4+(count*2))){
						Team team = null;
						boolean throwException = false;
						for(Team t: teamsHeadOf){
							boolean teamInCmd = false;
							for(int j = 0; j<cmdParts.length; j++){
								if(t.getName().equals(cmdParts[j]))
									teamInCmd = true; //If team entered in command is part of the teams the employee is a head of, teamInCmd is set to true
							}
							if(teamInCmd == false){
								team = t;
								throwException = true; //If teamInCmd is false, throwException is set to true, i.e. if team entered in command is not part of the teams the employee is a head of
								break;
							}
							
						}
						if(throwException == true){
							throwException = false;
							throw new ExInsufficientActingHeadsAppointed(team); //Throws exception if throwException is true, i.e. if team entered in command is not part of the teams the employee is a head of
						}
					}
					
					boolean employeeFound = false;
					for(Employee e: team.getMembersList()){
						if(e.getName().equals(cmdParts[i+1]))
							employeeFound = true; //If given employee is one of the members of the team, employeeFound is set to true
					}
					
					if(employeeFound == false){
						throw new ExActingHeadNotFound(cmdParts[i+1], team); //Throws exception if employeeFound is false, i.e. the employee is not one of the members of the team
					}
					
					for(LeaveRecord l: actingHeadEmp.getLeaveList()){
						if ((l.getStartDay().toInteger() <= leave.getStartDay().toInteger() && leave.getStartDay().toInteger() <= l.getEndDay().toInteger())||(l.getStartDay().toInteger() <= leave.getEndDay().toInteger() && leave.getEndDay().toInteger() <= l.getEndDay().toInteger())){
							throw new ExActingHeadOnLeave(actingHeadEmp, l); //Throws exception if the acting head appointed is on leave during the period appointed
						}
					}
					
					
					actingHeadsRequired.add(team); //If no exceptions are thrown, the team is added to the list of teams that require acting heads
					actingHeadsList.add(actingHead); //If no exceptions are thrown, actingHead is added to the list of acting heads needed due to the leave applied by the actual head
				}
				
				for(Team t: company.getTeamsList()){
					for(ActingHead ah: t.getActingHeads()){
						if((ah.getActingHead().getName().equals(e.getName())) && ((ah.getActingHeadStartDay().toInteger() <= leave.getStartDay().toInteger() && leave.getStartDay().toInteger() <= ah.getActingHeadEndDay().toInteger()) || (ah.getActingHeadStartDay().toInteger() <= leave.getEndDay().toInteger() && leave.getEndDay().toInteger() <= ah.getActingHeadEndDay().toInteger())))
							throw new ExLeaveDuringActingHead(ah.getActingHead(), t, ah.getActingHeadStartDay(), ah.getActingHeadEndDay()); //Throws exception if leave applied for clashes with the period he employee is the acting head of some team
					}
				}
				
				for(int i =0; i<actingHeadsList.size(); i++){
					actingHeadsRequired.get(i).setActingHead(actingHeadsList.get(i)); //If no exceptions are thrown, all the acting heads in the acting heads list are appointed to the respective teams that require acting heads
				}
				e.addLeave(leave); //If no errors, leave is added to the leave record of the given employee who's taking leave
				Collections.sort(e.getLeaveList());
				
				addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
				clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
				
				System.out.println("Done.");
			} catch (ExOverlappingLeave e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			} catch (ExInsufficientLeaves e){
				System.out.println(e.getMessage());
			} catch (ExLeaveDatePast e){
				System.out.println(e.getMessage());
			} catch (ExActingHeadOnLeave e){
				System.out.println(e.getMessage());
			} catch (ExLeaveDuringActingHead e){
				System.out.println(e.getMessage());
			} catch (ExActingHeadNotFound e){
				System.out.println(e.getMessage());
			} catch (ExInsufficientActingHeadsAppointed e){
				System.out.println(e.getMessage());
			}
	}
	
	@Override
	public void undoMe(){
		e.removeLeave(leave); //undo: leave is removed from the leave record of the given employee
		Collections.sort(e.getLeaveList());
		for(int i=4; i<command.length; i+=2){
			team = Team.searchTeam(company.getTeamsList(), command[i]);
			actingHeadEmp = Employee.searchEmployee(company.getEmployeeList(), command[i+1]);
			actingHead = ActingHead.searchActingHead(team.getActingHeads(), actingHeadEmp);
			team.removeActingHead(actingHead); //undo: acting head is removed from the acting heads record of the given team
		}
		addRedoCommand(this);
	}
	
	@Override
	public void redoMe(){
		e.addLeave(leave); //redo: removed leave is added back to the leave record of the given employee
		Collections.sort(e.getLeaveList());
		for(int i=4; i<command.length; i+=2){
			team = Team.searchTeam(company.getTeamsList(), command[i]);
			actingHeadEmp = Employee.searchEmployee(company.getEmployeeList(), command[i+1]);
			actingHead = new ActingHead(actingHeadEmp, leave.getStartDay(), leave.getEndDay());
			team.setActingHead(actingHead); //redo: removed acting head is added back to the acting heads record of the given team
		}
		addUndoCommand(this);
	}
}
