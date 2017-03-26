import java.util.ArrayList;

//Class ActingHead stores Employee, and start and end day of period the employee is the acting head for

public class ActingHead implements Comparable<ActingHead> {
	private Employee e;
	private Day from;
	private Day to;
	
	public ActingHead(Employee emp, Day f, Day t){
		e = emp;
		from = f;
		to = t;
	}
	
	public Employee getActingHead(){
		return e;
	}
	
	public Day getActingHeadStartDay(){
		return from;
	}
	
	public Day getActingHeadEndDay(){
		return to;
	}
	
	public static ActingHead searchActingHead(ArrayList<ActingHead> list, Employee e){ //Returns the ActingHead searched for
		for(ActingHead ah: list){
			if(ah.getActingHead().getName().equals(e.getName()))
				return ah;
		}
		return null;
	}
	
	@Override
	public int compareTo(ActingHead another){ //Compares ActingHeads
		if(this.from.toInteger()==another.from.toInteger())
			return 0;
		else if (this.from.toInteger()>another.from.toInteger())
			return 1;
		else
			return -1;
	}
}
