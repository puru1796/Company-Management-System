import java.util.ArrayList;
import java.util.Collections;

public class Employee implements Comparable<Employee>{
	private String name;
	private int yrLeavesEntitled; //The amount of leave the employee is entitled to
	private int remaining_leaves; //The amount of leave the employee has remaining after deducting all of the employee's leaves
	private ArrayList<LeaveRecord> leaves;
	private ArrayList<Team> roles;
	private Company company;
	
	public Employee(String n, int yle){
		name = n;
		yrLeavesEntitled = yle;
		remaining_leaves = yle;
		leaves = new ArrayList<LeaveRecord>();
		roles = new ArrayList<Team>();
		company = Company.getInstance();
	}
	
	public int getRemainingLeaves(){
		return remaining_leaves;
	}
	
	public void addLeave(LeaveRecord l){
		leaves.add(l);
		remaining_leaves -= ((l.getEndDay().toInteger() - l.getStartDay().toInteger())+1); //Deducts the duration of the new leave from remaining_leaves
	}
	
	public void removeLeave(LeaveRecord l){
		leaves.remove(l);
		remaining_leaves += ((l.getEndDay().toInteger() - l.getStartDay().toInteger())+1); //Adds back the duration of the leave removed to remaining_leaves
	}
	
	public void addRole(Team t){
		roles.add(t);
		Collections.sort(roles);
	}
	
	public void removeRole(Team t){
		roles.remove(t);
		Collections.sort(roles);
	}
	
	public void listRoles(){ //Lists roles of given employee
		if(roles.isEmpty())
			System.out.println("No role"); //If employee has no roles
		else
			for(Team t: roles){
				System.out.print(t.getName());
				if(t.getHead().equals(this)){
					System.out.print(" (Head of Team)");
				}
				System.out.println();
			}
		}
	
	public ArrayList<LeaveRecord> getLeaveList(){
		return leaves;
	}
	
	public String getName(){
		return name;
	}
	
	public int getLeaves(){
		return yrLeavesEntitled;
	}
	
	public static Employee searchEmployee(ArrayList<Employee> list, String nameToSearch){
		for(Employee e: list){
			if(e.getName().equals(nameToSearch))
				return e;
		}
		return null;
	}
	
	public static void list(ArrayList<Employee> list){ //Lists all employees existing
		for(Employee e: list){
			System.out.println(e.getName() + " (Entitled Annual Leaves: " + e.getLeaves() + " days)");
		}
	}
	
	@Override
	public int compareTo(Employee another){ //Compares two employees
		return (this.name.compareTo(another.name));
	}
}
