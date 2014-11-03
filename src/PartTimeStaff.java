
public class PartTimeStaff extends StaffMember{
	private int monthlyContractDuration;
	private int monthsLeft;
	
	public PartTimeStaff(){
		super();
		monthlyContractDuration = 0;
	}
	
	public PartTimeStaff(int id, String name, double contractPay, int monthlyContractDuration){
		super(id, name, (contractPay/monthlyContractDuration));
		this.monthlyContractDuration = monthlyContractDuration;
		monthsLeft = monthlyContractDuration;
	}
	
	public void setMonthlyContractDuration(int monthlyContractDuration){
		this.monthlyContractDuration = monthlyContractDuration;
	}
	
	public void advanceMonthlyContractDuration(){
		monthlyContractDuration--;
	}
	public int getMonthlyContractDuration(){
		return monthlyContractDuration;
	}
	
	public void setMonthsLeft(int monthsLeft){
		this.monthsLeft = monthsLeft;
	}
	
	public int getMonthsLeft(){
		return monthsLeft;
	}
	public String toString(){
		return (super.toString()+"\nMonths of employment left: "+monthlyContractDuration );
	}
}
