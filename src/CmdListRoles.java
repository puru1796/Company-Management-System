public class CmdListRoles implements Command {
	private static Company company;
	private Employee e;
	
	public CmdListRoles(String emp){
		company = Company.getInstance();
	}
	
	@Override
	public void execute(String[] cmdParts){
		try {
			boolean employeeFound = false;
			for(Employee e: company.getEmployeeList()){
				if(e.getName().equals(cmdParts[1]))
					employeeFound = true; //If employee exists, employeeFound is set to true
			}
			if(employeeFound == false) 
				throw new ExEmployeeNotFound(); //Throws exception if employeeFound is still false, i.e. given employee doesn't exist
			e = Employee.searchEmployee(company.getEmployeeList(), cmdParts[1]);
			e.listRoles(); //Lists all roles of given employee. listRoles is a function called from Employee class.
		} catch (ExEmployeeNotFound e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}