
public class PartTimeStaff extends StaffMember{
	private int monthlyContractDuration;
	
	public PartTimeStaff(){
		super();
		monthlyContractDuration = 0;
	}
	
	public PartTimeStaff(String id, double contractPay, int hours, int monthlyContractDuration){
		super(id, (contractPay/monthlyContractDuration), hours);
		this.monthlyContractDuration = monthlyContractDuration;
	}
	
	public void setMonthlyContractDuration(int monthlyContractDuration){
		this.monthlyContractDuration = monthlyContractDuration;
	}
	
	public int getMonthlyContractDuration(){
		return monthlyContractDuration;
	}
}
