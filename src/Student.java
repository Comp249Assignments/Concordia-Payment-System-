
public class Student extends ConcordiaPerson{
<<<<<<< HEAD
	private boolean alumni;
	private int monthsUntilGraduation;
	
	public Student(){
		super();
		monthsUntilGraduation = 0;
		alumni = true;
	}
	
	public Student(int id, String name, double monthlyPay, boolean alumni, int monthsUntilGraduation)
	{
		super(id, name, monthlyPay);
		this.alumni = alumni;
		this.monthsUntilGraduation = monthsUntilGraduation;
	}
	
	public boolean isAlumni(){
		return alumni;
	}
	
	public void setAlumni(boolean alumni){
		this.alumni = alumni;
	}
	
	public boolean qualifiesForTA(){
		if(alumni)
			return false;
		else
			return true;
	}
=======
public student(){}
>>>>>>> origin/master
}
