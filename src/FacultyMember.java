import java.util.*;
public class FacultyMember extends ConcordiaPerson{
	private String[] coursesTaught;
	private int[] studentsPerClass;
	
	public FacultyMember(String id, String name, double monthlyPay, int numCourses){
		super(id, name, monthlyPay);
		coursesTaught=new String[numCourses];
		studentsPerClass=new int[numCourses];
		//setting the contents of the arrays
		for(int i=0;i<numCourses;i++){
			Scanner kb=new Scanner(System.in);
			System.out.print("please put in the number of students in this class");
			studentsPerClass[i]=kb.nextInt();
			kb.nextLine();
			System.out.print("please put in the class chosen");
			coursesTaught[i]=kb.nextLine();
		}
	}
	//getter methods
	public String[] getCoursesTaught(){
		return coursesTaught;
	}
	
	public int[] getStudentsPerClass(){
		return studentsPerClass;
	}
	
	public int getStudentsPerClass(int courseNum){
		return studentsPerClass[courseNum];
	}
	
	public int getNumCourses(){
		return coursesTaught.length;
	}
	public int getNumStudents(){
		return studentsPerClass.length;
	}
	
	//setter methods
	public void setCoursesTaught(int courseNum, String courseName){
			this.coursesTaught[courseNum]=courseName;
	}
	
	public void setStudentsPerClass(int courseNum,int numStudents){
		this.studentsPerClass[courseNum]=numStudents;
	}
	
}
