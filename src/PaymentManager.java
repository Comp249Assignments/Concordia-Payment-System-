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
				//ta 2000000-3000000
				if((ID.get(index)>=2000000 && ID.get(index)<3000000)||ID.get(index)==1){
					tas.add((TA) in2.readObject());
					if(ID.get(index)==1){
						badID.add(taID);
					}
					taID++;
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
		
			arrayCeption.add(tas);
		
			arrayCeption.add(permanentFaculty);

			arrayCeption.add(partTimeFaculty);

			arrayCeption.add(permanentStaff);
			
			arrayCeption.add(partTimeStaff);

			arrayCeption.add(commissionStaff);

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
			action = getInputRange(1, 8);
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
		int action, itsBadID, ID, monthlyPay,numCourses,hours,bonus=0;
		String name="";
		String [] classNames;
		int [] studentsPerClass;
		
		double hourlyRate;
		
		System.out.println("Is the Faculty Member a\n" +
				"1: Permanent Faculty Member\n" +
				"2: Part time Faculty Member\n" +
		 		"3: Go back");
		action=getInputRange(1,2);
		if (action<3){
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
		 		partTimeFaculty.add(new PartTimeFaculty(ID, name, hours, hourlyRate, numCourses, classNames, studentsPerClass, bonus));
		 		else
			 		partTimeFaculty.add(itsBadID-4000000,new PartTimeFaculty(ID, name, hours, hourlyRate, numCourses, classNames, studentsPerClass, bonus));

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
	
	//Method to update the attributes of individuals (might use the search method in order to find that individual?)
	public static void update(){
		
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
		System.out.println(((ConcordiaPerson)arrayCeption.get(id/1000000-1).get(id%1000000))+"\n"+dateFormat.format(date));
		if(id/1000000==4){
			System.out.println("Hours: "+((PartTimeFaculty)arrayCeption.get(id/1000000-1).get(id%1000000)).getHours());
			System.out.println("Hourly wage: "+((PartTimeFaculty)arrayCeption.get(id/1000000-1).get(id%1000000)).getHourlyRate());
			
		}
	}
	
	//method to advance the system one month. Deletes employees from the system if their contract has expired and changes employees to alumni if they
	//have graduated. Also changes TAs to regular student alumni if they graduate
	public static void advanceToNextMonth(){
		
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
		if(id<10){
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
				+ "1: Students\n"
				+ "2:Ta's\n"
				+ "3:Permanent faculty members\n"
				+ "4:Part time faculty members\n"
				+ "5:Permanent staff\n"
				+ "6:Part time staff\n"
				+ "7:Commissioned staff\n");
		action=getInputRange(1,7);
		switch (action){
		case 1:
			for(int i=0;i<students.size();i++)
				System.out.println(students.get(i));
			break;
		case 2:
			for(int i=0;i<tas.size();i++)
				System.out.println(tas.get(i));
			break;
		case 3:
			for(int i=0;i<permanentFaculty.size();i++)
				System.out.println(permanentFaculty.get(i));
			break;
		case 4:
			for(int i=0;i<partTimeFaculty.size();i++)
				System.out.println(partTimeFaculty.get(i));
			break;
		case 5:
			for(int i=0;i<permanentStaff.size();i++)
				System.out.println(permanentStaff.get(i));
			break;
		case 6:
			for(int i=0;i<partTimeStaff.size();i++)
				System.out.println(partTimeStaff.get(i));
			break;
		case 7:
			for(int i=0;i<commissionStaff.size();i++)
				System.out.println(commissionStaff.get(i));
			break;
		}
		
	}

	//method to search for individuals, list individuals based on criteria, and check people who don't qualify for a TA position
	public static int search(){
		int action,id=0,employeeLocation=-1,employeeType;
		String name;
		boolean deleted=false;
		
		System.out.println("Please enter a number to search by\n"
				+ "1: Name or\n"
				+ "2: ID");
		action=getInputRange(1,2);
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
		}
		System.out.println("Error: there is no person with that ID");
		return 1;
	}
	
	
	//Method returns the total pay for all Concordia employees
	public static double totalPay(){
		double pay = 0;
		
		for(int x=0; x<tas.size(); x++)
			pay+=tas.get(x).getMonthlyPay();
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
