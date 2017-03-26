import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Company {
	private ArrayList<Employee> allEmployee;
	private static ArrayList<Team> allTeams;
	
	private static Company instance = new Company();
	
	private Company(){
		allEmployee = new ArrayList<Employee>();
		allTeams = new ArrayList<Team>();
	}
	
	public ArrayList<Employee> getEmployeeList(){
		return allEmployee;
	}
	
	public ArrayList<Team> getTeamsList(){
		return allTeams;
	}
	
	public  void listTeams(){
		Team.list(allTeams);
	}
	
	public void listEmployees(){
		Employee.list(allEmployee);
	}
	
	public void listTeamMembers(){ //Lists all the teams and their members
		for(Team t: this.getTeamsList()){
			System.out.println(t.getName()+":");
			for(Employee e: t.getMembersList()){
				System.out.print(e.getName());
				if(t.getHead().equals(e)){
					System.out.print(" (Head of Team)");
				}
				System.out.println();
				
			}
			if(!t.getActingHeads().isEmpty()){ //If team has acting heads, prints the acting heads too
				System.out.println("Acting heads:");
				for(ActingHead acthead: t.getActingHeads()){
					System.out.println(acthead.getActingHeadStartDay() + " to " + acthead.getActingHeadEndDay() + ": " + acthead.getActingHead().getName());
				}
			}
			
			System.out.println();
		}
	}
	
	
	public static Company getInstance(){
		return instance;
	}
	
	public Employee createEmployee(String name, int leave){
		Employee e = new Employee(name, leave);
		allEmployee.add(e); //Creates and adds new employee to company's record of employees
		Collections.sort(allEmployee);
		return e;
	}
	
	public Team createTeam(String team_name, String leader){
		Employee e = Employee.searchEmployee(allEmployee, leader);
		Team t = new Team(team_name, e);
		allTeams.add(t); //Creates and adds new team to company's record of teams
		Collections.sort(allTeams);
		return t;
	}
	
	public void addEmployee(Employee e){
		allEmployee.add(e);
		Collections.sort(allEmployee);
	}
	
	public void removeEmployee(Employee e){
		allEmployee.remove(e);
	}
	
	public void addTeam(Team t){
		allTeams.add(t);
		Collections.sort(allTeams);
	}
	
	public void removeTeam(Team t){
		allTeams.remove(t);
	}
}