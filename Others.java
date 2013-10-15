/**
 * This program is an implementation of the game "Battle City" using java 
 * @author	Jose Carlo G. Husmillo
 * @version	1.0
 * @date March 26, 2013
*/
import java.awt.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;

public class Others extends JPanel implements ActionListener{
	JButton button1;
	JLabel label1;
	JLabel label2;
	JTextArea field1;
	JTextArea field2;
	Image tank,tank2;
	int x = 10;
	int x2 = 550;
	int y = 500;
	int y2 = 600;
	Timer t = new Timer(10,this);
	public Others(){
	//null layout
		this.setLayout(null);
		
		label1 = new JLabel("Top Scorers:");
		label2 = new JLabel("How to play:");
		button1 = new JButton("Back to Main Menu");
		field1 = new JTextArea("Scorers",10,20);
		field2 = new JTextArea("destroy all tanks",10,20);
		
		button1.setBounds(235,410,175,30);
		field1.setBounds(100,40,375,150);
		field2.setBounds(100,240,375,150);
		field1.setEditable(false);
		field2.setEditable(false);
		this.add(button1);
		this.add(label1);
		this.add(label2);
		this.add(field1);
		this.add(field2);
		//this.setFont(new Color(Color.white));
		this.setBackground(new Color(0,0,0));
		
		try{
		tank = ImageIO.read(new File("images/Battle_City_Tank_Player2.PNG"));
		tank2 = ImageIO.read(new File("images/Battle_City_Tank_Enemy4.PNG"));
		}catch(Exception ee){}
		t.start();
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Font f = new Font("Georgia", Font.ITALIC, 18);

		g.setFont(f);
		g.drawString("Top Scorers:", 250,25);
		g.drawString("How to play:", 250,220);
		setForeground(new Color(255,255,255));
		g.setFont(new Font("Georgia", Font.ITALIC, 12));
		g.drawString("© Jose Carlo G. Husmillo", 435,463);
		g.drawImage(tank,x,y,this);
		g.drawImage(tank2,x2,y2,this);
		
		y-=2;
		y2-=3;
		if(y < -36){
			y = 500;
		}
		if(y2 < -36){
			y2 = 600;
		}
		
	}
	
	public void actionPerformed(ActionEvent e){
		
		if(e.getSource()==t){
			this.repaint();
		}
	}
}