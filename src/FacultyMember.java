import java.io.Serializable;
import java.util.*;
public class FacultyMember extends ConcordiaPerson{
	private String[] coursesTaught;
	private int[] studentsPerClass;
	
	public FacultyMember(){}

	
	public FacultyMember(int id, String name, double monthlyPay, int numCourses, String[] classNames, int[] studentsPerClass){
		super(id,name,monthlyPay);
		coursesTaught=new String[numCourses];
		this.studentsPerClass=new int[numCourses];
		for(int i=0; i<classNames.length; i++)
		{
			coursesTaught[i]=classNames[i];
			this.studentsPerClass[i]=studentsPerClass[i];
		}
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
	
	public String toString(){
		String buffer="";
		for (int i=0;i<coursesTaught.length; i++){
		buffer=buffer+"Course: " +coursesTaught[i]+" Students attending: "+studentsPerClass[i] + "\n";
		}
		return (super.toString()+"\n"+buffer);
	}
	

	
	
}
