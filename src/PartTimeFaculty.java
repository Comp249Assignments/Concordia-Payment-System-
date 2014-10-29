
public class PartTimeFaculty extends FacultyMember{

	private int hours;
	
	


	private double hourlyRate;
	


	private int bonus;
	public PartTimeFaculty(String id, String name, int hours, double hourlyRate, int numCourses){
		
		super(id, name, hours*hourlyRate, numCourses);
		bonus=0;
		for(int i=0;i<numCourses;i++){
			if(getStudentsPerClass(i)>60)
				bonus+=1000;
			if(getStudentsPerClass(i)>40 && getStudentsPerClass(0)<60)
				bonus+=500;
		}
		setMonthlyPay(hours*hourlyRate+bonus);
		this.hours=hours;
		this.hourlyRate=hourlyRate;
	}
	
	
	
	public double getHourlyRate() {
		return hourlyRate;
	}

	public int getHours() {
		return hours;
	}
	
	public void setHourlyRate(double hourlyRate) {
		this.hourlyRate = hourlyRate;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}
	
	public String paymentStub(){
		return "name:"+getName()+"\nid:"+getID()+"\npayment:"+getMonthlyPay()+"\nhours worked"+hours+"\nhouly rate:"+hourlyRate;
	}
	
}
