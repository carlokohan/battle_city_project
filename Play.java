/**
 * This program is an implementation of the game "Battle City" using java 
 * @author	Jose Carlo G. Husmillo
 * @version	1.0
 * @date March 26, 2013
*/
import javax.swing.JFrame;
import java.awt.event.*;
import javax.swing.Timer;
public class Play extends JFrame implements ActionListener{
	Timer t;
	RealGame x;
	String name;
    public Play(String name) {
		this.name = name;
		x = new RealGame(name);
        this.add(x);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setTitle("Battle City v. 1.0");
        setResizable(false);
        setVisible(true);
		t=new Timer(1,this);
		t.start();
    }

    public void actionPerformed(ActionEvent e){
		if((5 - x.player.currentNum()) < 0){
			//show different panels (levels) using card layout
		}
	}
}