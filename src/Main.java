import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner in = new Scanner(System.in);

		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();
		
		Scanner inFile = new Scanner(new File(filepathname));
		
		//The first command in the file must be to set the system date 
		//(eg. "startNewDay|01-Jan-2016"); and it cannot be undone
		String cmdLine1 = inFile.nextLine();
		String[] cmdLine1Parts = cmdLine1.split("\\|");
		System.out.println("\n> "+cmdLine1);
		SystemDate.createTheInstance(cmdLine1Parts[1]); //creates the SystemDate instance
		
		while (inFile.hasNext())		
		{
			String cmdLine = inFile.nextLine().trim();
			
			//Blank lines exist in data file as separators.  Skip them.
			if (cmdLine.equals("")) continue;  

			System.out.println("\n> "+cmdLine);
			
			// split the words in actionLine => create an array of word strings
			// http://stackoverflow.com/questions/5675704/java-string-split-not-returning-the-right-values
			// https://community.oracle.com/thread/2084308
			String[] cmdParts = cmdLine.split("\\|"); 
			
			if(cmdParts[0].equals("startNewDay"))
				(new CmdStartNewDay()).execute(cmdParts); //Sets the SystemDate as a the given new day
			if (cmdParts[0].equals("hire"))
				(new CmdHireEmployee()).execute(cmdParts); //Hires a new employee
			if (cmdParts[0].equals("setupTeam"))
				(new CmdSetupTeam()).execute(cmdParts); //Sets up a new team
			if (cmdParts[0].equals("listEmployees"))
				(new CmdListEmployees()).execute(cmdParts); //Lists all the company's employees
			if (cmdParts[0].equals("listTeams"))
				(new CmdListTeams()).execute(cmdParts); //Lists all the teams
			if (cmdParts[0].equals("takeLeave"))
				(new CmdTakeLeave()).execute(cmdParts); //Employee takes new leave
			if (cmdParts[0].equals("listLeaves") && cmdParts.length==2)
				(new CmdListLeaves(cmdParts[1])).execute(cmdParts); //Lists leaves of particular employee
			if (cmdParts[0].equals("listLeaves") && cmdParts.length==1)
				(new CmdListLeaves()).execute(cmdParts); //Lists all leaves taken in the company
			if (cmdParts[0].equals("addTeamMember"))
				(new CmdAddTeamMember()).execute(cmdParts); //Adds member to team
			if (cmdParts[0].equals("listTeamMembers"))
				(new CmdListTeamMembers()).execute(cmdParts); //Lists all the teams and their team members
			if (cmdParts[0].equals("listRoles"))
				(new CmdListRoles(cmdParts[1])).execute(cmdParts); //Lists all the roles of a given employee
			if (cmdParts[0].equals("undo")) //Undoes last given command
				if (RecordedCommand.getUL().isEmpty()){
					System.out.println("Nothing to undo."); //If there is no command to undo
				}
				else
					RecordedCommand.undoOneCommand();
			if (cmdParts[0].equals("redo")) //Redoes last undoed command
				if(RecordedCommand.getRL().isEmpty()){
					System.out.println("Nothing to redo."); //If there is no command to redo
				}
				else
					RecordedCommand.redoOneCommand();
		}
		inFile.close();
			
		in.close();
	}
}