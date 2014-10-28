
public class PermanentStaff extends StaffMember{
	
	public PermanentStaff(){
		super();
	}
	
	public PermanentStaff(String id, String name, double annualSalary){
		super(id, name, (annualSalary/12));
	}
}
