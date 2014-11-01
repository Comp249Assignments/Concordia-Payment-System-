import java.io.*;
import java.util.*;

import javax.swing.*;

public class PaymentManager {
	
	static int load=0;
	private static int studentID=1000000, taID=2000000, permanentFacultyID=3000000, partTimeFacultyID=4000000,
			permanentStaffID=5000000, partTimeStaffID=6000000, commissionStaffID=7000000;
	private static	File file=new File("Concordia database.txt");
	private static Scanner scanner = new Scanner(System.in);
	private static double underGradTAPay;
	private static ArrayList<ArrayList> arrayCeption=new ArrayList();
	private static ArrayList<Student> students = new ArrayList();
	private static ArrayList<TA> tas = new ArrayList();
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
		if(load()==0)
		{
			
			System.out.println("How much are undergrad TAs paid?");
			underGradTAPay = getInputDouble();
		}
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
				if(ID.get(index)>=1000000 && ID.get(index)<2000000){
					students.add((Student) in2.readObject());
					studentID++;
					load++;
				}
				//ta 2000000-3000000
				if(ID.get(index)>=2000000 && ID.get(index)<3000000){
					tas.add((TA) in2.readObject());
					taID++;
					load++;
				}
				
				//permanentFaculty 3000000-4000000
				if(ID.get(index)>=3000000 && ID.get(index)<4000000){
					permanentFaculty.add((PermanentFaculty) in2.readObject());
					permanentFacultyID++;
					load++;
				}
				
				//partTimeFaculty 4000000-5000000
				if(ID.get(index)>=4000000 && ID.get(index)<5000000){
					partTimeFaculty.add((PartTimeFaculty) in2.readObject());
					partTimeFacultyID++;
					load++;
				}			
				
				//permanentStaff 5000000-6000000
				if(ID.get(index)>=5000000 && ID.get(index)<6000000){
					permanentStaff.add((PermanentStaff) in2.readObject());
					permanentStaffID++;
					load++;
				}
				
				//partTimeStaff 6000000-7000000
				if(ID.get(index)>=6000000 && ID.get(index)<7000000){
					partTimeStaff.add((PartTimeStaff) in2.readObject());
					partTimeStaffID++;
					load++;
				}
				
				//commissionStaff 7000000-8000000
				if(ID.get(index)>=7000000 && ID.get(index)<8000000){
					commissionStaff.add((CommissionStaff) in2.readObject());
					commissionStaffID++;
					load++;
				}
			index++;
		}
			idIn.close();
			
			in2.close();
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
				return 0;
			}
			catch(IOException e){
				//when file ends stops the while loop
				stop=true;
			}
			catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		
			
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
		if(students.size()>0)
			arrayCeption.add(students);
		
		if (tas.size()>0)
			arrayCeption.add(tas);
		
		if (partTimeFaculty.size()>0)
			arrayCeption.add(partTimeFaculty);

		if (permanentFaculty.size()>0)
			arrayCeption.add(permanentFaculty);

		if (partTimeStaff.size()>0)
			arrayCeption.add(partTimeStaff);

		if (permanentStaff.size()>0)
			arrayCeption.add(permanentStaff);

		if (commissionStaff.size()>0)
			arrayCeption.add(commissionStaff);

		writeToSave(arrayCeption);
	}
	
	
	public static void action(){
		int action;
		boolean stop = false;
		while(!stop){
			System.out.println("What do you wish to do? \n"+
					"1: Add individuals to the system\n"+
					"2: Update attributes of individuals\n"+
					"3: Delete individuals\n"+
					"4: Search for someone in the system\n"+
					"5: Prepare paystubs\n"+
					"6: Calculate the amount of money that needs to be paid to all Concordia employees\n"+
					"7: Advance to next month\n"+
					"8: Exit");
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
		
	}
	
	//Method for adding a faculty member
	public static void addFacultyMember(){
		int action;
		int monthlyPay,numCourses,hours,bonus=0;
		String name,ID;
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
				ID=permanentFacultyID++;
				
		 		
			 
		 		//input numClasses
		 		System.out.println("Please input the number of courses this faculty member will be teaching");
		 		numCourses=getInputInt();
		 		
		 		//input monthlyPay
		 		System.out.println("Please input this faculty member's monthly salary in dollars");
		 		monthlyPay=getInputInt();
		 		
		 		//input class names
		 		classNames=new String[numCourses];
		 		System.out.println("Please input the names of the classes");
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
		 		permanentFaculty.add(new PermanentFaculty(ID, name, monthlyPay, numCourses, classNames, studentsPerClass));
		 		break;
			case 2:
		 		//generate ID
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
		 		partTimeFaculty.add(new PartTimeFaculty(ID, name, hours, hourlyRate, numCourses, classNames, studentsPerClass, bonus));
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
						//generate ID
						id=partTimeStaffID++;
						System.out.println("Where at Concordia does this person work?");
				 		input = scanner.next();
				 		commissionStaff.add(new CommissionStaff(id, name, pay, duration, input));
						break;
					case 2:
						//generate ID
						id=commissionedStaffID++;
						partTimeStaff.add(new PartTimeStaff(id, name, pay, duration));
						break;
				}
				break;
			case 2:
				//generate ID
				id=permanentStaffID++;
				System.out.println("Please input this staff member's yearly salary");
		 		pay = getInputDouble();
		 		permanentStaff.add(new PermanentStaff(id, name, pay));
				break;
		}
	}
	
	//Method to update the attributes of individuals (might use the search method in order to find that individual?)
	public static void update(){
		
	}
	
	//method to delete individuals from the system
	public static void deleteIndividual(){
		
	}
	
	
	//method to print out the paystubs for the employees
	public static void paystubs(){
		
	}
	//method to advance the system one month. Deletes employees from the system if their contract has expired and changes employees to alumni if they
	//have graduated. Also changes TAs to regular student alumni if they graduate
	public static void advanceToNextMonth(){
		
	}
	
	//method to search for individuals, list individuals based on criteria, and check people who don't qualify for a TA position
	public static void search(){
		int action,id;
		String name;
		
		System.out.println("would you like to search by\n"
				+ "1.Name\n"
				+ "2.Id");
		action=getInputRange(1,2);
		switch (action){
		case 1:
			System.out.println("please enter the persons name");
			name=scanner.next();
			for(int i=0;i<arrayCeption.size();i++)
				for(int j=0;j<arrayCeption.get(i).size();j++){
					concordiaPerson.add((ConcordiaPerson) (arrayCeption.get(i).get(j)));
					if(concordiaPerson.get(j).getName().equalsIgnoreCase(name)){
						concordiaPerson.get(j).getID();
						id=concordiaPerson.get(j).getID();
						findUser(id);
					}
				}
			break;
		case 2:
			System.out.println("please enter the persons id");
			id=getInputRange(1000000,8000000);
			findUser(id);
			
			break;
		}
		System.out.print("");
	}
	//finds user through his id
	public static void findUser(int id){
		if (id>=1000000 && id<2000000)
			System.out.println(students.get(id-1000000));
		
		if (id>=2000000 && id<3000000)
			 System.out.println(tas.get(id-2000000));
		if (id>=3000000 && id<4000000)
			System.out.println(permanentFaculty.get(id-3000000));
		if (id>=4000000 && id<5000000)
			 System.out.println(partTimeFaculty.get(id-4000000));
		if (id>=5000000 && id<6000000)
			 System.out.println(permanentStaff.get(id-5000000));
		if (id>=6000000 && id<7000000)
			 System.out.println(partTimeStaff.get(id-6000000));
		if (id>=7000000 && id<8000000)
			 System.out.println(commissionStaff.get(id-7000000));
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
