import java.io.Serializable;


public class PartTimeFaculty extends FacultyMember implements Serializable{

	private int hours;
	private double hourlyRate;
	private int bonus;
	private int monthsLeft;
	public PartTimeFaculty(){
		
	}
	public PartTimeFaculty(int id, String name, int hours, double hourlyRate, int numCourses, String[] classNames, int[] studentsPerClass, int bonus, int monthsLeft){
		super(id, name, hours*hourlyRate+bonus, numCourses, classNames, studentsPerClass);
		this.bonus=bonus;
		this.hours=hours;
		this.hourlyRate=hourlyRate;
		this.monthsLeft=monthsLeft;
	}
	
	
	
	
	//getters
	public double getHourlyRate() {
		return hourlyRate;
	}

	public int getHours() {
		return hours;
	}
	
	public int getBonus(){
		return bonus;
	}
	
	//setters
	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
	
	public String toString(){
		return (super.toString+"\nHours: "+hours+"\nHourly wage: "+hourlyRate+"\nBonus: "+bonus);
	}
	
	public int getMonthsLeft(){
		return this.monthsLeft;
	}
	public void upwardMonthsLeft(){
		this.monthsLeft++;
	}
	public void setMonthsleft(int i){
		this.monthsLeft=i;
	}
}
