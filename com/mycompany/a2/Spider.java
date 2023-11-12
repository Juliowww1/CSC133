package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Spider extends Moveable 
{
	//create random generator object
	static Random random = new Random();
	
	
	//not food consumers
	Spider() 
	{
		//size, location, color, heading, speed, foodLevel
		super(getRandomSize(), getRandomLocation(), ColorUtil.MAGENTA, random.nextInt(360) , random.nextInt(6) + 5, 50);

	}
	
	//method to change heading randomly for spider
	public void changeHeadingRandomly()
	{
		
		int randomValue = random.nextInt(6);
		
		
		//must keep heading between 0-360
		if (this.getHeading() - randomValue <0)
		{
			this.setHeading(359);
		}
		else 
		{
			this.setHeading(this.getHeading() - random.nextInt(6));
		}
		
	}
	
	public void setFoodLevel()
	{
		
	}
	
	public String toString()
	{
		return "Spider: location=" + "(" + this.getLocation().getX()+ ","  + this.getLocation().getY() + ")"+
				", " + this.printColor()+
				", Heading = "+ this.getHeading() +
				", Speed = " + this.getSpeed() +
				", Size= " + this.getSize();
	}
	


}
