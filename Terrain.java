/**
 * This is a class for the object Terrain in the game
 * @author	Jose Carlo G. Husmillo
 * @version	1.0
 * @date March 26, 2013
*/
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Terrain{
	private String type;
	private boolean currState;
	
	Image terrain;//look of the powerup
	int x,y,width,height;//coordinates
	
	public Terrain(int n,int x,int y){
		if(n==0)
			this.type = "brick";
		if(n==1)
			this.type = "metal";
		if(n==2)
			this.type = "water";
		if(n==3)
			this.type = "tree";
		this.x=x;
		this.y=y;
		this.currState = true;
		appear();
	}
	public boolean terrainDestroyed(Bullet e){
		/**
		 if(bullet.getType == 1 && this.getType == "brick"){
		 
		 }
		*/
		return true;
	}
	
	public void appear(){
		try{
			if(this.type.equals("brick") == true){
				this.terrain = ImageIO.read(new File("images/Battle_City_bricks.PNG"));
				width = terrain.getWidth(null);
				height = terrain.getHeight(null);
			}
			if(this.type.equals("metal") == true){
				this.terrain = ImageIO.read(new File("images/Battle_City_wall.PNG"));
				width = terrain.getWidth(null);
				height = terrain.getHeight(null);
			}
			if(this.type.equals("water") == true){
				this.terrain = ImageIO.read(new File("images/Battle_City_water.PNG"));
				width = terrain.getWidth(null);
				height = terrain.getHeight(null);
			}
			if(this.type.equals("tree") == true){
				this.terrain = ImageIO.read(new File("images/Battle_City_trees.PNG"));
				width = terrain.getWidth(null);
				height = terrain.getHeight(null);
			}
		}catch(Exception e){}
	}
	
	public boolean isHit(Bullet e){
		//if(e.x >= this.x){
		//	return true;
		//}
		return false;
	}
	
	public void setType(String type){
		this.type = type;
	}
	public void setState(boolean state){
		this.currState = state;
	}
	
	public String getType(){
		return this.type;
	}
	
	public boolean getState(){
		return this.currState;
	}
	
	public Image getImage() {
        return terrain;
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