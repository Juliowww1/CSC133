package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class Fixed extends GameObject 
{
	
	//constructor
	
	Fixed(Point location)
	{
		super (location);
	}
	
	Fixed(int size, Point location)
	{
		super(size, location);
	}
	
	Fixed(int size, Point location, int color)
	{
		super(size, location, color);
	}
	

	//empty method so that fixed objects can't have their locations changed
	public void setLocation (Point location)
	{
		
	}
}
