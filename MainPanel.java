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

public class MainPanel extends JPanel implements ActionListener{
	JButton button1;
	JButton button2;
	Image bg;
	Image tank,tank2,tree,water;
	Image tank4 =null;
	String direction;
	int x = 10;
	int x2 = 48;
	int y = 500;
	int y2 = 600;
	Timer t = new Timer(20,this);
	public MainPanel(){
	//null layout
		this.setLayout(null);
		try{
			bg = ImageIO.read(new File("images/a.JPG"));
		}
		catch(Exception e){}
		
		button1 = new JButton("New Game");
		button1.setBounds(245,275,125,30);
		button2 = new JButton("Top Scorers");
		button2.setBounds(245,315,125,30);
		
		//add to panel
		this.add(button1);
		this.add(button2);
		this.setBackground(new Color(0,0,0));
		
		//load tanks
		try{
		tank = ImageIO.read(new File("images/Battle_City_Tank_Player1.PNG"));
		tank2 = ImageIO.read(new File("images/Battle_City_Tank_Enemy2.PNG"));
		tree =ImageIO.read(new File("images/Battle_City_trees.PNG"));
		}catch(Exception ee){}
		t.start();
	}
	//paint all components
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Font f = new Font("Georgia", Font.ITALIC, 12);
		
		g.setFont(f);
		g.drawImage(bg,8,0,null);//background
		g.drawImage(tank,x,y,this);
		g.drawImage(tank2,x2,y2,this);
		g.drawImage(tree,10,50,this);
		g.drawImage(tree,10,89,this);
		g.drawString("© Jose Carlo G. Husmillo", 435,463);
		setForeground(new Color(255,255,255));
		
        
		y-=2;
		y2-=3;
		if(y<=89){
			try{
				tank = ImageIO.read(new File("images/Battle_City_Tank_Player2.PNG"));
			}catch(Exception ee){}
		}
		if(y < -36){
			try{
				tank = ImageIO.read(new File("images/Battle_City_Tank_Player1.PNG"));
			}catch(Exception ea){}
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