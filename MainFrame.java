/**
 * This program is an implementation of the game "Battle City" using java 
 * @author	Jose Carlo G. Husmillo
 * @version	1.0
 * @date March 26, 2013
*/
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainFrame extends JFrame implements ActionListener{
	JPanel card;
	CardLayout layout;
	MainPanel panel1;
	Others panel2;
	NewGame panel3;
	RealGame panel4;
	Image bg;
	public MainFrame(){
		// frame's name
		super("Battle City v. 1.0");	
		
		// Set the dimensions
		this.setSize(600, 500);
		//center
		this.setLocationRelativeTo(null);
		card = new JPanel();
		layout = new CardLayout();
		card.setLayout(layout);
		
		panel1 = new MainPanel();
		panel2 = new Others();
		panel3 = new NewGame();
		//add panels to card layout
		card.add(panel1, "1");
		card.add(panel2, "2");
		card.add(panel3, "3");
		this.add(card);
		
		//add listeners for button
		panel1.button1.addActionListener(this);
		panel1.button2.addActionListener(this);
		panel2.button1.addActionListener(this);
		panel3.button1.addActionListener(this);
		panel3.button2.addActionListener(this);
		// Set the default close operation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		// Set the visibility
		this.setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e){
		if(e.getSource() == panel1.button1){
			layout.show(card, "3");
		}
		if(e.getSource() == panel1.button2){
			layout.show(card, "2");
		}
		if(e.getSource() == panel2.button1){
			layout.show(card, "1");
		}
		if(e.getSource() == panel3.button2){
			layout.show(card, "1");
		}
		if(e.getSource() == panel3.button1){
			String name = panel3.name.getText();
			this.setVisible(false);
			Play x = new Play(name);
		}
	}
}