/**
 * This is a class for the object Tank in the game
 * @author	Jose Carlo G. Husmillo
 * @version	1.0
 * @date March 26, 2013
*/
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

public class Tank{
	/**
	 * attributes
	*/
	private String tankType,friendlyFire;
	private int lifePoints,tanksDestroyed=0,score=0;
	private int moveSpeed,firePower;
	private boolean gotAPowerUp, shield;
	static int numberOfEnemyTanks = 0;
	private ArrayList bullets;
	private String direction="n";
	Image tank;//for the look of the tank
	int x,y,width,height,dx,dy,t1,t2,x2;//position of the tank
	//constructor
	public Tank(String type, int lPts, String friend,int speed, int power,int x,int y){
		
		this.tankType = type;
		this.lifePoints = lPts;
		this.friendlyFire = friend;
		this.moveSpeed = speed;
		this.firePower = power;
		this.x = x;
		this.y = y;
		appear();
		bullets = new ArrayList();
		
		
		//if(type.equals("enemy")==true)
			//disappear();
	}
	public void incEnemies(){
		this.numberOfEnemyTanks++;
	}
	
	public static int currentNum(){
		return numberOfEnemyTanks;
	}
	//return values of attributes
	public String getType(){
		return this.tankType;
	}
	
	public int getLife(){
		return this.lifePoints;
	}
	
	public String getFriendlyFire(){
		return this.friendlyFire;
	}
	
	public int getNumKilled(){
		return this.tanksDestroyed;
	}
	
	public int getScore(){
		this.score = 10 * this.tanksDestroyed;
		return this.score;
	}
	
	public int getSpeed(){
		return this.moveSpeed;
	}
	
	public int getPower(){
		return this.firePower;
	}
	
	public boolean getState(){
		return this.gotAPowerUp;
	}
	
	public boolean hasKilled(){
		return true;
	}
	
	public void incTankDest(){
		this.tanksDestroyed++;
	}
	
	public void incSpeed(){
		this.moveSpeed++;
	}
	
	public void incFPower(){
		this.firePower++;
	}
	
	public void incLP(){
		this.lifePoints++;
	}
	
	public boolean hasShield(){
		return this.shield;
	}
	
	public void setShield(){
		this.shield = true;//make tank appear with shield
	}
	
	public void setSpeed(int n){
		this.moveSpeed = n;
	}
	public void disappear(){
		this.tank = null;
	}
	public void appear(){
		Random g = new Random();
		try{
			if(this.tankType.equals("player") == true){
				this.tank = ImageIO.read(new File("images/Battle_City_Tank_Player1.PNG"));
				width = tank.getWidth(null);
				height = tank.getHeight(null);
			}
			else{
				x2 = g.nextInt(4);
				if(x2 == 0)
					this.tank = ImageIO.read(new File("images/Battle_City_Tank_Enemy2d.PNG"));
				if(x2 == 1)
					this.tank = ImageIO.read(new File("images/Battle_City_Tank_Enemy4.PNG"));
				else
					this.tank = ImageIO.read(new File("images/Battle_City_Tank_Enemy2d.PNG"));
				
				width = tank.getWidth(null);
				height = tank.getHeight(null);
			}
		}catch(Exception e){}
	}
	public boolean isDestroyed(){
		if(this.lifePoints == 0)
			return true;
		else return false;
	}
	public boolean gotPowerUp(){
		if(this.gotAPowerUp == true)
			return true;
		else return false;
	}
	
	public void decLP(){
		this.lifePoints--;
	}
	//bullet appears
	public void fireBullet(String direction){
		if(direction.equals("n")==true)
			this.bullets.add(new Bullet((x + width/2)-5, y,direction,this.moveSpeed+1));
		else if(direction.equals("l")==true)
			this.bullets.add(new Bullet(x , (y+height/2)-5,direction,this.moveSpeed+1));
		else if(direction.equals("r")==true)
			this.bullets.add(new Bullet(x +width, (y+height/2)-5,direction,this.moveSpeed+1));
		else if(direction.equals("s")==true)
			this.bullets.add(new Bullet((x +width/2)-5, y+height,direction,this.moveSpeed+1));
	}
	//move up
	public void moveForward(){
		this.y -= this.moveSpeed;
	}
	//move down
	public void moveBackward(){
		this.y += this.moveSpeed;
	}
	//move left
	public void moveToLeft(){
		this.x -= this.moveSpeed;
	}
	//move right
	public void moveToRight(){
		this.x += this.moveSpeed;
	}
	public String getD(){
		return this.direction;
	}
	public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return tank;
    }
	
	public ArrayList getBullets() {
        return this.bullets;
    }
	
	public Rectangle bounds() {
        return new Rectangle(x, y, width, height);
    }
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            fireBullet(direction);
        }

        if (key == KeyEvent.VK_A) {
			//moveToLeft();
			dx = -moveSpeed;
			direction="l";
			try{
			if(this.tankType.equals("player") == true){
				this.tank = ImageIO.read(new File("images/Battle_City_Tank_Player1l.PNG"));
				width = tank.getWidth(null);
				height = tank.getHeight(null);
			}
			}catch(Exception ess){}
        }

        if (key == KeyEvent.VK_D) {
			//moveToRight();
			dx = moveSpeed;
			direction="r";
			try{
			if(this.tankType.equals("player") == true){
				this.tank = ImageIO.read(new File("images/Battle_City_Tank_Player1r.PNG"));
				width = tank.getWidth(null);
				height = tank.getHeight(null);
			}
			}catch(Exception ea){}
        }

        if (key == KeyEvent.VK_W) {
			//moveForward();
			dy = -moveSpeed;
			direction="n";
			try{
			if(this.tankType.equals("player") == true){
				this.tank = ImageIO.read(new File("images/Battle_City_Tank_Player1.PNG"));
				width = tank.getWidth(null);
				height = tank.getHeight(null);
			}
			}catch(Exception eb){}
        }

        if (key == KeyEvent.VK_S) {
			//moveBackward();
			dy = moveSpeed;
			direction="s";
			try{
			if(this.tankType.equals("player") == true){
				this.tank = ImageIO.read(new File("images/Battle_City_Tank_Player1d.PNG"));
				width = tank.getWidth(null);
				height = tank.getHeight(null);
			}
			}catch(Exception ec){}
        }
    }
	public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_A) {
            dx = 0;
        }

        if (key == KeyEvent.VK_D) {
            dx = 0;
        }

        if (key == KeyEvent.VK_W) {
            dy = 0;
        }

        if (key == KeyEvent.VK_S) {
            dy = 0;
        }
    }
	public void move() {
		t1=x;
		t2=y;
        x += dx;
        y += dy;

        if (x < 0) {
            x = 0;
        }

        if (y < 0) {
            y = 0;
        }
		if(x>= 564){
			x = 564;
		}
		if(y>=437){
			y=437;
		}
    }
	
}