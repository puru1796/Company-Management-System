import java.util.ArrayList;
import java.util.Collections;

public class CmdHireEmployee extends RecordedCommand {
	private Employee e;
	private int leave;
	private static Company company;
	
	public CmdHireEmployee(){
		company = Company.getInstance();
	}
	
	@Override
	public void execute(String[] cmdParts){ //If error free, employee is hired 
		try {
			if(cmdParts.length<3){
				throw new ExInsufficientArguments(); //Throws exception if insufficient arguments are provided in command
			}
			leave = Integer.parseInt(cmdParts[2]);
			
			for(Employee e: company.getEmployeeList()){
				if (e.getName().equals(cmdParts[1]))
					throw new ExEmployeeExists(); //Throws exception if employee already exists
			}
			if(leave>300){
				throw new ExAnnualLeavesOutOfRange(); //Throws exception if leaves taken are out of permitted range
			}
			e = new Employee(cmdParts[1], leave);
			company.addEmployee(e); //Adds employee to company record of employees
			Collections.sort(company.getEmployeeList());
			
			addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
			clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
			
			System.out.println("Done.");
		} catch (ExInsufficientArguments e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (ExEmployeeExists e){
			System.out.println(e.getMessage());
		} catch (ExAnnualLeavesOutOfRange e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void undoMe(){
		company.removeEmployee(e); //undo: removes employee from company record of employees
		Collections.sort(company.getEmployeeList());
		addRedoCommand(this);
	}
	
	@Override
	public void redoMe(){
		company.addEmployee(e); //redo: adds removed employee back to company record of employees
		Collections.sort(company.getEmployeeList());
		addUndoCommand(this);
	}
}
