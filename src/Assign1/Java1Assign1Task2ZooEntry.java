package Assign1;
/*
Assignment 1 - Task 2
Joshua Graham
ITC-206 (Distance)
ID: 11490893
Version 2.0
27/12/2015
 */
import java.util.Scanner;

public class Java1Assign1Task2ZooEntry {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(
				"The entry charges to a zoo are:\n\n" +
				"Children 5 years old and younger: free\n" +
				"Accompanied children from 6 to 15 years old: $2 each\n" +
				"Unaccompanied children from 6 to 15 years old: $5 each\n" +
				"Adults from 16 to 59 years old: $10 each\n" +
				"Seniors from 60 years and older: $8 each\n");
		Scanner input = new Scanner(System.in);
		int cost = 0;
		cost = inputPerson(input, cost, true);
		System.out.println("Total cost is: $" + cost);
		System.out.println("Thank for using this program.");
	}
	public static int inputPerson (Scanner input, int cost, boolean allowChild) {
		String text = null;
		while (text != "exit") {
			System.out.println("Whats your age? type 'exit' to quit");
			text = input.nextLine();
			try {
				if (text.equals("exit")) {
					break;
				} else {
					int age = Integer.parseInt(text);
					if (age <= 5) {
						if (allowChild == false) {
							System.out.println("< 5 year olds can't be the adult");
							continue; //re enter
						}
						//no cost
					} else if (age <= 15){
						//6 to 15
						if (allowChild == false) {
							System.out.println("< 16 year olds can't be the adult");
							continue; //re enter
						} else {
							while (!text.equalsIgnoreCase("Y") && !text.equalsIgnoreCase("N")) {
								System.out.println("Accompanied or not? (Y or N)");
								text = input.nextLine();
								//System.err.println(text != "Y" && text != "N");
								if (text.equalsIgnoreCase("Y")) {
									cost += 2;
									System.out.println("Accompanied adult:");
									cost = inputPerson (input, cost, false);
								} else if (text.equalsIgnoreCase("N")){
									//not accompanied
									cost += 5;
								} else {
									System.out.println("Incorrect entry");
								}
							}
						}
					} else if (age <= 59) {
						//16 to 59
						cost += 10;
						if (allowChild == false) {
							break; //escape
						}
					} else {
						//59+
						cost += 8;
						if (allowChild == false) {
							break; //escape
						}
					}
				}
			} catch (NumberFormatException e) {
				System.out.println("Bad entry.");
				continue;
			}
		}
		return cost;
	}
}
