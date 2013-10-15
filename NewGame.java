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
public class NewGame extends JPanel implements ActionListener{
	JButton button1;
	JButton button2;
	JTextArea name;
	Image bg;
	Image tank,tank2;
	int x = 420;
	int x2 = 470;
	int y = 500;
	int y2 = 600;
	Timer t = new Timer(20,this);
		
	public NewGame(){
	//null layout
		this.setLayout(null);
		try{
			bg = ImageIO.read(new File("images/a.JPG"));
		}
		catch(Exception e){}
		
		name = new JTextArea("");
		name.setBounds(245,275,125,22);
		button1 = new JButton("Game Start!");
		button1.setBounds(245,315,125,30);
		button2 = new JButton("Back to Main Menu");
		button2.setBounds(225,353,163,30);
		
		this.add(name);
		this.add(button1);
		this.add(button2);
		this.setBackground(new Color(0,0,0));
		
		
		try{
		tank = ImageIO.read(new File("images/Battle_City_Tank_Player1.PNG"));
		tank2 = ImageIO.read(new File("images/Battle_City_Tank_Enemy1.PNG"));
		}catch(Exception ee){}
		t.start();
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Font f = new Font("Georgia", Font.ITALIC, 18);
		
		g.setFont(f);
		g.drawImage(bg,8,0,null);
		g.drawString("Enter name:", 140,290);
		g.setFont(new Font("Georgia", Font.ITALIC, 12));
		g.drawString("© Jose Carlo G. Husmillo", 435,463);
		setForeground(new Color(255,255,255));//font color for drawString
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