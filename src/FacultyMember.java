import java.io.Serializable;
import java.util.*;
public class FacultyMember extends ConcordiaPerson implements Serializable {
	private String[] coursesTaught;
	private int[] studentsPerClass;
	
	
	
	public FacultyMember(String id, String name, double monthlyPay, int numCourses, String[] classNames, int[] studentsPerClass){
		super(id,monthlyPay,name);
		coursesTaught=new String[numCourses];
		studentsPerClass=new int[numCourses];
		coursesTaught=classNames;
		this.studentsPerClass=studentsPerClass;
	}
	//getter methods
	public String getCoursesTaught(int courseNum){
		return coursesTaught[courseNum];
	}
	
	public int getStudentsPerClass(int courseNum){
		return studentsPerClass[courseNum];
	}
	
	
	public  int getNumCourses(){
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
