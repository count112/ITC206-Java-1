package Assign1;
/*
 * Assignment 1 - Task 1
 * Joshua Graham
 * ITC-206 (Distance)
 * ID: 11490893
 * Version 2 with GUI
 * 28/12/2015
 */
//Import some functions used for the custom label, font and html.
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Java1Assign1Task1CardLayoutApplet extends JApplet{
	private static final long serialVersionUID = 1L;//stop Eclipse complaining
	public Java1Assign1Task1CardLayoutApplet() {}	//constructor to instantiate the applet
	public void start() {
		JButton resetButton = new JButton("Run Program"); //Button to run the program
		resetButton.setPreferredSize(new Dimension(120, 50));
		resetButton.addActionListener(new ActionListener() {	//run program on click of program
	        @Override 
			public void actionPerformed(ActionEvent e) {
	        	 runProgram ();
	          }
	       });
		this.setLayout(new GridBagLayout()); //Gridbag used os the size on the button sticks
		this.add(resetButton);
		this.setVisible(true);	//done setting up, now to display
		this.setFocusable(true); //sometimes program does not focus, we fix it here
		//use the run program button to start
	}
	public void runProgram() {
		ImageIcon icon = null;
		try {
			//image location, image icon used to add to the dialog box
			icon = new ImageIcon(ImageIO.read(getClass().getResource("image/speed_profit.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//content of message, note &emsp is a tab and string is formatted for a box on the right and added white space here for readability
		String message = "<html>&emsp&emsp&emsp&emsp&emsp&emsp&emsp<b>Josh's Fast & Cheap Delivery</b>" +
						 "<br>The Supa Fast Package Transmission Company working on the cheap." +
						 "<br>													&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp - - - - - - - - - - - - - - - - - - -" +
						 "<br>-&emsp Next day delivery avilable.				&emsp&emsp&emsp&emsp&emsp&emsp																	|&emsp Your local Office: 			&emsp&emsp&emsp&emsp|"  + 
			        	 "<br>-&emsp Will match competitor price.				&emsp&emsp&emsp&emsp&emsp																		| &emsp<i>17 Hoskin St </i>&emsp&emsp&emsp&emsp&emsp&emsp|" +
			        	 "<br>-&emsp Care is always taken.						&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp														| &emsp<i>Wangaratta VIC 3676</i>			&emsp&emsp |" +
			        	 "<br>													&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp&emsp - - - - - - - - - - - - - - - - - - -" +
			        	 "<br>Go to to your <u>local office</u> or call us on <u>1234-5678</u>." +
			        	 "<br>ABN: 0987654321 &emsp&emsp www.JoshFastCheapDelivery.com.au" +
			        	 "</html>";
		//The display code
		JLabel label = new JLabel(message);  
		label.setFont(new Font("serif", Font.PLAIN, 14));  //a different font is used as normal font doesn't support underlining etc.
		JOptionPane.showMessageDialog(null, label, "Josh's Fast & Cheap Delivery",
				JOptionPane.INFORMATION_MESSAGE, icon);        
	}

}
