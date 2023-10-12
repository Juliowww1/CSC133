package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class Flag extends Fixed 
{
	private int sequenceNumber;
	
	//constructors
	//flag 1 will be generated with location passed, same location as ant
	Flag(Point location, int sequenceNumber)
	{
		//size, location, color
		super(10 ,location, ColorUtil.BLUE);
		this.sequenceNumber = sequenceNumber;
		
	}
	
	//for flags to be generated in random location
	Flag(int sequenceNumber)
	{
		super(10, getRandomLocation(),ColorUtil.BLUE);
		this.sequenceNumber = sequenceNumber;
	}

	public int getSequenceNumber() 
	{
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) 
	{
		this.sequenceNumber = sequenceNumber;
	}
	
	public String toString()
	{
		return "Flag: location=" + "(" + this.getLocation().getX()+ ","  + this.getLocation().getY() + ")"+
				", " + this.printColor()+
				", Size= " + this.getSize() +
				", SequenceNum" + this.getSequenceNumber();
	}
	
	

}
