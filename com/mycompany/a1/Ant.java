package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Ant extends Moveable implements IFoodie 
{
	//attributes
	private int maximumSpeed;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	
	
	
	//pass a location for the ant to spawn in
	Ant(Point location)
	{
		//size, location, color, heading, speed, foodLevel
		super(15, location, ColorUtil.rgb(5, 0, 0), 0,10, 50);
		
		this.maximumSpeed = 30;//constant maxSpeed
		
		//this.foodConsumptionRate = 15; //constant food consumption rate
		
		this.healthLevel = 10; //constant healthValue
		
		this.lastFlagReached = 1; //constant lastFlagReached value
	}
	


	public int getMaximumSpeed() 
	{
		return maximumSpeed;
	}


	public void setMaximumSpeed(int maximumSpeed) 
	{
		this.maximumSpeed = maximumSpeed;
	}


	public int getFoodConsumptionRate() 
	{
		return foodConsumptionRate;
	}


	public void setFoodConsumptionRate() 
	{
		//come back later. changeable?
		this.foodConsumptionRate = 5;
	}
	


	public int getHealthLevel() 
	{
		return healthLevel;
	}


	public void setHealthLevel(int healthLevel) 
	{
		this.healthLevel = healthLevel;
	}


	public int getLastFlagReached() 
	{
		return lastFlagReached;
	}


	public void setLastFlagReached(int lastFlagReached) 
	{
		this.lastFlagReached = lastFlagReached;
	}
	
	public String toString()
	{
		return "Ant: location=" + "(" + this.getLocation().getX()+ ","  + this.getLocation().getY() + ")" +
				", " + this.printColor()+
				", Heading = "+ this.getHeading() +
				", Speed = " + this.getSpeed() +
				", Size= " + this.getSize() +
				", MaxSpeed=" + this.getMaximumSpeed()+
				", FoodConsumptionRate=" + this.getFoodConsumptionRate();
				
	}
	
	


}
