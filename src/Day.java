import java.io.*;
import java.util.Scanner;
import java.util.*;

public class Day implements Cloneable {
	
	private int year;
	private int month;
	private int day;
	private static final String MonthNames = "JanFebMarcAprMayJunJulAugSepOctNovDec";
	
	//Constructor
	public Day(int y, int m, int d) {
		this.year=y;
		this.month=m;
		this.day=d;		
	}
	
	public Day(String sDay){ //Day Constructor using a String parameter
		this.set(sDay); 
	}
	
	public void set(String sDay){
		String[] sDayParts = sDay.split("-");
		this.day = Integer.parseInt(sDayParts[0]);
		this.year = Integer.parseInt(sDayParts[2]);
		this.month = MonthNames.indexOf(sDayParts[1])/3+1;
	}
	
	// check if a given year is a leap year
	static public boolean isLeapYear(int y)
	{
		if (y%400==0)
			return true;
		else if (y%100==0)
			return false;
		else if (y%4==0)
			return true;
		else
			return false;
	}
	
	// check if y,m,d valid
	static public boolean valid(int y, int m, int d)
	{
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}

	// Return a string for the day like dd MMM yyyy
	
	@Override
	public String toString() {
		return day + "-" + MonthNames.substring((month-1)*3, (month)*3) + "-" + year;
	}
	
	public int toInteger(){
		return (year*10000)+(month*100)+(day); //Converts date to an integer for comparison purposes
	}
	
	public static int dateDifference(Day start, Day end){ //Calculates difference between two dates using in-built Date class
		Date startDate = new Date(start.year, start.month, start.day);
		Date endDate = new Date(end.year, end.month, end.day);
		long startDateTime = startDate.getTime(); //Gets milliseconds passed since 1 January 1970 in long format
		long endDateTime = endDate.getTime();
		long diff = endDateTime - startDateTime; //Calculates difference in milliseconds between the two dates in long format
		int difference = (int) diff/(1000*60*60*24); //Converts milliseconds to number of days
		return difference;
	}
	
	@Override
	public Day clone(){ //Clones Day to another Day object reference
		Day copy = null;
		try {
			copy = (Day) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return copy;
	}
}