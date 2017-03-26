import java.util.ArrayList;

abstract class RecordedCommand implements Command{
	/* 2 abstract methods for subclasses to implement */
	public abstract void undoMe();
	public abstract void redoMe();
	
	/* undolist and redolist; */
	public static ArrayList<RecordedCommand> undoList=new ArrayList<>();
	public static ArrayList<RecordedCommand> redoList=new ArrayList<>();
	
	public static ArrayList<RecordedCommand> getUL(){
		return undoList;
	}
	
	public static ArrayList<RecordedCommand> getRL(){
		return redoList;
	}
	
	/* adding command to the list */
	protected static void addUndoCommand(RecordedCommand cmd) {undoList.add(cmd);}
	protected static void addRedoCommand(RecordedCommand cmd) {redoList.add(cmd);}
	
	/* clear redo list*/
	protected static void clearRedoList() {redoList.clear();}
	
	/* carry out undo/redo */
	public static void undoOneCommand() {undoList.remove(undoList.size()-1).undoMe();}
	public static void redoOneCommand() {redoList.remove(redoList.size()-1).redoMe();}

}