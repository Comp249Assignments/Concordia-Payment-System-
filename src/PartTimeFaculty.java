
public class PartTimeFaculty extends FacultyMember{
private int hours;
	
	public PartTimeFaculty(){
		super();
		hours = 0;
	}
	
	public void setHours(int hours){
		this.hours = hours;
	}
	
	public int getHours(){
		return hours;
	}
}
