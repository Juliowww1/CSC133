package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;

public class FoodStation extends Fixed 
{
	//capacity is proportional to size of food station
	private int capacity;
	
	//create random generator object
	Random random = new Random();
	
	//constructor
	FoodStation()
	{
	
		super(getRandomSize(), getRandomLocation() , ColorUtil.GREEN);
		
		//capacity is proportional to size
		capacity = this.getSize();
		
	
	}

	public int getCapacity() 
	{
		return capacity;
	}
	
	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}
	
	public String toString()
	{
		return "FoodStation: location= " + "(" + this.getLocation().getX()+ ","  + this.getLocation().getY() + ")"+
				", " + this.printColor()+
				", Size= " + this.getSize() +
				", Capacity =" +this.getCapacity();
	}
	
/*	
	//setting random capacity
	public void setCapacity() 
	{
		
		//capacity is proportional to size
		this.capacity = random.nextInt(41) +10;
		
	}
*/	
	
	
	

}
