import java.util.ArrayList;
import java.util.Collections;

public class Team implements Comparable<Team> {
	private String teamName;
	private Employee head;
	private ArrayList<ActingHead> actingHeads;
	private Day dateSetup;
	private ArrayList<Employee> team_members;
	
	public Team(String n, Employee hd){
		teamName = n;
		head = hd;
		dateSetup = SystemDate.getInstance().clone();
		team_members = new ArrayList<Employee>(); //Instantiates by creating new ArrayList of employees
		actingHeads = new ArrayList<ActingHead>(); //Instantiates by creating new ArrayList of acting heads
		team_members.add(hd); //Adds head of the given team to the list of members of that team
		Collections.sort(team_members);
	}
	
	public ArrayList<Employee> getMembersList(){
		return team_members;
	}
	
	public void addMember(Employee e){
		team_members.add(e);
		Collections.sort(team_members);
	}
	
	public void removeMember(Employee e){
		team_members.remove(e);
		Collections.sort(team_members);
	}
	
	public String getName(){
		return teamName;
	}
	
	public Employee getHead(){
		return head;
	}
	
	public void setActingHead(ActingHead ah){
		actingHeads.add(ah); //Sets acting head of given team
		Collections.sort(actingHeads);
	}
	
	public ArrayList<ActingHead> getActingHeads(){
		return actingHeads; //Returns list of acting heads of given team
	}
	
	public void removeActingHead(ActingHead ah){
		actingHeads.remove(ah); //Removes given acting head of given team
		Collections.sort(actingHeads);
	}
	
	public static Team searchTeam(ArrayList<Team> list, String nameToSearch){
		for(Team t: list){
			if(t.getName().equals(nameToSearch))
				return t;
		}
		return null;
	}
	
	public static void list(ArrayList<Team> list){ //Prints list of teams with team name, leader and setup date
		System.out.printf("%-30s%-10s%-13s\n", "Team Name", "Leader", "Setup Date");
		for(Team t: list){
			System.out.printf("%-30s%-10s%-13s\n", t.teamName, t.head.getName(), t.dateSetup.toString());
		}
	}
	
	@Override
	public int compareTo(Team another){ //Compares two teams
		return (this.teamName.compareTo(another.teamName));
	}
}
