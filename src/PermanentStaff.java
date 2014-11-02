
public class PermanentStaff extends StaffMember{
	
	public PermanentStaff(){
		super();
	}
	
	public PermanentStaff(int id, String name, double annualSalary){
		super(id, name, (annualSalary/12));
	}
}
