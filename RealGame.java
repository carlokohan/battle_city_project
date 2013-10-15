/**
 * This program is an implementation of the game "Battle City" using java 
 * @author	Jose Carlo G. Husmillo
 * @version	1.0
 * @date March 26, 2013
*/
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;
public class RealGame extends JPanel implements ActionListener{
	/**
	* Panel for the game proper
	*/
	String name;//player's name
    Tank player;//player tank
	Base base;//base
	PowerUp power;//Power Ups
    ArrayList enemy;//list of enemies
	ArrayList terrain;
    boolean gameover;//responsible to keep playing the game
	Timer t1;//timer for player
	Random gen;
	int tmp,tmp2,current,x=150,y=500;
	//(x,y) corrdinates of the terrain
	int coords[][] = 
	{
	{50,50},{150,150},{50,89},{150,189},{150,228},{0,360},{555,360},{555,321},{555,282},{189,150},{228,150},
	{267,150},{0,300},{0,261},{89,50},{200,50},{239,50},{278,50},{555,100},{300,300},{339,300},{39,360},{78,360},
	{516,360},{470,320},{400,100},{189,189},{228,228},{140,0},{410,0},{400,228},{228,189},{267,189},{306,189},
	{439,100},{439,139},{52,200},{91,200},{153,342},{120,429},{483,236},{522,236},{439,429},{272,344}
	};
	//for surrounding the base
	int xy[][] = 
	{
	{249,429},{249,390},{285,390},{324,390},{324,429}
	};
	//enemy's starting points
	int xy2[][] =
	{
	{0,0},{300,0},{555,0}
	};
	
	public RealGame(String name) {
		gen = new Random();
		this.name = name;
        this.addKeyListener(new TAdapter());
        this.setFocusable(true);
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.gameover = false;
        this.setSize(600, 500);

		
        this.player = new Tank("player",3,"friend",1,1,210,427);
		this.base = new Base();
		this.power = new PowerUp("fire",1,210,427);
        makeTerrain();
		//create enemy on certain map positions
		makeEnemy();
		t1 = new Timer(5, this);
        t1.start();
    }
	/**
	 * Initialize terrain
	*/
	public void makeTerrain() {
        terrain = new ArrayList();
		int i=0;
		//make surrounding bricks for base
		while(i<xy.length) {
			int type = 0;//brick type
			//add to list, later on we will draw the terrain
            terrain.add(new Terrain(type,xy[i][0], xy[i][1]));
			i++;
        }
		i=0;
		//terrain for game
        while(i<coords.length) {
			int type = gen.nextInt(4);
            terrain.add(new Terrain(type,coords[i][0], coords[i][1]));
			i++;
        }
    }
	
	/**
	 * Initialize enemies
	*/
	public void makeEnemy() {
        enemy = new ArrayList();
		int i=0;
		while(i<3){
			enemy.add(new Tank("enemy",1,"notfriend",1,1,xy2[current][0],xy2[current][1]));
			current++;
			i++;
			if(current==3)
				current=0;
		}
    }
	/**
	 * add enemy one by one
	*/
	public void addEnemy() {
		enemy.add(new Tank("enemy",1,"notfriend",1,1,xy2[current][0],xy2[current][1]));
		current++;
		if(current==3)
			current=0;
    }
	
	public void paint(Graphics g){
		super.paint(g);
		
		if(!gameover){
			Graphics2D g2 = (Graphics2D)g;
			//draw tank
			if (!player.isDestroyed())
                g2.drawImage(player.getImage(), player.getX(), player.getY(), this);
			//draw base
			if(base.getLife() > 0)
				g2.drawImage(base.getImage(),base.getX(),base.getY(),this);
			
			//add one enemy if one has been destroyed
			if((enemy.size() > 0 && enemy.size() < 3) && (player.currentNum())<30)
				addEnemy();
			
			//draw enemies
			for (int i = 0; i < enemy.size(); i++) {
                Tank a = (Tank)enemy.get(i);
                if (!a.isDestroyed())
                    g2.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
			//draw terrain
			for (int i = 0; i < terrain.size(); i++) {
                Terrain t = (Terrain)terrain.get(i);
                if (t.getState())
                    g2.drawImage(t.getImage(), t.getX(), t.getY(), this);
            }
			
			ArrayList b = player.getBullets();
			//draw bullets
			for (int i = 0; i < b.size(); i++) {
				Bullet shell = (Bullet)b.get(i);
				g2.drawImage(shell.getImage(), shell.getX(), shell.getY(), this);
            }
			//draw powerup	
			if(tmp >= 1000 && power != null){
				g2.drawImage(power.getImage(),power.getX(),power.getY(),this);
			}
			//print lives
			g2.setColor(Color.GRAY);
			g2.drawString("Tank lives: "+player.getLife()+" Base lives: "+base.getLife(), 440,464);
			g2.drawString("Enemy left: " + (31-player.currentNum()), 0,464);
		}
		else{
			Graphics2D g2 = (Graphics2D)g;
			Font s = new Font("Arial", Font.BOLD, 24);
			g2.setColor(Color.white);
			g2.setFont(s);
			g2.drawString("Game Over! Tanks Killed: " + player.getNumKilled(), x,y);
			this.y--;
			if(this.y < 250)
				y=250;
		}
		Toolkit.getDefaultToolkit().sync();
        g.dispose();
		
	}
	
	public void actionPerformed(ActionEvent e) {
		tmp++;
		ArrayList b2 = player.getBullets();
		for (int i = 0; i < b2.size(); i++) {
            Bullet f = (Bullet) b2.get(i);
            if (f.getState()) 
                f.moveForward();
            else b2.remove(i);
        }
		//no more base lives
		if(base.getLife() < 1){
			gameover = true;
		}
		/**
		* This determines if the terrain is still present, if not, remove to array list
		*/
		for (int i = 0; i < terrain.size(); i++) {
            Terrain f = (Terrain) terrain.get(i);
            if (!f.getState()) 
				terrain.remove(i);
        }
		
		/**
		* This determines if the enemy is still present, if not, remove to array list
		*/
		for (int i = 0; i < enemy.size(); i++) {
            Tank f = (Tank) enemy.get(i);
            if (f.isDestroyed()){
				enemy.remove(i);
				player.incEnemies();
				if((31-player.currentNum()) < 0)
					gameover = true;
			}
        }
		
		//show power ups
		if(tmp >= 1000){
			if(power.getState() == false)
				power = null;
		}
		
		player.move();
		lookForCollisions();
		this.repaint();
    }
	
	//check if objects collide
	public void lookForCollisions() {
	/**
	 * Using Rectangle class is like you will surround the image with an invisible rectangle. It is suitable for collision detection
	  * because of .intersects method
	*/
        Rectangle rect1 = player.bounds();
        ArrayList list1 = player.getBullets();
		//check if it intersects with a power up
		if(tmp>=1000){
			Rectangle rect2 = power.bounds();
				
			if(rect1.intersects(rect2)){
				if(power.getType().equals("fire") == true){
					power.increaseFirepower(player);
					power.setState(false);
					power.x = 3000;
					power.y = 3000;
				}	
				tmp = 0;
				this.power = new PowerUp("fire",1,210,427);
			}	
		}
		
		for (int i = 0; i<terrain.size(); i++) {
            Terrain t = (Terrain) terrain.get(i);
            Rectangle rect2 = t.bounds();

            if (rect1.intersects(rect2)) {
				if(t.getType().equals("tree") == false){
					// don't move because it is not a tree type of terrain
					//t1 and t2 are previous values of x and y just before dx & dy were added to x & y respectively
					player.x = player.t1;
					player.y = player.t2;
				}
            }
        }
		
		for (int i = 0; i<enemy.size(); i++) {
			//for each enemy tank the player is colliding with
            Tank t = (Tank)enemy.get(i);
            Rectangle rect2 = t.bounds();

            if (rect1.intersects(rect2)) {
				// don't move because it is a tank you're colliding with
				//t1 and t2 are previous values of x and y just before dx & dy were added to x & y respectively
				player.x = player.t1;
				player.y = player.t2;
            }
        }
		
        for (int i = 0; i < list1.size(); i++) {
			//for each bullet by the player in the game
            Bullet shell = (Bullet) list1.get(i);

            Rectangle rect2 = shell.bounds();
			//check if the bullet intersects with all terrain in the game
            for (int j = 0; j<terrain.size(); j++) {
                Terrain t = (Terrain)terrain.get(j);
                Rectangle rect3 = t.bounds();

                if (rect2.intersects(rect3)) {
					//hide
					if(t.getType().equals("brick") == true){
						shell.setState(false);
						t.setState(false);
					}
					else if(t.getType().equals("metal") == true && player.getPower() == 1){
						shell.setState(false);
					}
					else if(t.getType().equals("tree") == true && player.getPower() > 1){
						shell.setState(false);
						t.setState(false);
					}
					else if(t.getType().equals("metal") == true && player.getPower() > 1){
						shell.setState(false);
						t.setState(false);
					}
                }
            }
        }
		
		for (int i = 0; i < list1.size(); i++) {
			//for each bullet by the player in the game
            Bullet shell = (Bullet) list1.get(i);

            Rectangle rect2 = shell.bounds();
			//check if the bullet intersects with all enemies in the game
            for (int j = 0; j<enemy.size(); j++) {
                Tank t = (Tank)enemy.get(j);
                Rectangle rect3 = t.bounds();

                if (rect2.intersects(rect3)) {
					t.decLP();//destroy enemy
					shell.setState(false);
					player.incTankDest();//increment tanks destroyed by player
                }
            }
        }
		
		for (int i = 0; i < list1.size(); i++) {
			//for each bullet by the player in the game
            Bullet shell = (Bullet)list1.get(i);
            Rectangle rect2 = shell.bounds();
			Rectangle rect3 = base.bounds();
			//check if the bullet collides with the base
            if (rect2.intersects(rect3)) {
				shell.setState(false);
				base.baseDestroyed();//decrement life
            }
        }
    }
	
	private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
          player.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}