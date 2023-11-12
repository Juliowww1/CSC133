package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class Moveable extends GameObject 
{
	//attributes
	private int heading;
	private int speed;
	private int foodLevel;
	
	//constructor
	Moveable(int size, Point location, int color, int heading, int speed, int foodLevel)
	{
		super(size, location, color);
		this.heading = heading;
		this.speed = speed;
		this.foodLevel = foodLevel;
	}
	
	public void move()
	{
		
		//if food level is 0, can't move
		if(foodLevel ==0)
		{
			System.out.println("Cannot move. Food Level is at 0");
		}
		else
		{
			//calculate difference in moving
			double deltaX = Math.cos(Math.toRadians(90 -heading))*speed;
			double deltaY = Math.sin(Math.toRadians(90 -heading))*speed;
			
			//set location for new values
			this.setLocation((float)(deltaX + this.getXLocation()), (float)(deltaY + this.getYLocation()));
		}
	}

	public int getHeading() {
		return heading;
	}

	public void setHeading(int heading) {
		this.heading = heading;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFoodLevel() {
		return foodLevel;
	}

	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	
	
	

}
