
public class ConcordiaPerson extends ConcordiaUniversity {
	private int id;
	private String name;
	private double monthlyPay;
	
	public ConcordiaPerson(){
		id = 0;
		name = "";
		monthlyPay = 0;
	}
	
	public ConcordiaPerson(int id, String name, double monthlyPay){
		this.id = id;
		this.name = name;
		this.monthlyPay = monthlyPay;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setMonthlyPay(double monthlyPay){
		this.monthlyPay = monthlyPay;
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public double getMonthlyPay(){
		return monthlyPay;
	}
	public String toString(){
		return ("Name:"+this.name+"\nID:"+this.id+"Monthly salary:"+this.monthlyPay);
	}
}
