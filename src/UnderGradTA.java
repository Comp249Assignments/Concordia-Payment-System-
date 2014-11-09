
public class UnderGradTA extends TA{
	public UnderGradTA(){
		super();
	}
	//Constructor for the TA who will hold the base hourly salary for undergrad TAs
	public UnderGradTA(double hourlyPay){
		super(0, "", hourlyPay, 1000000, 1);
	}
	public UnderGradTA(int id, String name, double hourlyPay, int monthsUntilGraduation, int hours){
		super(id, name, hourlyPay, monthsUntilGraduation, hours);
	}
}
