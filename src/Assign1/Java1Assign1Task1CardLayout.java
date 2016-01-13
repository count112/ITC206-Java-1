package Assign1;
/*
Assignment 1 - Task 1
Joshua Graham
ITC-206 (Distance)
ID: 11490893
 */
//Import some functions used for the custom label, font and html.
import javax.swing.*;
import java.awt.*;
public class Java1Assign1Task1CardLayout {
	public static void main(String[] args) {
		//image location
		ImageIcon icon1 = new ImageIcon("image\\speed_profit.jpg");
		
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
						JOptionPane.INFORMATION_MESSAGE,   icon1);        
	}

}
