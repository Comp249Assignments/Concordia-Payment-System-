
public class ConcordiaPerson {
	private String id;
	private double monthlyPay;
	private int hours;
	
	public ConcordiaPerson(){
		id = "0000000";
		monthlyPay = 0;
		hours = 0;
	}
	
	public ConcordiaPerson(String id, double monthlyPay, int hours){
		this.id = id;
		this.monthlyPay = monthlyPay;
		this.hours = hours;
	}
	
	public void setID(String id){
		this.id = id;
	}
	
	public void setMonthlyPay(double monthlyPay){
		this.monthlyPay = monthlyPay;
	}
	
	public void setHours(int hours){
		this.hours = hours;
	}
	
	public String getID(){
		return id;
	}
	
	public double getMonthlyPay(){
		return monthlyPay;
	}
	
	public int getHours(){
		return hours;
	}
}