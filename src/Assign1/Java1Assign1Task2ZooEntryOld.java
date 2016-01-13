package Assign1;
/*
Assignment 1 - Task 2
Joshua Graham
ITC-206 (Distance)
ID: 11490893
Version 1.0
*/
import java.util.Scanner;
public class Java1Assign1Task2ZooEntryOld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Initialise the scanner
		Scanner input = new Scanner(System.in);
		int total = 0;
		// TODO Auto-generated method stub
		System.out.println("Zoo Charge Calculator");
		System.out.println(" "); //empty white space
		//loop start
		System.out.print("Enter a group? (Yes=1/No=0):");
		int userInputGroup = input.nextInt();
		while (userInputGroup == 1){
			System.out.print("Enter the number of children (age 6-15):");
			int userInputChildren = input.nextInt();
			System.out.print("Enter the number of adults (age 16-59):");
			int userInputAdults = input.nextInt();
			System.out.print("Enter the number of seniors (age60+):");
			int userInputSeniors = input.nextInt();
		
			//instalise calc variables
			int unaccompChildren, accompChildren, adults, seniors, charge;
		
			//calculation		
			if((userInputAdults + userInputSeniors) >=  userInputChildren){ //calc accompanied children
				accompChildren = userInputChildren;
			}
			else {
				accompChildren = (userInputAdults + userInputSeniors);
			}
			unaccompChildren = userInputChildren - (userInputAdults + userInputSeniors);
			if(unaccompChildren < 0)//can't have negatives, must be no unaccompained children
			unaccompChildren = 0; 
			adults = userInputAdults;
			seniors = userInputSeniors;
			//charges
			charge = accompChildren * 2;
			charge = charge + (unaccompChildren * 5);
			charge = charge + (adults * 10);
			charge = charge + (seniors * 8);
			
			total = total + charge;
			//total
			System.out.println("Total entry charge is $" + charge);
			System.out.println(" "); //empty white space
		
			//start again
			System.out.print("Enter a group? (Yes=1/No=0):");
			userInputGroup = input.nextInt();
			
		}
		//loop end
		System.out.println("Total takings: $" + total);
		if(input != null) {
			input.close();
		}
	}

}
