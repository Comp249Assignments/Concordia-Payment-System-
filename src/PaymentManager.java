import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.*;

public class PaymentManager {
	
	static int load=0;
	private static int studentID=1000000, gradTAID=2000000, permanentFacultyID=3000000, partTimeFacultyID=4000000,
			permanentStaffID=5000000, partTimeStaffID=6000000, commissionedStaffID=7000000, underGradTAID = 8000000;
	private static ArrayList<Integer> badID=new ArrayList();
	private static	File file=new File("Concordia database.txt");
	private static Scanner scanner = new Scanner(System.in);
	private static double underGradTAPay;
	private static ArrayList<ConcordiaPerson> concordiaPerson=new ArrayList();
	private static ArrayList<ArrayList> arrayCeption=new ArrayList();
	private static ArrayList<Student> students = new ArrayList();
	private static ArrayList<GradTA> gradTAs = new ArrayList();
	private static ArrayList<UnderGradTA> underGradTAs = new ArrayList();
	private static ArrayList<PermanentFaculty> permanentFaculty = new ArrayList();
	private static ArrayList<PartTimeFaculty> partTimeFaculty = new ArrayList();
	private static ArrayList<PermanentStaff> permanentStaff = new ArrayList();
	private static ArrayList<PartTimeStaff> partTimeStaff = new ArrayList();
	private static ArrayList<CommissionStaff> commissionStaff = new ArrayList();
	public static void main(String[] args) {
		startUp();
		scanner.close();
	}
	//each TA has an individual salary so we will have to put this in the TA class
	public static void startUp(){
		System.out.println("Welcome!");
		load();
		action();
	}
	
//The method to load the system (returns 0 if there is no system to load)
	public static int load(){
		ArrayList<Integer> ID=new ArrayList();
		int index=0;
		boolean stop=false;
		try{
			//making two input streams "idIn" is for retrieving the ID of the person and using an id
			//cataloging system (that we will have to implement) determines to what group he belongs to
			//"in2" is used to return the actual constructor and casts it into the correct group by
			//using "idIn"
			ObjectInputStream idIn=new ObjectInputStream(new FileInputStream(file));
			ObjectInputStream in2=new ObjectInputStream(new FileInputStream(file));

			
			//wont stop until IOException is recieved (meaning we hit the end of the file)
			while(!stop){
				//retrieving the id and inputing it into an ArrayList ID
				ID.add(((ConcordiaPerson) idIn.readObject()).getID());
				
				//students 1000000-2000000
				if((ID.get(index)>=1000000 && ID.get(index)<2000000)||ID.get(index)==0){
					students.add((Student) in2.readObject());
					//if they were deleted in the previous session
					if(ID.get(index)==0){
						badID.add(studentID);
					}
					studentID++;
					load++;
					
				}
				//grad ta 2000000-3000000
				if((ID.get(index)>=2000000 && ID.get(index)<3000000)||ID.get(index)==1){
					gradTAs.add((GradTA) in2.readObject());
					if(ID.get(index)==1){
						badID.add(gradTAID);
					}
					gradTAID++;
					load++;
				}
				
				//permanentFaculty 3000000-4000000
				if((ID.get(index)>=3000000 && ID.get(index)<4000000)||ID.get(index)==2){
					permanentFaculty.add((PermanentFaculty) in2.readObject());
					if(ID.get(index)==2){
						badID.add(permanentFacultyID);
					}
					permanentFacultyID++;
					load++;
				}
				
				//partTimeFaculty 4000000-5000000
				if((ID.get(index)>=4000000 && ID.get(index)<5000000)||ID.get(index)==3){
					partTimeFaculty.add((PartTimeFaculty) in2.readObject());
					if(ID.get(index)==3){
						badID.add(partTimeFacultyID);
					}
					partTimeFacultyID++;
					load++;
				}			
				
				//permanentStaff 5000000-6000000
				if((ID.get(index)>=5000000 && ID.get(index)<6000000)||ID.get(index)==4){
					permanentStaff.add((PermanentStaff) in2.readObject());
					if(ID.get(index)==4){
						badID.add(permanentStaffID);
					}
					permanentStaffID++;
					load++;
				}
				
				//partTimeStaff 6000000-7000000
				if((ID.get(index)>=6000000 && ID.get(index)<7000000)||ID.get(index)==5){
				partTimeStaff.add((PartTimeStaff) in2.readObject());
					if(ID.get(index)==5){
						badID.add(partTimeStaffID);
					}
					partTimeStaffID++;
					load++;
				}
				
				//commissionStaff 7000000-8000000
				if((ID.get(index)>=7000000 && ID.get(index)<8000000)||ID.get(index)==6){
					commissionStaff.add((CommissionStaff) in2.readObject());

					if(ID.get(index)==6){
						badID.add(commissionedStaffID);
					}
					commissionedStaffID++;
					load++;
				}
				
				//undergrad ta 8000000-9000000
				if((ID.get(index)>=8000000 && ID.get(index)<9000000)||ID.get(index)==7){
					underGradTAs.add((UnderGradTA) in2.readObject());
					if(ID.get(index)==1){
						badID.add(gradTAID);
					}
					gradTAID++;
					load++;
				}
			
				
			
			index++;
		}
			idIn.close();
			
			in2.close();
			}
			catch(FileNotFoundException e){
				return 0;
			}
			catch(IOException e){
				//when file ends stops the while loop
				stop=true;
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		
			System.out.println(load);
		return load;
	}
	
	
//overwrites the old save contents with the new ones
	public static void writeToSave(ArrayList<ArrayList> t){
		try{
			file.delete();
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file,false));
			for(int i=0;i<t.size();i++)
				for(int j=0; j<t.get(i).size();j++)
					out.writeObject(t.get(i).get(j));
			out.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	//method to save the system. 
	public static void save(){
		arrayCeption.clear();
			arrayCeption.add(students);
		
			arrayCeption.add(gradTAs);
			
			arrayCeption.add(permanentFaculty);

			arrayCeption.add(partTimeFaculty);

			arrayCeption.add(permanentStaff);
			
			arrayCeption.add(partTimeStaff);

			arrayCeption.add(commissionStaff);
			
			arrayCeption.add(underGradTAs);

		writeToSave(arrayCeption);
	}
	
	
	public static void action(){
		int action;
		boolean stop = false;
		while(!stop){
			save();
			System.out.println("What do you wish to do? \n"+
					"1: Add individuals to the system\n"+
					"2: Update attributes of individuals\n"+
					"3: Delete individuals\n"+
					"4: Search for someone in the system\n"+
					"5: Prepare paystubs\n"+
					"6: Calculate the amount of money that needs to be paid to all Concordia employees\n"+
					"7: Advance to next month\n"+
					"8: List all the members in any group\n"+
					"9: Exit");
			action = getInputRange(1, 9);
			switch(action){
				case 1:
					addIndividual();
					break;
				case 2:
					update();
					break;
				case 3:
					deleteIndividual();
					break;
				case 4:
					search();
					break;
				case 5:
					paystubs();
					break;
				case 6:
					System.out.println("The amount that must be paid to Concordia employees this month is $" + totalPay());
					break;
				case 7:
					advanceToNextMonth();
					break;
				case 8:
					list();
					break;
				case 9:
					exit();
					stop = true;
					break;
			}
		}
	}
	
	//method to add individuals to the system
	public static void addIndividual(){
		int action;
		boolean stop = false;
		while(!stop){
			System.out.println("Would you like to \n" +
					"1: Add a Student or TA\n" +
					"2: Add a Faculty Member\n" +
					"3: Add a Staff Member\n" +
					"4: Go back");
			action=getInputRange(1,4);
			switch (action){
				case 1:
					addStudent();
					break;
				case 2:
					addFacultyMember();
					break;
				case 3:
					addStaffMember();
					break;
				default:
					stop = true;
			}		
		}
	}
	
	//Method to add a Student or TA
	public static void addStudent(){
		int action, duration,id,itsBadID, hours;
		String name = "", input;
		boolean alumni = false;
		double pay;
		System.out.println("Is the student a \n" +
				"1: Regular student/alumni\n" +
				"2: TA\n" +
				"3: Go back");
		action=getInputRange(1,3);
		
		if(action < 3){
			System.out.println("Please input the student's name");
 			name=scanner.next();
		}
		switch(action){
			case 1:
				System.out.println("Is this an alumni? \n" +
						"1: Yes\n" +
						"2: No");
				if(getInputRange(1,2)==1){
					alumni = true;
					duration=0;
				}
				else{
					alumni = false;
					System.out.println("How many months until this student graduates?");
					duration = getInputInt();
				}
				
				itsBadID=isItBadID(studentID);
				if(itsBadID==0){
					id=studentID++;
			 		students.add(new Student(id, name, 0, alumni, duration));
				}
				else{
					id=itsBadID;
					students.add(itsBadID-1000000, new Student(id, name, 0, alumni, duration));
				}
				break;
			case 2:
				System.out.println("Is this a(n) \n" +
						"1: Grad TA\n" +
						"2: Undergrad TA");
				action=getInputRange(1,2);
				
				System.out.println("How many months until this student graduates?");
				duration = getInputInt();
				
				System.out.println("How many hours per month does this TA work for?");
				hours = getInputInt();
				
				System.out.println("How much does this TA get paid per hour?");
				pay = getInputDouble();
				
				if(action==1){
					itsBadID=isItBadID(gradTAID);
					if(itsBadID==0){
						id=gradTAID++;
				 		gradTAs.add(new GradTA(id, name, pay, duration, hours));
					}
					else{
						id=itsBadID;
						gradTAs.add(itsBadID-2000000, new GradTA(id, name, pay, duration, hours));
					}
				}
				else{
					itsBadID=isItBadID(underGradTAID);
					if(itsBadID==0){
						id=underGradTAID++;
				 		underGradTAs.add(new UnderGradTA(id, name, pay, duration, hours));
					}
					else{
						id=itsBadID;
						underGradTAs.add(itsBadID-8000000, new UnderGradTA(id, name, pay, duration, hours));
					}
				}
				
				break;
		}
	}
	

	//Method for adding a faculty member
	public static void addFacultyMember(){
		int action, itsBadID, ID, monthlyPay,numCourses,hours,monthsLeft,bonus=0;
		String name="";
		String [] classNames;
		int [] studentsPerClass;
		
		double hourlyRate;
		
		System.out.println("Is the Faculty Member a\n" +
				"1: Permanent Faculty Member\n" +
				"2: Part time Faculty Member\n"+
				"3: Go back");
		action=getInputRange(1,3);
		if(action<3){
			//input name
		 	System.out.println("Please input this faculty member's name");
			name=scanner.next();	
		}
		switch (action){
			case 1:
				
				//generate ID
				itsBadID=isItBadID(permanentFacultyID);
				if(itsBadID!=0){
					permanentFaculty.remove(itsBadID-3000000);
					ID=itsBadID;
				}
				else
					ID=permanentFacultyID++;

		 		//input numClasses
		 		System.out.println("Please input the number of courses this faculty member will be teaching");
		 		numCourses=getInputInt();
		 		
		 		//input monthlyPay
		 		System.out.println("Please input this faculty member's monthly salary in dollars");
		 		monthlyPay=getInputInt();
		 		
		 		//input class names
		 		classNames=new String[numCourses];
		 		for(int i=0;i<numCourses;i++){
		 			System.out.println("Please put in the name of class number "+(i+1));
		 			classNames[i]=scanner.next();
		 		}
		 		
		 		//input students per class
		 		studentsPerClass=new int[numCourses];
		 		for(int i=0;i<numCourses;i++){
		 			System.out.println("please put in the number of students in class "+ classNames[i]);
		 			studentsPerClass[i]=getInputRange(0,999999999);
		 			}
		 		
		 		//PermenantFaculty permenantFaculty=
		 		if (itsBadID!=0){
			 		permanentFaculty.add(itsBadID-3000000,new PermanentFaculty(ID, name, monthlyPay, numCourses, classNames, studentsPerClass));

		 		}
			 		else
		 			permanentFaculty.add(new PermanentFaculty(ID, name, monthlyPay, numCourses, classNames, studentsPerClass));

		 		break;
			case 2:
		 		//generate ID
				itsBadID=isItBadID(partTimeFacultyID);
				if(itsBadID!=0){
					ID=itsBadID;
					partTimeFaculty.remove(itsBadID-4000000);
				}
				else
					ID=partTimeFacultyID++;

					
				//input months to be hired for
		 		System.out.println("Please input for how long will this faculty member be hired");
		 		monthsLeft=getInputInt();
		 		
		 		//input hourlyRate
		 		System.out.println("Please input the amount the faculty member will be paid per hour in dollars");
		 		hourlyRate=getInputDouble();
		 		
		 		//input amount of hours
		 		System.out.println("please input the amount of hours the faculty member will be teaching for");
		 		hours=getInputInt();
			 
		 		//input numClasses
		 		System.out.println("Please input the amount of classes this faculty member is teaching");
		 		numCourses=getInputRange(1,2);
		 		
		 		//input class names
		 		classNames=new String[numCourses];
		 		for(int i=0;i<numCourses;i++){
		 			System.out.println("Please put in the name of class number "+(i+1));
		 			classNames[i]=scanner.next();
		 		}
		 		
		 		//input students per class
		 		studentsPerClass=new int[numCourses];
		 		for(int i=0;i<numCourses;i++){
		 			System.out.println("please put in the number of students in class "+ classNames[i]);
		 			studentsPerClass[i]=getInputRange(0,999999999);
		 			}
		 		//calculating bonus
		 		for (int i=0; i<numCourses;i++){
		 			bonus+=((studentsPerClass[i]>=40 && studentsPerClass[i]<=60)? 500:0);
		 			bonus+=((studentsPerClass[i]>60)? 1000:0);		 		}
		 		if(itsBadID==0)
		 		partTimeFaculty.add(new PartTimeFaculty(ID, name, hours, hourlyRate, numCourses, classNames, studentsPerClass, bonus,monthsLeft));
		 		else
			 		partTimeFaculty.add(itsBadID-4000000,new PartTimeFaculty(ID, name, hours, hourlyRate, numCourses, classNames, studentsPerClass, bonus, monthsLeft));

		 		break;
		}
	 }
	
	
	//Method for adding any kind of staff member
	public static void addStaffMember(){
		int action, duration,id,itsBadID;
		String name = "", input;
		double pay;
		System.out.println("Is the staff a \n" +
				"1: Part time staff member\n" +
				"2: Permanent staff member\n" +
				"3: Go back");
		action=getInputRange(1,3);
		
		if(action < 3){
			System.out.println("Please input this staff member's name");
 			name=scanner.next();
		}
 		
	switch(action){
			case 1:
				System.out.println("Is this a commissioned staff member? \n" +
						"1: Yes\n" +
						"2: No");
				action=getInputRange(1,2);
		 		
		 		System.out.println("How many months has this person been hired for?");
		 		duration = getInputInt();
		 		
		 		System.out.println("How much will this person be paid over the duration of their contract?");
		 		pay = getInputDouble();
				switch(action){
					case 1:
						System.out.println("Where at Concordia does this person work?");
				 		input = scanner.next();
				 		//commissionStaff.add(new CommissionStaff(id, name, pay, duration, input));
						break;
					case 2:
						//partTimeStaff.add(new PartTimeStaff(id, name, pay, duration));
						break;
				}
				break;
			case 2:
				System.out.println("Please input this staff member's yearly salary");
		 		pay = getInputDouble();
		 	//	permanentStaff.add(new PermanentStaff(id, name, pay));
				break;
		}
	}
	
	//Method to update the attributes of individuals (might use the search method in order to find that individual?)
	public static void update(){
		int action, id, employeeType;
		boolean stop = false;
		System.out.println("We must search for the person to delete first");
		do{
			System.out.println("First we need to find the individual");
			id=search();
		}while(id<10);
		employeeType = id/1000000;
		switch(employeeType){
			case 1:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Alumni status\n" +
						"3: Months until graduation");
				action = getInputRange(1,3);
				if(action==1){
					System.out.println("Please input the new name");
					students.get(id%1000000).setName(scanner.next());
				}
				else if(action==2){
					System.out.println("Input\n"+
							"1: Is alumni"+
							"2: Is not alumni");
					action=getInputRange(1,2);
					if(action==1)
						students.get(id%1000000).setAlumni(true);
					else
						students.get(id%1000000).setAlumni(false);
				}
				else{
					System.out.println("Please input the number of months until graduation for this student");
					students.get(id%1000000).setMonthsUntilGraduation(getInputRange(1,1000000));
				}
				break;
			case 2:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Alumni status\n" +
						"3: Months until graduation"+
						"4: Hours worked per month"+
						"5: Hourly pay");
				action = getInputRange(1,5);
				if(action==1){
					System.out.println("Please input the new name");
					gradTAs.get(id%1000000).setName(scanner.next());
				}
				else if(action==2){
					System.out.println("Input\n"+
							"1: Is alumni"+
							"2: Is not alumni");
					action=getInputRange(1,2);
					if(action==1)
						gradTAs.get(id%1000000).setAlumni(true);
					else
						gradTAs.get(id%1000000).setAlumni(false);
				}
				else if(action==3){
					System.out.println("Please input the number of months until graduation for this student");
					gradTAs.get(id%1000000).setMonthsUntilGraduation(getInputRange(1,1000000));
				}
				else if(action==4){
					System.out.println("Please input the numbers of hours this TA works per month");
					int hours = getInputRange(0, 1000000);
					double hourlyPay = (gradTAs.get(id%1000000).getMonthlyPay()/gradTAs.get(id%1000000).getHours());
					gradTAs.get(id%1000000).setHours(hours);
					gradTAs.get(id%1000000).setMonthlyPay(hours*hourlyPay);
				}
				else{
					System.out.println("Please input the hourly pay for this TA");
					gradTAs.get(id%1000000).setMonthlyPay(getInputDouble()*gradTAs.get(id%1000000).getHours());
				}
				break;
			case 3:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Monthly pay\n" +
						"3: Classes"+
						"4: Students per class");
				action = getInputRange(1,5);
				if(action==1){
					System.out.println("Please input the new name");
					permanentFaculty.get(id%1000000).setName(scanner.next());
				}
				else if(action==2){
					System.out.println("Please input the monthly pay for this person");
					permanentFaculty.get(id%1000000).setMonthlyPay(getInputDouble());
				}
				else if(action==3){
					for(int i=0; i<permanentFaculty.get(id%1000000).getNumCourses(); i++){
						System.out.print("Input the name of class " + (i+1));
						permanentFaculty.get(id%1000000).setCoursesTaught(i, scanner.next());
					}
				}
				else{
					for(int i=0; i<permanentFaculty.get(id%1000000).getNumCourses(); i++){
						System.out.print("Input the number of students in class " + permanentFaculty.get(id%1000000).getCoursesTaught(i));
						permanentFaculty.get(id%1000000).setStudentsPerClass(i, getInputRange(0, 1000));
					}
				}
				break;
			case 4:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Hourly pay\n" +
						"3: Hours"+
						"4: Classes"+
						"5: Students per class"+
						"6: Remaining months of employment");
				action = getInputRange(1,6);
				if(action==1){
					System.out.println("Please input the new name");
					partTimeFaculty.get(id%1000000).setName(scanner.next());
				}
				else if(action==2){
					int maxStudents=0, bonus = 0;;
					System.out.println("Please input the hourly pay for this person");
					double pay = getInputDouble();
					for(int i=0; i<partTimeFaculty.get(id%1000000).getNumCourses(); i++){
						if(partTimeFaculty.get(id%1000000).getStudentsPerClass(i)>maxStudents)
							maxStudents = partTimeFaculty.get(id%1000000).getStudentsPerClass(i);
					}
					if(maxStudents>60){
						bonus=1000;
					}
					else if(maxStudents>=40){
						bonus=500;
					}
					partTimeFaculty.get(id%1000000).setMonthlyPay(pay*partTimeFaculty.get(id%1000000).getHours()+bonus);
					partTimeFaculty.get(id%1000000).setHourlyRate(pay);
				}
				else if(action==3){
					int maxStudents=0, bonus = 0;;
					System.out.println("Please input the hours this person works per month");
					int hours = getInputRange(0, 1000000);
					for(int i=0; i<partTimeFaculty.get(id%1000000).getNumCourses(); i++){
						if(partTimeFaculty.get(id%1000000).getStudentsPerClass(i)>maxStudents)
							maxStudents = partTimeFaculty.get(id%1000000).getStudentsPerClass(i);
					}
					if(maxStudents>60){
						bonus=1000;
					}
					else if(maxStudents>=40){
						bonus=500;
					}
					partTimeFaculty.get(id%1000000).setMonthlyPay(hours*partTimeFaculty.get(id%1000000).getHourlyRate()+bonus);
					partTimeFaculty.get(id%1000000).setHours(hours);
				}
				else if(action==4){
					for(int i=0; i<partTimeFaculty.get(id%1000000).getNumCourses(); i++){
						System.out.print("Input the name of class " + (i+1));
						partTimeFaculty.get(id%1000000).setCoursesTaught(i, scanner.next());
					}
				}
				else if(action==5){
					int students=0, maxStudents = 0;
					for(int i=0; i<partTimeFaculty.get(id%1000000).getNumCourses(); i++){
						System.out.print("Input the number of students in class " + partTimeFaculty.get(id%1000000).getCoursesTaught(i));
						students = getInputRange(0, 1000);
						if(students>maxStudents)
							maxStudents = students;
						partTimeFaculty.get(id%1000000).setStudentsPerClass(i, students);
					}
					if(maxStudents>60){
						partTimeFaculty.get(id%1000000).setMonthlyPay(partTimeFaculty.get(id%1000000).getMonthlyPay()+1000);
					}
					else if(maxStudents>=40){
						partTimeFaculty.get(id%1000000).setMonthlyPay(partTimeFaculty.get(id%1000000).getMonthlyPay()+500);
					}
				}
				else{
					System.out.println("Please input the number of months this employee has left to work");
					partTimeFaculty.get(id%1000000).setMonthsleft(getInputRange(1,1000000));
				}
				break;
			case 5:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Annual salary");
				action = getInputRange(1,2);
				if(action==1){
					System.out.println("Please input the new name");
					permanentStaff.get(id%1000000).setName(scanner.next());
				}
				else{
					System.out.println("Please input this person's annual salary");
					permanentStaff.get(id%1000000).setMonthlyPay((getInputDouble()/12));
				}
				break;
			case 6:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Countract pay\n" +
						"3: Total months on contract"+
						"4: Months left on contract");
				action = getInputRange(1,4);
				if(action==1){
					System.out.println("Please input the new name");
					partTimeStaff.get(id%1000000).setName(scanner.next());
				}
				else if(action==2){
					System.out.println("Please input the amount this person will be paid during the full time of their contract");
					partTimeStaff.get(id%1000000).setMonthlyPay(((getInputDouble()/partTimeStaff.get(id%1000000).getMonthlyContractDuration())));
				}
				else if(action==3){
					System.out.println("Please input the number of months on the contract");
					double contractPay = partTimeStaff.get(id%1000000).getMonthlyPay()*partTimeStaff.get(id%1000000).getMonthlyContractDuration();
					int months = getInputRange(1,1000000);
					partTimeStaff.get(id%1000000).setMonthlyContractDuration(months);
					partTimeStaff.get(id%1000000).setMonthlyPay((contractPay/months));
				}
				else{
					System.out.println("Please input the number of months this person has left to work");
					partTimeStaff.get(id%1000000).setMonthsLeft(getInputRange(1,1000000));
				}
				break;
			case 7:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Countract pay\n" +
						"3: Total months on contract"+
						"4: Months left on contract"+
						"5: Work location"+
						"6: Sales made"+
						"7: Add sales to the sales made");
				action = getInputRange(1,7);
				if(action==1){
					System.out.println("Please input the new name");
					commissionStaff.get(id%1000000).setName(scanner.next());
				}
				else if(action==2){
					System.out.println("Please input the amount this person will be paid during the full time of their contract");
					commissionStaff.get(id%1000000).setMonthlyPay(((getInputDouble()/commissionStaff.get(id%1000000).getMonthlyContractDuration())));
				}
				else if(action==3){
					System.out.println("Please input the number of months on the contract");
					double contractPay = commissionStaff.get(id%1000000).getMonthlyPay()*commissionStaff.get(id%1000000).getMonthlyContractDuration();
					int months = getInputRange(1,1000000);
					commissionStaff.get(id%1000000).setMonthlyContractDuration(months);
					commissionStaff.get(id%1000000).setMonthlyPay((contractPay/months));
				}
				else if(action==4){
					System.out.println("Please input the number of months this person has left to work");
					commissionStaff.get(id%1000000).setMonthsLeft(getInputRange(1,1000000));
				}
				else if(action==5){
					System.out.println("Please input the new location this person will be working at in Concordia");
					commissionStaff.get(id%1000000).setLocation(scanner.next());
				}
				else if(action==6){
					System.out.println("Please input the total sales this person made this month");
					commissionStaff.get(id%1000000).setSalesMade(getInputDouble());
				}
				else{
					System.out.println("Please input the sale you wish to add to this person's total sales made this month");
					commissionStaff.get(id%1000000).addSales(getInputDouble());
				}
				break;
			case 8:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Alumni status\n" +
						"3: Months until graduation"+
						"4: Hours worked per month"+
						"5: Hourly pay");
				action = getInputRange(1,5);
				if(action==1){
					System.out.println("Please input the new name");
					underGradTAs.get(id%1000000).setName(scanner.next());
				}
				else if(action==2){
					System.out.println("Input\n"+
							"1: Is alumni"+
							"2: Is not alumni");
					action=getInputRange(1,2);
					if(action==1)
						underGradTAs.get(id%1000000).setAlumni(true);
					else
						underGradTAs.get(id%1000000).setAlumni(false);
				}
				else if(action==3){
					System.out.println("Please input the number of months until graduation for this student");
					underGradTAs.get(id%1000000).setMonthsUntilGraduation(getInputRange(1,1000000));
				}
				else if(action==4){
					System.out.println("Please input the numbers of hours this TA works per month");
					int hours = getInputRange(0, 1000000);
					double hourlyPay = (underGradTAs.get(id%1000000).getMonthlyPay()/underGradTAs.get(id%1000000).getHours());
					underGradTAs.get(id%1000000).setHours(hours);
					underGradTAs.get(id%1000000).setMonthlyPay(hours*hourlyPay);
				}
				else{
					System.out.println("Please input the hourly pay for this TA");
					underGradTAs.get(id%1000000).setMonthlyPay(getInputDouble()*underGradTAs.get(id%1000000).getHours());
				}
				break;
		}
	}
	
	//method to print out the paystubs for the employees
	public static void paystubs(){
		int id;
		DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date=new Date();
		do{
			System.out.println("First we need to find the individual");
			id=search();
		}while(id<10);
		if (id!=13){
			System.out.println(((ConcordiaPerson)arrayCeption.get(id/1000000-1).get(id%1000000))+"\n"+dateFormat.format(date));
			if(id/1000000==4){
				System.out.println("Hours: "+((PartTimeFaculty)arrayCeption.get(id/1000000-1).get(id%1000000)).getHours());
				System.out.println("Hourly wage: "+((PartTimeFaculty)arrayCeption.get(id/1000000-1).get(id%1000000)).getHourlyRate());
			
		}
		}
	}
	
	//method to advance the system one month. Deletes employees from the system if their contract has expired and changes employees to alumni if they
	//have graduated. Also changes TAs to regular student alumni if they graduate
	public static void advanceToNextMonth(){
		int id;
		for(int i=0; i<students.size();i++){
			students.get(i).advanceMonthsLeftUntilGraduation();
			if (students.get(i).getMonthsLeftUntilGraduation()<=0){
				students.get(i).setAlumni(true);
			}
		}
		for(int i=0; i<partTimeFaculty.size(); i++){
			partTimeFaculty.get(i).advanceMonthsLeft();
			if (partTimeFaculty.get(i).getMonthsLeft()<=0){
				id=partTimeFaculty.get(i).getID();
				partTimeFaculty.get(i).setID(id/1000000-1);
			}
		}
		double totalSales=0;
		for (int i=0; i<commissionStaff.size();i++){
			for(int x=0; x<commissionStaff.size(); i++){
				totalSales+=commissionStaff.get(x).getSalesMade();
				if(x!=i&&commissionStaff.get(x).getLocation().equals(commissionStaff.get(i).getLocation())){
					totalSales+=commissionStaff.get(i).getSalesMade();
				}
			}
			commissionStaff.get(i).setMonthlyPay(commissionStaff.get(i).getMonthlyPay()+(totalSales/100));
			commissionStaff.get(i).advanceMonth();
			if (commissionStaff.get(i).getMonthsLeft()<=0){
				id=commissionStaff.get(i).getID();
				commissionStaff.get(i).setID(id/1000000-1);
			}
		}
		for (int i=0; i<partTimeStaff.size();i++){
			partTimeStaff.get(i).advanceMonthlyContractDuration();
			if (partTimeStaff.get(i).getMonthsLeft()<=0){
				id=partTimeStaff.get(i).getID();
				partTimeStaff.get(i).setID(id/1000000-1);
			}
			}
		
	}
	
	

	//method for replacing the ID of old and deleted ConcordiaPerson's
	public static int isItBadID(int id){
		int employeeType=id/1000000;
		int itIs=0;
		for(int i=0;i<badID.size();i++){
			if(badID.get(i)/1000000==employeeType){
				itIs=badID.get(i);
				badID.remove(i);
				break;
			}
		}
		return itIs;
	}
	
	//method to delete individuals from the system
	public static void deleteIndividual(){
		int id=search(),action=2;
		if(id<10 || id==13){
			action=3;
		}
		if(action==2){
		System.out.println("are you sure you want to delete\n"
				+ (ConcordiaPerson)arrayCeption.get(id/1000000-1).get(id%1000000)+"\n"
						+ "1.Yes\n"
						+ "2.No");
		action=getInputRange(1,2);
		}
		
		if(action==1){
		((ConcordiaPerson)arrayCeption.get(id/1000000-1).get(id%1000000)).setID(id/1000000-1);
		System.out.println("please reset the program to initiate the changes");
		}
		else
			System.out.println("Redirecting you to home page");
	}
	//this method allows you to list all the members of any group
	public static void list(){
		int action;
		System.out.println("would you like to list\n"
				+ "1: Regular students\n"
				+ "2: Grad TAs\n"
				+ "3: Undergrad TAs\n"
				+ "4: Permanent faculty members\n"
				+ "5: Part time faculty members\n"
				+ "6: Permanent staff\n"
				+ "7: Part time staff\n"
				+ "8: Commissioned staff\n"
				+ "9: All Students\n"
				+ "10:All Faculty\n"
				+ "11:All Staff\n"
				+ "12:Everyone\n");
		action=getInputRange(1,12);
		switch (action){
		case 1:
			listStudents();
			break;
		case 2:
			listGradTAs();
			break;
		case 3:
			listUndergradTAs();
			break;
		case 4:
			listPermanentFaculty();
			break;
		case 5:
			listPartTimeFaculty();
			break;
		case 6:
			listPermanentStaff();
			break;
		case 7:
			listPartTimeStaff();
			break;
		case 8:
			listCommissionStaff();
			break;
		case 9:
			listStudents();
			listGradTAs();
			listUndergradTAs();
			break;
		case 10:
			listPermanentFaculty();
			listPartTimeFaculty();
			break;
		case 11:
			listPermanentStaff();
			listPartTimeStaff();
			listCommissionStaff();
			break;
		case 12:
			listStudents();
			listGradTAs();
			listUndergradTAs();
			listPermanentFaculty();
			listPartTimeFaculty();
			listPermanentStaff();
			listPartTimeStaff();
			listCommissionStaff();
			break;
		}
		
	}
	
	public static void listStudents(){
		for(int i=0;i<students.size();i++)
			System.out.println(students.get(i));
	}
	public static void listGradTAs(){
		for(int i=0;i<gradTAs.size();i++)
			System.out.println(gradTAs.get(i));
	}
	public static void listUndergradTAs(){
		for(int i=0;i<underGradTAs.size();i++)
			System.out.println(underGradTAs.get(i));
	}
	public static void listPartTimeFaculty(){
		for(int i=0;i<partTimeFaculty.size();i++)
			System.out.println(partTimeFaculty.get(i));
	}
	public static void listPermanentFaculty(){
		for(int i=0;i<permanentFaculty.size();i++)
			System.out.println(permanentFaculty.get(i));
	}
	public static void listPermanentStaff(){
		for(int i=0;i<permanentStaff.size();i++)
			System.out.println(permanentStaff.get(i));
	}
	public static void listPartTimeStaff(){
		for(int i=0;i<partTimeStaff.size();i++)
			System.out.println(partTimeStaff.get(i));
	}
	public static void listCommissionStaff(){
		for(int i=0;i<commissionStaff.size();i++)
			System.out.println(commissionStaff.get(i));
	}

	//method to search for individuals, list individuals based on criteria, and check people who don't qualify for a TA position
	public static int search(){
		int action,id=0,employeeLocation=-1,employeeType;
		String name;
		boolean deleted=false;
		
		System.out.println("Please enter a number to search by\n"
				+ "1: Name or\n"
				+ "2: ID\n"
				+ "3: Go back");
		action=getInputRange(1,3);
		
		switch (action){
		case 1:
			int arrayIndex=0;
			do{
				System.out.println("Please enter the persons name");
				name=scanner.next();
				for(int i=0;i<arrayCeption.size();i++){
					for(int j=0;j<arrayCeption.get(i).size();j++){
						concordiaPerson.add((ConcordiaPerson) (arrayCeption.get(i).get(j)));
						
						if(concordiaPerson.get(arrayIndex).getName().equalsIgnoreCase(name)){
							id=concordiaPerson.get(arrayIndex).getID();
							employeeLocation=id%1000000;
							employeeType=id/1000000;
							if(((ConcordiaPerson) arrayCeption.get(employeeType-1).get(employeeLocation)).getID()<10){
								i=100000;
								deleted=true;
								break;
							}
													
							System.out.println(arrayCeption.get(employeeType-1).get(employeeLocation));
							return ((ConcordiaPerson) arrayCeption.get(employeeType-1).get(employeeLocation)).getID();
	
						}
						arrayIndex++;
					}
				}
				System.out.println("Error: there is no person with that name");
			}while(!deleted);
			return id;
		case 2:
			do{
			System.out.println("please enter the persons id");
			id=getInputRange(1000000,8000000);
			employeeLocation=id%1000000;
			employeeType=id/1000000;
			if(employeeLocation<=arrayCeption.get(employeeType-1).size()){
				if(((ConcordiaPerson) arrayCeption.get(employeeType-1).get(employeeLocation)).getID()<10){
					break;
				}			System.out.println(arrayCeption.get(employeeType-1).get(employeeLocation));
			return id;
			}
			System.out.println("Error: there is no person with that ID");
			}while(true);
			break;
		case 3: 
			return 13; 
		}
		System.out.println("Error: there is no person with that ID");
		return 1;
		
	}
	
	
	//Method returns the total pay for all Concordia employees
	public static double totalPay(){
		double pay = 0;
		
		for(int x=0; x<gradTAs.size(); x++)
			pay+=gradTAs.get(x).getMonthlyPay();
		for(int x=0; x<underGradTAs.size(); x++)
			pay+=underGradTAs.get(x).getMonthlyPay();
		for(int x=0; x<partTimeFaculty.size(); x++)
			pay+=partTimeFaculty.get(x).getMonthlyPay();
		for(int x=0; x<permanentFaculty.size(); x++)
			pay+=permanentFaculty.get(x).getMonthlyPay();
		for(int x=0; x<permanentStaff.size(); x++)
			pay+=permanentStaff.get(x).getMonthlyPay();
		for(int x=0; x<partTimeStaff.size(); x++)
			pay+=partTimeStaff.get(x).getMonthlyPay();
		for(int x=0; x<commissionStaff.size(); x++)
			pay+=commissionStaff.get(x).getMonthlyPay();
		return pay;
	}
	
	
	
	//Exits the program
	public static void exit(){
		JOptionPane.showMessageDialog(null, "Goodbye");
		System.exit(0);
	}
	
	//Gets number input from the user within the range specified
	public static int getInputRange(int low, int high){
		String input;
		boolean goodNum = false;
		int num = 0;
		do{
			input = scanner.next();
			if(parseable(true, input))
			{
				num = Integer.parseInt(input);
				if(num >= low && num <= high)
					goodNum = true;
				else
					JOptionPane.showMessageDialog(null, "Error: Num inputted was not one of the options. Please try that again");
			}
		}while(!goodNum);
		return num;
	}
	
	//Method to get double input and check for errors
	public static double getInputDouble(){
		String input;
		boolean parsed = false;
		do{
			input = scanner.next();
			parsed = parseable(false, input);
		}while(!parsed);
		return Double.parseDouble(input);
	}
	
	//Method to get integer input and check for errors
	public static int getInputInt(){
		String input;
		boolean parsed = false;
		do{
			input = scanner.next();
			parsed = parseable(false, input);
		}while(!parsed);
		return Integer.parseInt(input);
	}
	
	public static boolean parseable(boolean integer, String input){
		try{
			if(integer)
				Integer.parseInt(input);
			else
				Double.parseDouble(input);
		}
		//If it is not parseable, this block will catch the error and execute the code within it
		catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Error: The input received was not a number. Can you try that again?");
			return false;
		}
		return true;
	}
}
