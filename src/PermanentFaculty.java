import java.io.Serializable;

public class PermanentFaculty extends FacultyMember implements Serializable {
	
		public PermanentFaculty(String id, String name, double monthlyPay, int numCourses,String[] classNames, int[] studentsPerClass){
			super(id, name, monthlyPay, numCourses, classNames, studentsPerClass);
		}
	

}
