public class CmdStartNewDay extends RecordedCommand{
	private SystemDate old_date;
	private String new_date;
	
	public CmdStartNewDay(){
		old_date = SystemDate.getInstance(); //Returns old date of System to old_date upon use of constructor
	}
	
	@Override
	public void execute(String[] cmdParts){
		new_date = cmdParts[1];
		SystemDate.createTheInstance(new_date); //New date is created by calling static function createTheInstance from the SystemDate class
		
		addUndoCommand(this); //<====== store this command (addUndoCommand is implemented in RecordedCommand.java)
		clearRedoList(); //<====== There maybe some commands stored in the redo list.  Clear them.
		
		System.out.println("Done.");
	}
	
	@Override
	public void undoMe(){
		SystemDate.createTheInstance(old_date.toString()); //undo: sets back old date as the System Date
		addRedoCommand(this);
	}
	
	@Override
	public void redoMe(){
		SystemDate.createTheInstance(new_date); //redo: makes the new date the System Date again
		addUndoCommand(this);
	}
}
