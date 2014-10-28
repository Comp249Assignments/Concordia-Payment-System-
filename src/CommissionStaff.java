
public class CommissionStaff extends PartTimeStaff{
	private String location;
	private double salesMade;
	
	public CommissionStaff(){
		super();
		location = "nowhere";
		salesMade = 0;
	}
	
	public CommissionStaff(String id, double contractPay, int hours, int monthlyContractDuration, String location){
		super(id, contractPay, hours, monthlyContractDuration);
		this.location = location;
		salesMade = 0;
	}
	
	public void addSales(double newSale){
		salesMade += newSale;
	}
	
	public void setLocation(String location){
		this.location = location;
	}
	
	public void setSalesMade(double salesMade){
		this.salesMade = salesMade;
	}
	
	public String getLocation(){
		return location;
	}
	
	public double getSalesMade(){
		return salesMade;
	}
}