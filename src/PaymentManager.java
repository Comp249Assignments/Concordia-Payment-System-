import java.io.*;
import java.util.*;

import javax.swing.*;

public class PaymentManager {
	private static Scanner scanner = new Scanner(System.in);
	private static double underGradTAPay;
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
		
		return 0;
	}
	
	//appends to the save file
	public static void writeToSave(ArrayList t){
		try{
			ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(file,true));
			out.writeObject(t);
			out.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		
	}
	//method to save the system. Should run whenever a change is made to the system, in case the user closes the program in an unexpected way
	public static void save(){
		if(students.size()>0)
			writeToSave(students);
		if (tas.size()>0)
			writeToSave(tas);
		if (partTimeFaculty.size()>0)
			writeToSave(partTimeFaculty);
		if (permanentFaculty.size()>0)
			writeToSave(permanentFaculty);
		if (partTimeStaff.size()>0)
			writeToSave(partTimeStaff);
		if (permanentStaff.size()>0)
			writeToSave(permanentStaff);
		if (commissionStaff.size()>0)
			writeToSave(tas);
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
		int monthlyPay,numCourses,hours;
		String input,name,ID;
		boolean k=false;
		double hourlyRate;
		
		System.out.println("Is the Faculty Member a\n" +
				"1: Permanent Faculty Member\n" +
				"2: Part time Faculty Member\n" +
		 		"3: Go back");
		action=getInputRange(1,2);
		switch (action){
			case 1:
				//input ID
				System.out.println("Please input this faculty member's ID");//---------------------------------------------------------------------------------------
		 		ID=scanner.next();
			 
		 		//input name
		 		System.out.println("Please input this faculty member's name");
		 		name=scanner.next();
			 
		 		//input numClasses
		 		System.out.println("Please input the number of courses this faculty member will be teaching");
		 		numCourses=getInputInt();
		 		
		 		//input monthlyPay
		 		System.out.println("Please input this faculty member's monthly salary in dollars");
		 		monthlyPay=getInputInt();
			 
		 		//PermenantFaculty permenantFaculty=
		 		permanentFaculty.add(new PermanentFaculty(ID, name, monthlyPay, numCourses));
		 		break;
			case 2:
		 		//input ID
		 		System.out.println("Please input this faculty member's ID");//---------------------------------------------------------------------------------------
		 		ID=scanner.next();
		 		
		 		//input name
		 		System.out.println("Please input this faculty member's name");
		 		name=scanner.next();
		 		
		 		//input hourlyRate
		 		//Why do the next two things ask about TA??
		 		System.out.println("Please input the amount the Faculty Member will be paid per hour in dollars");
		 		hourlyRate=getInputDouble();
		 		
		 		//input amount of hours
		 		System.out.println("please input the amount of hours the Faculty Member will be teaching for");
		 		hours=getInputInt();
			 
		 		//input numClasses
		 		System.out.println("Please input the amount of classes this faculty member is teaching");
		 		numCourses=getInputRange(1,2);
		 		
		 		partTimeFaculty.add(new PartTimeFaculty(ID, name, hours, hourlyRate, numCourses));
		 }
	 }
	
	//Method for adding any kind of staff member
	public static void addStaffMember(){
		int action, duration;
		String id = "", name = "", input;
		double pay;
		System.out.println("Is the staff a \n" +
				"1: Part time staff member\n" +
				"2: Permanent staff member\n" +
				"3: Go back");
		action=getInputRange(1,3);
		
		if(action < 3){
			System.out.println("Please input this staff member's ID");
			id=scanner.next();
			
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
				 		commissionStaff.add(new CommissionStaff(id, name, pay, duration, input));
						break;
					case 2:
						partTimeStaff.add(new PartTimeStaff(id, name, pay, duration));
						break;
				}
				break;
			case 2:
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
	
	//method to search for individuals, list individuals based on criteria, and check people who don't qualify for a TA position
	public static void search(){
		
	}
	
	//method to print out the paystubs for the employees
	public static void paystubs(){
		
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
	
	//method to advance the system one month. Deletes employees from the system if their contract has expired and changes employees to alumni if they
	//have graduated. Also changes TAs to regular student alumni if they graduate
	public static void advanceToNextMonth(){
		
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
