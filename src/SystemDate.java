public class SystemDate extends Day{
	private static SystemDate instance;
	private SystemDate(String sDay){
		super(sDay);
	}
	public static SystemDate getInstance(){
		return instance;
	}
	public static void createTheInstance(String sDay){
			instance = new SystemDate(sDay); //Creates new system date
	}
}
