
public class PermanentStaff extends StaffMember{
	
	public PermanentStaff(){
		super();
	}
	
	public PermanentStaff(String id, double annualSalary, int hours){
		super(id, (annualSalary/12), hours);
	}
}
