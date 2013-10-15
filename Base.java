/**
 * This is a class for the object Base in the game
 * @author	Jose Carlo G. Husmillo
 * @version	1.0
 * @date March 26, 2013
*/
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
public class Base{
	private int lifePoints;//always start with 3
	
	Image pic;//look of the base
	int x,y,width,height;//coordinates
	
	//constructor
	public Base(){
		this.lifePoints = 3;
		this.x = 285;
		this.y = 429;
		appear();
	}
	//decrease base's life points
	public void baseDestroyed(){
		this.lifePoints--;
	}
	
	public void appear(){
		try{
			this.pic = ImageIO.read(new File("images/base.PNG"));
			width = pic.getWidth(null);
			height = pic.getHeight(null);
		}catch(Exception e){}
	}
	
	public boolean isHit(){
		return true;
	}
	//called when tank acquired a power up that increases this class' lifepoints
	public void setLife(){
		this.lifePoints++;
	}
	
	public int getLife(){
		return this.lifePoints;
	}
	
	public Image getImage() {
        return pic;
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