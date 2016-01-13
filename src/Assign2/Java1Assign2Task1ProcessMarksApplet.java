package Assign2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class Java1Assign2Task1ProcessMarksApplet extends JApplet {
	private static final long serialVersionUID = 1L; //stop Eclipse complaining
	
	final static int MAX_LINES_ALLOWED = 22;		//remove lines on the console past this
	static JTextArea console = new JTextArea(); 	//<-- This will be null on reload (re-make it during execution)
	
	public Java1Assign2Task1ProcessMarksApplet() { } 	//constructor to instantiate the applet
	
	static int[] marklist = Java1Assign2Task1Marks.getMarks(); 			//make array
	
	public void start() {
		JTextArea costLabel = new JTextArea(); //the Cost display
		
		// Display the marks from Marks.class
		String strMarklist = "";
		
		strMarklist += "The Grades:\n";
		for (int i = 0; i < marklist.length; i++)
			if  ((i + 1) % 15 == 0) 				//15 per line
				strMarklist += marklist[i] + "\n";
			else
				strMarklist += marklist[i] + " ";
		
		costLabel.setText(strMarklist);
		costLabel.setEditable(false);
		console = new JTextArea(); //<-- Added to stop Null pointer Error on reload
		console.setEditable(false);		//read only console, input is is a via dialog boxes

		JButton resetButton = new JButton("Run Program");
		resetButton.addActionListener(new ActionListener() {	//run program on click of program
	        @Override 
			public void actionPerformed(ActionEvent e) {
	        	 runProgram ();
	          }
	       });

		JPanel leftPanel = new JPanel();			//setup the layout
		leftPanel.setLayout(new BorderLayout());	//parts commented out, as they are for the desktop version
		leftPanel.add(console);
		
		JPanel rightPanel = new JPanel();
		rightPanel.add(costLabel);
		rightPanel.add(resetButton, BorderLayout.SOUTH);

		//JFrame window = new JFrame();

		this.setLayout(new GridLayout(1,2));
		
		this.add(leftPanel, BorderLayout.WEST);
		this.add(rightPanel, BorderLayout.EAST);
		
		//this.setTitle("Zoo Cost Calculator"); //set title of program
		//window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //make the close button quit.
		this.setSize(650, 400); 
		//this.setLocationRelativeTo(null); //center the program on screen
		this.setVisible(true);	//done setting up, now to display
		this.setFocusable(true); //sometimes program does not focus, we fix it here
		

								
				
				//Begin main menu rendering 

		//use the run program button to start
		
	}
	
	public static void runProgram () {
		//Begin declarations
		int choice;
		String strChoice = "";
		while (!strChoice.equals("99")) {					//keep running until user exits
			writeToConsoleln("\n########## MAIN MENU ##########\n"); // "\n" means: add new line
			writeToConsoleln("Please press a key corresponding to the option.\nThen press enter to continue:");
			writeToConsoleln("e.g. --> 2 <-- to find the max number.");
			writeToConsoleln("[1]  Min \n[2]  Max \n[3]  Range \n[4]  Mean \n[5]  Median \n[6]  Mode " + 
					"\n[7]  Grade Distribution \n[99] Exit Program.");
			strChoice = getInput("Select a choice:? type '99' to quit");
			if(strChoice.length() == 0)		//test for an empty line it's invalid
			writeToConsoleln("Select a choice:");
			choice = Integer.parseInt(strChoice); //"parseInt" converts the string to a number
			switch (choice){
				case 1: //Min
					writeToConsoleln("\nOne of the Minimum mark(s) is:");
					writeToConsoleln(calcMin(marklist));
					break; //break is used to get out the of the switch loop, the otherwise the next case is executed automatically
				case 2: //Max
					writeToConsoleln("\nOne of the Maximum mark(s) is:");
					writeToConsoleln(calcMax(marklist));
					break;
				case 3: //Range
					writeToConsoleln("\nThe Range is:");
					writeToConsoleln(calcRange(marklist));
					break;
				case 4: //Average
					writeToConsoleln("\nThe Average is:");
					writeToConsoleln(calcAverage(marklist));
					break;
				case 5: //Median
					writeToConsoleln("\nThe Median is:");
					writeToConsoleln(calcMedian(marklist));
					break;
				case 6: //Mode
					writeToConsoleln("\nOne of the the most common value(s) are(mode):");
					writeToConsoleln(calcMode(marklist));
					break;
				case 7: //gradeDistn
					writeToConsoleln("\nThe Grade Distribution is:");
					int[] gradeDistnArray = gradeDistn(grades(), marklist); //process distribution of grades nad put into an array
					char[] gradeLetters = grades(); //get grade letters for output
					for (int i = 0; i < gradeDistnArray.length; i++) //for each grade
						writeToConsole(gradeLetters[i] + ": " + gradeDistnArray[i] + "\n"); //Output the distribution
					break;
				case 99: //exit
						writeToConsoleln("Ending program...");
						break;
				default: writeToConsoleln("Invalid!"); //Number inputed doesn't respond to a choice.
			}
		}
		writeToConsole("Ended"); //last statement in program.	
	}
	
	//GUI Methods
	
	public static void writeToConsoleln (double text){
		writeToConsoleln (String.valueOf(text)); //run String method
	}
	
	public static void writeToConsoleln (int text){
		writeToConsoleln (String.valueOf(text)); //run String method
	}
	
	public static void writeToConsole (String text){
		writeToConsole (text, false); //default no new line
	}
	
	public static void writeToConsoleln (String text){
		writeToConsole (text, true); //do write new line
	}
	
	public static void writeToConsole (String text, boolean newLine){
		String StrNewLine;
		if (newLine == false) {	//new line or not
			StrNewLine = "";
		} else {
			StrNewLine = "\n";
		}
		console.append(StrNewLine + text);
		//detect if exceeded the number of lines, cut off the excess
		int numberOfLinesToCut = console.getLineCount() - MAX_LINES_ALLOWED;
		if (numberOfLinesToCut > 0) {
			int end;
			try {
				end = console.getLineEndOffset(numberOfLinesToCut);
				console.replaceRange("", 0, end);
			} catch (BadLocationException e) {
				e.printStackTrace();
			} 
		}
	}
	public static String getInput(String text) {
		writeToConsoleln(text);							//copy text to teh console
		String answer = null;
		while (answer == null || answer.isEmpty()) {
			answer = JOptionPane.showInputDialog(text);	// show box to get text input
			if (answer == null) {
				answer = "99";						//if they cancel, exit program, otherwise ask again
			}
		}
		writeToConsoleln(answer);							//print answer to the console
		return answer;
	}

	// Other Methods
	
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
			writeToConsoleln("\nGrades have been incorrectly edited, ensure they are the same number of grades to values!");
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