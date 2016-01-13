package Assign1;
/*
 * Assignment 1 - Task 2
 * Joshua Graham
 * ITC-206 (Distance)
 * ID: 11490893
 * Version 2.1 with GUI
 * 28/12/2015
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.text.BadLocationException;

public class Java1Assign1Task2ZooEntryApplet extends JApplet{ 
	private static final long serialVersionUID = 1L;//stop Eclipse complaining
	static boolean globalAllowChild = true;			//track whether we are allow children this time
	static int globalCost = 0;						//track the cost
	final static int MAX_LINES_ALLOWED = 22;		//remove lines on the console past this
	static JTextArea console = new JTextArea(); 	//<-- This will be null on reload (re-make  it during execution)
	public Java1Assign1Task2ZooEntryApplet () {	}	//constructor to instantiate the applet
	public void start() {
		JTextArea costLabel = new JTextArea(); //the Cost display
		costLabel.setText(
				"The entry charges to a zoo are:\n\n" +
				"Children 5 years old and younger: free\n" +
				"Accompanied children from 6 to 15 years old: $2 each\n" +
				"Unaccompanied children from 6 to 15 years old: $5 each\n" +
				"Adults from 16 to 59 years old: $10 each\n" +
				"Seniors from 60 years and older: $8 each\n\n" +
				"Note:\n" +
				"Each accompaning child \n"
				+ "can only have 1 accompanying adult.\n" +
				"The Child must be entered first."
				);
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
		//use the run program button to start
	}
	
	public static void runProgram () {
		int[] types = {0,0,0,0,0,0,0}; //Kid, U-Child, A-Child, U-adult, A-Adult, U-Elderly, A-Elderly 
		types = inputPerson(types);		//get inputs
		printTotal (types);				//calculate and print summary
	}
	
	public static void writeToConsole (String text){
		console.append("\n" + text);
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
		writeToConsole(text);							//copy text to teh console
		String answer = null;
		while (answer == null || answer.isEmpty()) {
			answer = JOptionPane.showInputDialog(text);	// show box to get text input
			if (answer == null) {
				answer = "exit";						//if they cancel, exit program, otherwise ask again
			}
		}
		writeToConsole(answer);							//print answer to the console
		return answer;
	}
	
	public static int[] inputPerson (int[] types) {
		String text = "";
		String extraText = "";
		while (!text.equals("exit")) {					//keep running until user exits
			if (globalAllowChild == false){
				extraText = "Accompanied adult:\n";		//getting accompanying adult now
			} else {
				extraText = "";
			}
			text = getInput(extraText + "Next Person - Whats your age? type 'exit' to quit");
			if (text.equals("exit")) {					//exit program
				break;
			} else {
				String type = null;
				try {
					int age = Integer.parseInt(text);
					if (age <= 5) {
						if (globalAllowChild == false) {
							writeToConsole("< 5 year olds can't be the adult");
						} 
						type = "K"; //kid
					} else if (age <= 15){
						//6 to 15
						if (globalAllowChild == false) {
							writeToConsole("< 16 year olds can't be the adult");
						} else {
							while (!text.equalsIgnoreCase("Y") && !text.equalsIgnoreCase("N")) {
								text = getInput("Accompanied or not? (Y or N)");
								if (text.equalsIgnoreCase("Y")) {
									type = "AC"; //Accompanied child
									globalAllowChild = false; //re-enter the loop with different variable
								} else if (text.equalsIgnoreCase("N")){
									//not accompanied
									type = "UC"; //UnAccompanied child
								} else {
									writeToConsole("Incorrect entry");
								}
							}
						}
					} else if (age <= 59) {
						//16 to 59
						if (globalAllowChild == false) {
							type = "AA"; //Accompanying Adult
							globalAllowChild = true;
						} else {
							type = "UA"; //UnAccomping Adult
						}
					} else {
						//59+
						if (globalAllowChild == false) {
							type = "AE"; //Accompanying  Elderly
							globalAllowChild = true;
						} else {
							type = "UE"; //UnAccompanying Elderly
						}
					}
				} catch (NumberFormatException e) {
					writeToConsole("Bad entry.");
				}
				types = addToTypes(type, types);	//put into array
				
			}
		} //end while loop
		return types;
	}
	public static int[] addToTypes(String type, int[] types) {
		if (type == "K"){			//kid)
			types[0] += 1;
		} else if (type == "UC") {	//UnAccompanied child
			types[1] += 1;
		} else if (type == "AC") { 	//Accompanied child
			types[2] += 1;
		} else if (type == "AA") { 	//Accompanying Adult
			types[3] += 1;
		} else if (type == "UA") { 	//UnAccompanied adult
			types[4] += 1;
		} else if (type == "AE") { 	//Accompanying  Elderly
			types[5] += 1;
		} else if (type == "UE") { 	//UnAccomping  Elderly
			types[6] += 1;
		}
		return types;
	}
	public static void printTotal (int[] types) {
		int[] costs = new int[5];
		costs[0] = types[0] * 0; //kids are free
		costs[1] = types[1] * 5; //$5 per UnAcommpanied Children
		costs[2] = types[2] * 2; //$2 per Accompanied Children
		costs[3] = (types[3] + types[4]) * 10; //$10 for per adult (both UnAcommpanied & Accompanied)
		costs[4] = (types[5] + types[6]) * 8; //$8 per elderly (both UnAcommpanied & Accompanied)
		
		writeToConsole("\nSummary:\n");
		writeToConsole("$"+costs[0]+" - "+types[0] + " Kids");
		writeToConsole("$"+costs[1]+" - "+types[1] +" UnAcommpanied Children");
		writeToConsole("$"+costs[2]+" - "+types[2] +" Acommpanied Children");
		writeToConsole("$"+costs[3]+" - "+(types[3] + types[4]) + " Adults ("+types[3]+" Accompanied "+types[4]+" UnAccompanied)");
		writeToConsole("$"+costs[4]+" - "+(types[5] + types[6]) + " Elderly ("+types[5]+" Accompanied "+types[6]+" UnAccompanied)");
		int total = 0;
		for (int cost: costs) {
			total += cost;		//add up the cost
		}
		writeToConsole("\nTotal cost is: $" + total);
		writeToConsole("Thank for using this program.");
	}
}
