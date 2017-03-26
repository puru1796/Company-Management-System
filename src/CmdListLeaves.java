public class CmdListLeaves implements Command {
	private Employee e;
	Company company;
	
	public CmdListLeaves(String emp){
		company = Company.getInstance();
		e = Employee.searchEmployee(company.getEmployeeList(), emp);
	}
	
	public CmdListLeaves(){
		company = Company.getInstance();
	}
	
	@Override
	public void execute(String[] cmdParts){
		if(e!=null){ //If employee is provided in command, lists all the leaves of given employee
			e = Employee.searchEmployee(company.getEmployeeList(), cmdParts[1]);
			if(e.getLeaveList().isEmpty()){
				System.out.println("No leave record"); //If the employee has no leave record
			}
			for(LeaveRecord l: e.getLeaveList()){
					l.printLeave();
			}
		}
		else{
			for(Employee e: company.getEmployeeList()){ //If no employee is provided in command, lists all the leaves of all employees
				System.out.println(e.getName() + ":");
				if(e.getLeaveList().size() == 0)
					System.out.println("No leave record"); //If the employee has no leave record
				for(LeaveRecord l: e.getLeaveList()){
					l.printLeave();
				}
			}
		}
	}
}