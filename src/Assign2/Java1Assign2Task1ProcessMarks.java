package Assign2;

public class Java1Assign2Task1ProcessMarks {
	public static void main(String[] args) {		 //program start here
		// Display the marks from Marks.class
		int[] marklist = Java1Assign2Task1Marks.getMarks(); 			//make array
		System.out.println("The Grades:\n");
		for (int i = 0; i < marklist.length; i++)
			if  ((i + 1) % 30 == 0) 				//30 per line
				System.out.println(marklist[i] + " ");
			else
				System.out.print(marklist[i] + " ");
		//Begin main menu rendering and declarations
		int choice;
		String strChoice;
		int running = 1; //using while (pre-test) loop, program must know it is running before actually running it-self
		java.util.Scanner input = new java.util.Scanner(System.in); //initialize the input scanner (used for keyboard input later)
		while(running == 1){ //while the program is meant to be running
			System.out.println("\n\n########## MAIN MENU ##########\n"); // "\n" means: add new line
			System.out.println("Please press a key corresponding to the option and press enter to continue:");
			System.out.println("e.g. --> 2 <-- to find the max number.");
			System.out.println("[1]  Min \n[2]  Max \n[3]  Range \n[4]  Mean \n[5]  Median \n[6]  Mode " + 
					"\n[7]  Grade Distribution \n[99] Exit Program.");
			strChoice = input.nextLine(); 	//get input and move on when 'enter' is pressed
			strChoice = strChoice.trim(); 	//remove spaces if user accidently put spaces in, ignore them
			if(strChoice.length() == 0)		//test for an empty line it's invalid
			System.out.println("Select a choice:");
			choice = Integer.parseInt(strChoice); //"parseInt" converts the string to a number
			switch (choice){
				case 1: //Min
					System.out.println("\nOne of the Minimum mark(s) is:");
					System.out.println(calcMin(marklist));
					break; //break is used to get out the of the switch loop, the otherwise the next case is executed automatically
				case 2: //Max
					System.out.println("\nOne of the Maximum mark(s) is:");
					System.out.println(calcMax(marklist));
					break;
				case 3: //Range
					System.out.println("\nThe Range is:");
					System.out.println(calcRange(marklist));
					break;
				case 4: //Average
					System.out.println("\nThe Average is:");
					System.out.println(calcAverage(marklist));
					break;
				case 5: //Median
					System.out.println("\nThe Median is:");
					System.out.println(calcMedian(marklist));
					break;
				case 6: //Mode
					System.out.println("\nOne of the the most common value(s) are(mode):");
					System.out.println(calcMode(marklist));
					break;
				case 7: //gradeDistn
					System.out.println("\nThe Grade Distribution is:");
					int[] gradeDistnArray = gradeDistn(grades(), marklist); //process distribution of grades nad put into an array
					char[] gradeLetters = grades(); //get grade letters for output
					for (int i = 0; i < gradeDistnArray.length; i++) //for each grade
						System.out.print(gradeLetters[i] + ": " + gradeDistnArray[i] + "\n"); //Output the distribution
					break;
				case 99: //exit
						System.out.print("Ending program...");
						running = 0;
						//Thought of using "System.exit(0);" but that thought a while loop would be easier to use/maintain/control
						break;
						
				default: System.out.println("Invalid!"); //Number inputed doesn't respond to a choice.
			}
			if(running == 1){ // skip pressing enter on way out, it's not needed
				System.out.print("Press enter to go back to main menu.");
				input.nextLine(); //a pause is added so that user may review the result of last choice.
			}
		}
		System.out.print("Ended"); //last statement in program.
	}
	public static int calcMax(int[] anArray) { //a method named 'calcMax"
		int highestNumber = 0; 
		for (int i = 0; i < anArray.length; i++) //go through array passed to this method
			if (anArray[i] > highestNumber)	//if a bigger number is found, replace the highest number variable.
				highestNumber = anArray[i];
		return highestNumber; //give the highest number to the statement that called it
		}
	
	public static int calcMin(int[] anArray) { //similar to above
		int lowestNumber = 100; 
		for (int i = 0; i < anArray.length; i++)
			if (anArray[i] < lowestNumber)
				lowestNumber = anArray[i];
		return lowestNumber;
	}
	public static int calcRange(int[] anArray){
		int range;
		range = calcMax(anArray) - calcMin(anArray); //use the above two methods to find the range
		return range;
	}
	public static int calcAverage(int[] anArray){
		int total = 0;
		for (int i = 0; i < anArray.length; i++)
			total += anArray[i];	//get total
			int average = total / anArray.length; 	//total divided by the number of elements yields the average.
		return average;
	}
	public static double calcMedian(int[] anArray){
		double median;
		int[] sortedAnArray;
		sortedAnArray = sortArray(anArray); //sort the array first without touching the original array
		if(sortedAnArray.length % 2 == 1){ 	//% means divide by the number before it by the number it and the reminder is the result
		median = sortedAnArray[Math.round(sortedAnArray.length / 2)];
		//if the number of marks is odd then the middle is the median
		}
		else{
			median = ((double)sortedAnArray[(sortedAnArray.length / 2) - 1] + sortedAnArray[sortedAnArray.length / 2]) / 2;
			//if the number of marks is even then the average of the two middle marks is the median
		}
		return median;
	}
	public static int calcMode(int[] anArray){
	int mode = 0, highestCount = 0;	
	int[]  counts = new int[100]; //make an array of the same size of values used, so that each value can be counted
	for(int i = 0; i < anArray.length; i++)
		counts[anArray[i]]++; //each each value
	//find which value is the highest
	for(int i = 0; i < counts.length; i++)
		if (counts[i] > highestCount){
			highestCount = counts[i];
			mode = i;
		}
	return mode;
	}
	public static int[] sortArray(int[] anArray){
		int[] sortedAnArray = new int[anArray.length];
		System.arraycopy(anArray, 0, sortedAnArray, 0, anArray.length); //make duplicate array
		java.util.Arrays.sort(sortedAnArray); //sort the new array
		return sortedAnArray;
	}
	public static char[] grades() { //this method is designed to be changable as long as the rules below are followed.
		char[] gradeLetters = {'A', 	'B', 	'C', 	'D', 	'E'};
		// the lower boundary of the grade letter is the number below, in method "gradeDistn".
		//eg for 'C' which is awarded for a grade between 74-65, the lower boundary is 65.
		//the higher boundary is the number before the next.
		//eg. 'C' is 74 because 'B' is 75, so 'C' must be one lower.
		
		return gradeLetters;	
	}
	public static int[] gradeDistn(char[] gradeLetters, int[] anArray){
		final int[] gradeBoundaries = 			{85, 	75, 	65, 	50, 	0};
		//these are the grade boundaries above 	A		B		C		D		E
		//gradeBoundaries & gradeLetters are declared in different methods as java cannot return two different arrays easily.
		int[] distn = new int[gradeBoundaries.length]; //create the distribution array for processing
		//check the grades & boundaries are correct (same number of elements, it's the rule as stated in above method)
		if (!(gradeLetters.length == gradeBoundaries.length))
			System.out.println("\nGrades have been incorrectly edited, ensure they are the same number of grades to values!");
		else{
			//Initialize count distn array
			for (int i = 0; i < distn.length; i++){
				distn[i] = 0;
			}
			//count the grades and store them
			int ii;
			for(int i = 0; i < anArray.length; i++){ //go through each grade value 
				for(ii = 0; ii < distn.length; ii++)	//with grade value go through the grade boundaries and check if fits in each one
					if(ii == 0){ //separate test that a out of bounds array error is not done
						if(anArray[i] >= gradeBoundaries[ii] && anArray[i] <= 100){ //a test for hard coded highest mark of 100, all other boundaries are changeable.
							distn[ii]  += 1; //count it
						}
					}
					else{
							if(anArray[i] >= gradeBoundaries[ii] && anArray[i] <= (gradeBoundaries[ii - 1] -1)) //used for most/all other boundary checks
								distn[ii]  += 1; //count
						}
				}
		}
		return distn;
	}
}