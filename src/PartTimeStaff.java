
public class PartTimeStaff extends StaffMember{
	private int monthlyContractDuration;
	
	public PartTimeStaff(){
		super();
		monthlyContractDuration = 0;
	}
	
	public PartTimeStaff(String id, String name, double contractPay, int monthlyContractDuration){
		super(id, name, (contractPay/monthlyContractDuration));
		this.monthlyContractDuration = monthlyContractDuration;
	}
	
	public void setMonthlyContractDuration(int monthlyContractDuration){
		this.monthlyContractDuration = monthlyContractDuration;
	}
	
	public int getMonthlyContractDuration(){
		return monthlyContractDuration;
	}
}
