
public class TA extends Student{
	private int hours;
	
	public TA(){
		super();
		hours = 0;
	}
	
	public TA(int id, String name, double hourlyPay, int monthsUntilGraduation, int hours){
		super(id, name, (hourlyPay*hours), false, monthsUntilGraduation);
		this.hours = hours;
	}
	
	public void setHours(int hours){
		this.hours = hours;
	}
	
	public int getHours(){
		return hours;
	}
	public String toString(){
		return(super.toString()+"\nHours per month: "+hours)
	}
}
