package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

/*
 * Ant will utilize the singleton design pattern.
 * Purpose is to only create one object of Ant.
 * One process can only access the ant
 */


public class Ant extends Moveable implements IFoodie 
{
	//attributes
	private int maximumSpeed;
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	
	//maintain a single global reference 
	private static Ant playerAnt;
	
	
	//private constructor; ensures no one can construct an Ant
	private Ant(Point location)
	{
		//size, location, color, heading, speed, foodLevel
		super(15, location, ColorUtil.rgb(5, 0, 0), 0,10, 50);
		
		this.maximumSpeed = 30;//constant maxSpeed
		
		//this.foodConsumptionRate = 15; //constant food consumption rate
		
		this.healthLevel = 10; //constant healthValue
		
		this.lastFlagReached = 1; //constant lastFlagReached value
	}
	
	//provides access to ant, creating one if necessary
	public static Ant getAnt(Point location)
	{
		if (playerAnt == null)
			playerAnt = new Ant(location);
		return playerAnt;
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
