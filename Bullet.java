/**
 * This is a class for the object Bullet in the game
 * @author	Jose Carlo G. Husmillo
 * @version	1.0
 * @date March 26, 2013
*/
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Bullet{
	private int type;
	private int bulletSpeed;
	private boolean currState;
	
	Image image;//look of the powerup
	int x,y;//coordinates
	int width, height;
	String direction;
	public Bullet(int x, int y,String d,int s){
		this.type = 1;
		this.bulletSpeed = s;
		this.currState = true;
		this.x = x;
		this.y = y;
		direction = d;
		appear();
	}
	
	public void appear(){
		try{
			this.image = ImageIO.read(new File("images/bullet.GIF"));
			width = image.getWidth(null);
			height = image.getHeight(null);
		}catch(Exception e){}
	}
	
	public void disappear(){
		this.image = null;
	}
	
	public void moveForward(){
		if(direction.equals("n")==true){
			this.y -= this.bulletSpeed;
			if (y < 0)
				currState = false;
		}
		else if(direction.equals("l")==true){
			this.x -= this.bulletSpeed;
			if (x < 0)
				currState = false;
		}
		else if(direction.equals("r")==true){
			this.x += this.bulletSpeed;
			if (x > 600)
				currState = false;
		}
		else if(direction.equals("s")==true){
			this.y += this.bulletSpeed;
			if (y > 500)
				currState = false;
		}
	}
	
	public void setBulletSpeed(int n){
		this.bulletSpeed = n;
	}
	
	public void setType(int type){
		this.type = type;
	}
	
	public void setState(boolean state){
		this.currState = state;
	}
	
	public int getBulletSpeed(){
		return this.bulletSpeed;
	}
	
	public int getType(){
		return this.type;
	}
	
	public boolean getState(){
		return this.currState;
	}
	
	public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
	
	public Rectangle bounds() {
        return new Rectangle(x, y, width, height);
    }
}