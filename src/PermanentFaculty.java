import java.io.Serializable;

public class PermanentFaculty extends FacultyMember implements Serializable {
		public PermanentFaculty(){}
		public PermanentFaculty(int id, String name, double monthlyPay, int numCourses,String[] classNames, int[] studentsPerClass){
			super(id, name, monthlyPay, numCourses, classNames, studentsPerClass);
		}
	

}
