public class LeaveRecord implements Comparable<LeaveRecord>{
	private Day from;
	private Day to;
	Company company;
	
	public LeaveRecord(Day aFrom, Day aTo){ //LeaveRecord stores the start day and end day of the leave
		company = Company.getInstance();
		from = aFrom;
		to = aTo;
	}
	
	public void printLeave(){
		System.out.println(this.from.toString() + " to " + this.to.toString());
	}
	
	public String getLeave(){ //Returns a requested leave in string format
		return (this.from.toString() + " to " + this.to.toString());
	}
	
	public Day getStartDay(){
		return from;
	}
	
	public Day getEndDay(){
		return to;
	}
	
	@Override
	public int compareTo(LeaveRecord another){ //Compares two given leaves
		if (this.from.toInteger()==another.from.toInteger())
			return 0;
		else if (this.from.toInteger()>another.from.toInteger())
			return 1;
		else
			return -1;
	}
}
