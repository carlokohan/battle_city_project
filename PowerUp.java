/**
 * This is a class for the object PowerUp in the game
 * @author	Jose Carlo G. Husmillo
 * @version	1.0
 * @date March 26, 2013
*/
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class PowerUp{
	private String type;
	private boolean currState;
	
	Image powerup;//look of the powerup
	int x,y,width,height;//coordinates
	
	public PowerUp(String type, int n,int x, int y){
		this.type = type;
		this.currState = true;
		this.x=x;
		this.y=y;
		appear(n);
	}
	
	public void appear(int n){
		try{
			if(n==1)
				this.powerup = ImageIO.read(new File("images/Battle_City_Powerup_Star.PNG"));
			width = powerup.getWidth(null);
			height = powerup.getHeight(null);
		}catch(Exception e){}
	}
	
	public void disappear(){
	
	}
	
	public boolean isCaught(Tank e){
		if(e.x >= this.x){
			return true;
		}
		return false;
	}
	//destroy enemy tanks power up
	public void destroyEnemy(Tank e){
		if(this.type == "destroy")
			e.tank = null;
	}
	//+1 to player's life
	public void plusOneToLife(Tank e){
		if(this.type == "plus")
			e.incLP();
	}
	//+1 to firepower and moveSpeed
	public void increaseFirepower(Tank e){
		if(this.type == "fire"){
			e.incFPower();
			e.incSpeed();
		}
	}
	//shield the base
	public void shieldBase(){
		//make the surrounding terrain type of the base be a metal
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void setState(boolean state){
		this.currState = true;
	}
	
	public String getType(){
		return this.type;
	}
	
	public boolean getState(){
		return this.currState;
	}
	
	public Image getImage() {
        return powerup;
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