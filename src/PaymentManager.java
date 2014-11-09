/*Names of team members: Sean Marcoux, Gadiel Weigensberg, Andre Manata
 *IDs: 7511876, 7164777
 */

import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.*;

public class PaymentManager {
	
	static int load=0;	
	private static int concordiaID=1000000;
	private static ArrayList<Integer> badID=new ArrayList();
	private static	File file=new File("Concordia database.txt");
	private static Scanner scanner = new Scanner(System.in);
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
	private static UnderGradTA baseRate;
	public static void main(String[] args) {
		startUp();
		scanner.close();
	}
	//each TA has an individual salary so we will have to put this in the TA class
	public static void startUp(){
		double hourlyPay;
		System.out.println("Welcome!");
		if(load()==0){
			System.out.println("What is the base hourly pay for an Undergrad TA?");
			hourlyPay = getInputDouble();
			baseRate = new UnderGradTA(hourlyPay);
		}
		action();
	}
	//The method to load the system (returns 0 if there is no system to load)
	public static int load(){
		ConcordiaPerson loadingBuffer=new ConcordiaPerson();
		int index=0;
		boolean stop=false;
		try{
			
			ObjectInputStream in=new ObjectInputStream(new FileInputStream(file));

			
			//wont stop until IOException is recieved (meaning we hit the end of the file)
			while(!stop){
				//retrieving the id and inputing it into an ArrayList ID
				loadingBuffer=((ConcordiaPerson) in.readObject());
				//grad ta 
				if(loadingBuffer instanceof GradTA){
					gradTAs.add((GradTA) loadingBuffer);
					load++;
					// to make sure it isn't loaded into the Student ArrayList as Well
					loadingBuffer=null;
				}
				
				//undergrad ta 
				if(loadingBuffer instanceof UnderGradTA){
					underGradTAs.add((UnderGradTA) loadingBuffer);
					load++;
					loadingBuffer=null;
				}
			
				//students 
				if(loadingBuffer instanceof Student){
					students.add((Student) loadingBuffer);
					load++;
				}
			
				//permanentFaculty 
				if(loadingBuffer instanceof PermanentFaculty){
					permanentFaculty.add((PermanentFaculty) loadingBuffer);
					load++;
				}
				
				//partTimeFaculty 
				if(loadingBuffer instanceof PartTimeFaculty){
					partTimeFaculty.add((PartTimeFaculty) loadingBuffer);
					load++;
				}			
				
				//permanentStaff 
				if(loadingBuffer instanceof PermanentStaff){
					permanentStaff.add((PermanentStaff) loadingBuffer);
					load++;
				}
				
				//commissionStaff 
				if(loadingBuffer instanceof CommissionStaff){
					commissionStaff.add((CommissionStaff) loadingBuffer);
					load++;
					loadingBuffer=null;
				}
				
				//partTimeStaff 
				if(loadingBuffer instanceof PartTimeStaff){
				partTimeStaff.add((PartTimeStaff) loadingBuffer);
					load++;
				}
				
				
				
			
				
			
			index++;
		}
			
			in.close();
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
		int action, duration,id, hours;
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
				
				id=concordiaID++;
			 	students.add(new Student(id, name, 0, alumni, duration));
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
				
				pay = baseRate.getMonthlyPay();
				
				if(action==1){
					pay= pay*1.2;
					id=concordiaID++;
				 	gradTAs.add(new GradTA(id, name, pay, duration, hours));
				}
				
				else{
						id=concordiaID++;
				 		underGradTAs.add(new UnderGradTA(id, name, pay, duration, hours));
				}
				
				break;
		}
	}
	

	//Method for adding a faculty member
	public static void addFacultyMember(){
		int action, ID, monthlyPay,numCourses,hours,monthsLeft,bonus=0;
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
				ID=concordiaID++;

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
		 		
		 	
		 		//PermenantFaculty permenantFaculty
		 			permanentFaculty.add(new PermanentFaculty(ID, name, monthlyPay, numCourses, classNames, studentsPerClass));

		 		break;
			case 2:
		 		//generate ID
					ID=concordiaID++;
					
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
		 			partTimeFaculty.add(new PartTimeFaculty(ID, name, hours, hourlyRate, numCourses, classNames, studentsPerClass, bonus,monthsLeft));

		}
	 }
	
	//Method for adding any kind of staff member
	public static void addStaffMember(){
		int action, duration,id;
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
				 		//generate ID
						id=concordiaID++;
					 	commissionStaff.add(new CommissionStaff(id, name, pay, duration, input));			
						break;
					case 2:
						//generate ID
						id=concordiaID++;
						partTimeStaff.add(new PartTimeStaff(id, name, pay, duration));
						break;
				}
				break;
			case 2:
				System.out.println("Please input this staff member's yearly salary");
		 		pay = getInputDouble();
		 		//generate ID
				id=concordiaID++;
		 			permanentStaff.add(new PermanentStaff(id, name, pay));
					break;
		}
	}
	
	//Method to update the attributes of individuals (might use the search method in order to find that individual?)
	public static void update(){
		int action, hours;
		int[] person;
		boolean stop = false;
		System.out.println("We must search for the person to delete first");
		do{
			System.out.println("First we need to find the individual");
			person=search();
		}while(person[0]>0);
		switch(person[0]){
			case 1:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Alumni status\n" +
						"3: Months until graduation\n"+
						"4: Make into a TA");
				action = getInputRange(1,4);
				if(action==1){
					System.out.println("Please input the new name");
					students.get(person[1]).setName(scanner.next());
				}
				else if(action==2){
					System.out.println("Input\n"+
							"1: Is alumni\n"+
							"2: Is not alumni");
					action=getInputRange(1,2);
					if(action==1)
						students.get(person[1]).setAlumni(true);
					else
						students.get(person[1]).setAlumni(false);
				}
				else if(action==3){
					System.out.println("Please input the number of months until graduation for this student");
					students.get(person[1]).setMonthsUntilGraduation(getInputRange(1,1000000));
				}
				else{
					if(students.get(person[1]).qualifiesForTA()){
						System.out.println("Is this a(n)\n"+
								"1: Grad Student\n"+
								"2: Undergrad Student");
						action = getInputRange(1,2);
						System.out.println("How many hours will this person work per month?");
						hours = getInputInt();
						if(action==1){
							gradTAs.add(new GradTA(students.get(person[1]).getID(), students.get(person[1]).getName(), (baseRate.getMonthlyPay()*1.2), students.get(person[1]).getMonthsLeftUntilGraduation(), hours));
							students.remove(person[1]);
						}
						else{
							underGradTAs.add(new UnderGradTA(students.get(person[1]).getID(), students.get(person[1]).getName(), baseRate.getMonthlyPay(), students.get(person[1]).getMonthsLeftUntilGraduation(), hours));
							students.remove(person[1]);
						}
					}
					else{
						System.out.println("This student cannot be a TA");
					}
				}
				break;
			case 2:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Alumni status\n" +
						"3: Months until graduation\n"+
						"4: Hours worked per month\n"+
						"5: Hourly pay");
				action = getInputRange(1,5);
				if(action==1){
					System.out.println("Please input the new name");
					gradTAs.get(person[1]).setName(scanner.next());
				}
				else if(action==2){
					System.out.println("Input\n"+
							"1: Is alumni\n"+
							"2: Is not alumni");
					action=getInputRange(1,2);
					if(action==1)
					{
						students.add(new Student(gradTAs.get(person[1]).getID(), gradTAs.get(person[0]).getName(), 0, true, 0));
						gradTAs.remove(person[1]);
					}
					else
						gradTAs.get(person[1]).setAlumni(false);
				}
				else if(action==3){
					System.out.println("Please input the number of months until graduation for this student");
					gradTAs.get(person[1]).setMonthsUntilGraduation(getInputRange(1,1000000));
				}
				else if(action==4){
					System.out.println("Please input the numbers of hours this TA works per month");
					hours = getInputRange(0, 1000000);
					double hourlyPay = (gradTAs.get(person[1]).getMonthlyPay()/gradTAs.get(person[1]).getHours());
					gradTAs.get(person[1]).setHours(hours);
					gradTAs.get(person[1]).setMonthlyPay(hours*hourlyPay);
				}
				else{
					System.out.println("Please input the hourly pay for this TA");
					gradTAs.get(person[1]).setMonthlyPay(getInputDouble()*gradTAs.get(person[1]).getHours());
				}
				break;
			case 3:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Alumni status\n" +
						"3: Months until graduation\n"+
						"4: Hours worked per month\n"+
						"5: Hourly pay");
				action = getInputRange(1,5);
				if(action==1){
					System.out.println("Please input the new name");
					underGradTAs.get(person[1]).setName(scanner.next());
				}
				else if(action==2){
					System.out.println("Input\n"+
							"1: Is alumni\n"+
							"2: Is not alumni");
					action=getInputRange(1,2);
					if(action==1){
						students.add(new Student(underGradTAs.get(person[1]).getID(), underGradTAs.get(person[0]).getName(), 0, true, 0));
						underGradTAs.remove(person[1]);
					}
					else
						underGradTAs.get(person[1]).setAlumni(false);
				}
				else if(action==3){
					System.out.println("Please input the number of months until graduation for this student");
					underGradTAs.get(person[1]).setMonthsUntilGraduation(getInputRange(1,1000000));
				}
				else if(action==4){
					System.out.println("Please input the numbers of hours this TA works per month");
					hours = getInputRange(0, 1000000);
					double hourlyPay = (underGradTAs.get(person[1]).getMonthlyPay()/underGradTAs.get(person[1]).getHours());
					underGradTAs.get(person[1]).setHours(hours);
					underGradTAs.get(person[1]).setMonthlyPay(hours*hourlyPay);
				}
				else{
					System.out.println("Please input the hourly pay for this TA");
					underGradTAs.get(person[1]).setMonthlyPay(getInputDouble()*underGradTAs.get(person[1]).getHours());
				}
				break;
			case 4:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Monthly pay\n" +
						"3: Classes\n"+
						"4: Students per class");
				action = getInputRange(1,5);
				if(action==1){
					System.out.println("Please input the new name");
					permanentFaculty.get(person[1]).setName(scanner.next());
				}
				else if(action==2){
					System.out.println("Please input the monthly pay for this person");
					permanentFaculty.get(person[1]).setMonthlyPay(getInputDouble());
				}
				else if(action==3){
					for(int i=0; i<permanentFaculty.get(person[1]).getNumCourses(); i++){
						System.out.print("Input the name of class " + (i+1));
						permanentFaculty.get(person[1]).setCoursesTaught(i, scanner.next());
					}
				}
				else{
					for(int i=0; i<permanentFaculty.get(person[1]).getNumCourses(); i++){
						System.out.print("Input the number of students in class " + permanentFaculty.get(person[1]).getCoursesTaught(i));
						permanentFaculty.get(person[1]).setStudentsPerClass(i, getInputRange(0, 1000));
					}
				}
				break;
			case 5:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Hourly pay\n" +
						"3: Hours\n"+
						"4: Classes\n"+
						"5: Students per class\n"+
						"6: Remaining months of employment");
				action = getInputRange(1,6);
				if(action==1){
					System.out.println("Please input the new name");
					partTimeFaculty.get(person[1]).setName(scanner.next());
				}
				else if(action==2){
					int maxStudents=0, bonus = 0;;
					System.out.println("Please input the hourly pay for this person");
					double pay = getInputDouble();
					for(int i=0; i<partTimeFaculty.get(person[1]).getNumCourses(); i++){
						if(partTimeFaculty.get(person[1]).getStudentsPerClass(i)>maxStudents)
							maxStudents = partTimeFaculty.get(person[1]).getStudentsPerClass(i);
					}
					if(maxStudents>60){
						bonus=1000;
					}
					else if(maxStudents>=40){
						bonus=500;
					}
					partTimeFaculty.get(person[1]).setMonthlyPay(pay*partTimeFaculty.get(person[1]).getHours()+bonus);
					partTimeFaculty.get(person[1]).setHourlyRate(pay);
				}
				else if(action==3){
					int maxStudents=0, bonus = 0;;
					System.out.println("Please input the hours this person works per month");
					hours = getInputRange(0, 1000000);
					for(int i=0; i<partTimeFaculty.get(person[1]).getNumCourses(); i++){
						if(partTimeFaculty.get(person[1]).getStudentsPerClass(i)>maxStudents)
							maxStudents = partTimeFaculty.get(person[1]).getStudentsPerClass(i);
					}
					if(maxStudents>60){
						bonus=1000;
					}
					else if(maxStudents>=40){
						bonus=500;
					}
					partTimeFaculty.get(person[1]).setMonthlyPay(hours*partTimeFaculty.get(person[1]).getHourlyRate()+bonus);
					partTimeFaculty.get(person[1]).setHours(hours);
				}
				else if(action==4){
					for(int i=0; i<partTimeFaculty.get(person[1]).getNumCourses(); i++){
						System.out.print("Input the name of class " + (i+1));
						partTimeFaculty.get(person[1]).setCoursesTaught(i, scanner.next());
					}
				}
				else if(action==5){
					int students=0, maxStudents = 0;
					for(int i=0; i<partTimeFaculty.get(person[1]).getNumCourses(); i++){
						System.out.print("Input the number of students in class " + partTimeFaculty.get(person[1]).getCoursesTaught(i));
						students = getInputRange(0, 1000);
						if(students>maxStudents)
							maxStudents = students;
						partTimeFaculty.get(person[1]).setStudentsPerClass(i, students);
					}
					if(maxStudents>60){
						partTimeFaculty.get(person[1]).setMonthlyPay(partTimeFaculty.get(person[1]).getMonthlyPay()+1000);
					}
					else if(maxStudents>=40){
						partTimeFaculty.get(person[1]).setMonthlyPay(partTimeFaculty.get(person[1]).getMonthlyPay()+500);
					}
				}
				else{
					System.out.println("Please input the number of months this employee has left to work");
					partTimeFaculty.get(person[1]).setMonthsleft(getInputRange(1,1000000));
				}
				break;
			case 6:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Annual salary");
				action = getInputRange(1,2);
				if(action==1){
					System.out.println("Please input the new name");
					permanentStaff.get(person[1]).setName(scanner.next());
				}
				else{
					System.out.println("Please input this person's annual salary");
					permanentStaff.get(person[1]).setMonthlyPay((getInputDouble()/12));
				}
				break;
			case 7:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Countract pay\n" +
						"3: Total months on contract\n"+
						"4: Months left on contract");
				action = getInputRange(1,4);
				if(action==1){
					System.out.println("Please input the new name");
					partTimeStaff.get(person[1]).setName(scanner.next());
				}
				else if(action==2){
					System.out.println("Please input the amount this person will be paid during the full time of their contract");
					partTimeStaff.get(person[1]).setMonthlyPay(((getInputDouble()/partTimeStaff.get(person[1]).getMonthlyContractDuration())));
				}
				else if(action==3){
					System.out.println("Please input the number of months on the contract");
					double contractPay = partTimeStaff.get(person[1]).getMonthlyPay()*partTimeStaff.get(person[1]).getMonthlyContractDuration();
					int months = getInputRange(1,1000000);
					partTimeStaff.get(person[1]).setMonthlyContractDuration(months);
					partTimeStaff.get(person[1]).setMonthlyPay((contractPay/months));
				}
				else{
					System.out.println("Please input the number of months this person has left to work");
					partTimeStaff.get(person[1]).setMonthsLeft(getInputRange(1,1000000));
				}
				break;
			case 8:
				System.out.println("Would you like to update the \n" +
						"1: Name\n" +
						"2: Countract pay\n" +
						"3: Total months on contract\n"+
						"4: Months left on contract\n"+
						"5: Work location\n"+
						"6: Sales made\n"+
						"7: Add sales to the sales made");
				action = getInputRange(1,7);
				if(action==1){
					System.out.println("Please input the new name");
					commissionStaff.get(person[1]).setName(scanner.next());
				}
				else if(action==2){
					System.out.println("Please input the amount this person will be paid during the full time of their contract");
					commissionStaff.get(person[1]).setMonthlyPay(((getInputDouble()/commissionStaff.get(person[1]).getMonthlyContractDuration())));
				}
				else if(action==3){
					System.out.println("Please input the number of months on the contract");
					double contractPay = commissionStaff.get(person[1]).getMonthlyPay()*commissionStaff.get(person[1]).getMonthlyContractDuration();
					int months = getInputRange(1,1000000);
					commissionStaff.get(person[1]).setMonthlyContractDuration(months);
					commissionStaff.get(person[1]).setMonthlyPay((contractPay/months));
				}
				else if(action==4){
					System.out.println("Please input the number of months this person has left to work");
					commissionStaff.get(person[1]).setMonthsLeft(getInputRange(1,1000000));
				}
				else if(action==5){
					System.out.println("Please input the new location this person will be working at in Concordia");
					commissionStaff.get(person[1]).setLocation(scanner.next());
				}
				else if(action==6){
					System.out.println("Please input the total sales this person made this month");
					commissionStaff.get(person[1]).setSalesMade(getInputDouble());
				}
				else{
					System.out.println("Please input the sale you wish to add to this person's total sales made this month");
					commissionStaff.get(person[1]).addSales(getInputDouble());
				}
				break;
		}
	}
	
	//method to print out the paystubs for the employees
	public static void paystubs(){
		int id;
		int action;
		System.out.println("would you like to view the paystub of \n"
				+ "1.All members"
				+ "2.A specific member");
		action=getInputRange(1,2);
		switch(action){
		
		case 1:
			for(int i=0;i<arrayCeption.size();i++){
				for(int j=0;j<arrayCeption.get(i).size();j++){
					System.out.println(arrayCeption.get(i).get(j));
				}
			}
		
		case 2:
		int [] arrayLocation=search();
		
		DateFormat dateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date=new Date();
		do{
			System.out.println("First we need to find the individual");
			id=search();
		}while(id<10);
		
			System.out.println(arrayCeption.get(arrayLocation[0]).get(arrayLocation[1])+"\n"+dateFormat.format(date));
			break;
			
		
			
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
				partTimeFaculty.remove(i);
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
				commissionStaff.remove(i);
				
			}
		}
		for (int i=0; i<partTimeStaff.size();i++){
			partTimeStaff.get(i).advanceMonthlyContractDuration();
			if (partTimeStaff.get(i).getMonthsLeft()<=0){
				partTimeStaff.remove(i);
				
			}
			}
		
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
