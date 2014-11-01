
public class ConcordiaPerson {
	private int id;
	private String name;
	private double monthlyPay;
	
	public ConcordiaPerson(){
		id = "0000000";
		name = "";
		monthlyPay = 0;
	}
	
	public ConcordiaPerson(String id, String name, double monthlyPay){
		this.id = id;
		this.name = name;
		this.monthlyPay = monthlyPay;
	}
	
	public void setID(String id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setMonthlyPay(double monthlyPay){
		this.monthlyPay = monthlyPay;
	}
	
	public String getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public double getMonthlyPay(){
		return monthlyPay;
	}
}
