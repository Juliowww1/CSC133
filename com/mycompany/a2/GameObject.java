package com.mycompany.a2;

import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public abstract class GameObject 
{
	private int size;
	private Point location;
	private int color;
	
	public GameObject() {}
	
	public GameObject(Point location)
	{
		this.location = location;
	}
	
	public GameObject(int size, int color)
	{
		this.size = size;
		this.color = color;
	}
	
	public GameObject(int size, Point location)
	{
		this.size = size;
		this.location = location;
	}
	
	public GameObject(int size, Point location, int color)
	{
		this.size = size;
		this.location = location;
		this.color = color;
	}
	
	
	
	public int getSize() 
	{
		return size;
	}
	
	public Point getLocation() 
	{
		return location;
	}
	public float getXLocation()
	{
		return location.getX();
	}
	
	public float getYLocation()
	{
		return location.getY();
	}
	
	public void setLocation(Point location) 
	{
		this.location = location;
	}
	
	//setting random location
	public static Point getRandomLocation()
	{
		Random random = new Random();
		
		
		//get random point 
		 Point randLocation = new Point(random.nextInt(950),random.nextInt(950));
		 return randLocation;
		
	}
	
	public void setLocation(float x, float y)
	{
		location = new Point(x,y);
	}
	public Point getPointLocation()
	{
		return location;
	}
	public int getColor() 
	{
		return color;
	}
	public void setColor(int r, int g, int b) 
	{
		ColorUtil.rgb(r, g, b);
	}
	
	public void setColor(int color)
	{
		this.color = color;
	}
	
	public String printColor() 
	{
		return "Color=[" + ColorUtil.red(this.getColor()) + "," + ColorUtil.green(this.getColor()) + "," + ColorUtil.blue(this.getColor()) + "]";
	}
	
	public static int getRandomSize()
	{
		Random random = new Random();
		return random.nextInt(41) + 10;
		
	}
	
	public abstract String toString();

}
