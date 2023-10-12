package com.mycompany.a1;

import java.util.ArrayList;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.List;

public class GameWorld 
{
	
	//Player will be Ant
	private Ant antPlayer; 
	
	//GameObjects
	private FoodStation foodStation;
	private FoodStation foodStation2;
	private FoodStation foodStation3;
	private Flag flag1;
	private Flag flag2;
	private Spider spider;
	private ArrayList<GameObject> gameObjectCollection; 
	private ArrayList<GameObject> emptyFoodStations;
	
	//game attributes
	private int clock =0;
	private int lives =3;
	private int lastFlag = 5;

	
	//constructor
	public GameWorld()
	{

	}
	
	public void init()
	{
		//create collection of gameObjects
		gameObjectCollection = new ArrayList<GameObject>();
		emptyFoodStations = new ArrayList<GameObject>();
		
		
		//create location for ant and flag
		Point spawnLocation = new Point(300,300);
		
		//ant and spider objects
		antPlayer = new Ant(spawnLocation);
		spider = new Spider();
		
		
		//instantiate fixed objects
		foodStation = new FoodStation();
		foodStation2 = new FoodStation();
		foodStation3 = new FoodStation();
		flag1= new Flag(spawnLocation, 1);
		flag2 = new Flag(2);
		
		//add objects to collection
		gameObjectCollection.add(antPlayer);
		gameObjectCollection.add(foodStation);
		gameObjectCollection.add(spider);
		gameObjectCollection.add(flag1);
		gameObjectCollection.add(flag2);
		
	}
	
	//method to accelerate ant
	public void accelerate()		
	{
		int currentSpeed = antPlayer.getSpeed();	
			
		//accelerate if current speed is less than max, and health is more than 50%
		if ((currentSpeed < antPlayer.getMaximumSpeed()) && (antPlayer.getHealthLevel() >5))
		{
			System.out.println("Accelerating!");
			currentSpeed+=2;
			antPlayer.setSpeed(currentSpeed);
		}
		
		else if (currentSpeed >= antPlayer.getMaximumSpeed())
		{
			System.out.println("Cannot accelerate. You have hit your max speed");
		}
		
		//if health is less than 50%, can only accelerate up to a limited range
		if (antPlayer.getHealthLevel()<=5)			
		{
			//if ant has lower health than 6, it can only accelerate to 20
			int lowHealthSpeed = 20;
			antPlayer.setMaximumSpeed(lowHealthSpeed);
				
			if(currentSpeed >= lowHealthSpeed)
			{				
				System.out.println("Cannot accelerate any faster since you are at low health");
				currentSpeed = lowHealthSpeed;
				antPlayer.setSpeed(currentSpeed);
			}
			else
			{
				System.out.println("Accelerating. Note your maximum acceleration is less because you are low health.");
				currentSpeed+=2;	
				antPlayer.setSpeed(currentSpeed);
			}
		}
	}
			
			
	//lowers speed
	public void frenar() 
	{
		if(antPlayer.getSpeed() <= 0)
		{
			System.out.println("Speed is at zero.");
		}
		else
		{
			System.out.println("Braking!");
			antPlayer.setSpeed(antPlayer.getSpeed() -1);
		}
		
	}

	//changes heading of ant by 5 degrees
	public void left() 
	{
		int currentHeading = antPlayer.getHeading();
		System.out.println("Taking a left!\n");
		
		//if heading will be negative, set it to zero
		if(currentHeading -5 < 0)
		{
			antPlayer.setHeading(355);
		}
		else
		{
			antPlayer.setHeading(currentHeading -5);
		}
		
	}

	public void right() 
	{
		int currentHeading = antPlayer.getHeading();
		
		System.out.println("Taking a right! \n");
		
		//have heading b/w 0 and 360
		if(currentHeading + 5 >=360)
		{
			antPlayer.setHeading(0);
		}
		else
		{
			antPlayer.setHeading(currentHeading +5);
		}
		
	}

	public void consumption() 
	{
		
		antPlayer.setFoodConsumptionRate();
		System.out.println("Food consumption set successfully to " + antPlayer.getFoodConsumptionRate() +"\n");
		
	}

	public void foodStation() 
	{
		System.out.println("Food station collision!");
		
		//add food level with food station capacity
		int newFoodLevel = antPlayer.getFoodLevel() + foodStation.getCapacity();
		antPlayer.setFoodLevel(newFoodLevel);
		
		//set foodStation capacity to 0.
		foodStation.setCapacity(0);
		foodStation.setColor(ColorUtil.rgb(144,238,144));//set color to light green
		
		
		
		
		
	}

	public void spiderCollision() 
	{
		System.out.println("Collision with spider!");
		
		//lowers health by 5
		antPlayer.setHealthLevel(antPlayer.getHealthLevel() -5);
		
		//changes color to light red
		antPlayer.setColor(ColorUtil.rgb(223,70,97));
		
		//slightly reduce speed
		antPlayer.setSpeed((int) Math.round(antPlayer.getSpeed()*.60));
		
		//if health is lower than 0, decrease lives
		if (antPlayer.getHealthLevel()<= 0)
		{
			lives--;
			
			if(lives==0)
			{
				System.out.println("Game Over! Time taken: " + clock);
				System.exit(0);
				
			}
			else
			{
				System.out.println("You died! Lives remaining: " + lives);
				init();
			}
		}
		
		
	}

	public void tick() 
	{
		//elapse game clock
		clock++;
		
		//spiders update heading
		for (int i =0; i < gameObjectCollection.size(); i++)
		{
			if(gameObjectCollection.get(i) instanceof Spider)
			{
				((Spider) gameObjectCollection.get(i)).changeHeadingRandomly();
			}
		}
		
		//all movable objects update positions according to heading and speed
		for (int i =0; i < gameObjectCollection.size(); i++)
		{
			if(gameObjectCollection.get(i) instanceof Moveable)
			{
				((Moveable) gameObjectCollection.get(i)).move();
			}
		}
		//hi
		//lower ants food level. minus food level by consumption rate
		antPlayer.setFoodLevel(antPlayer.getFoodLevel() - antPlayer.getFoodConsumptionRate());
		
		//if food level is 0, restart game
		if(antPlayer.getFoodLevel() <=0)
		{
			System.out.println("You starved to death!\n");
			lives--;
			
			if(lives==0)
			{
				System.out.println("Game Over! Time taken: " + clock);
				System.exit(0);
				
			}
			else
			{
				System.out.println("Lives remaining: " + lives);
				init();
			}
		}
		

		
		System.out.println("Time has ticked! Time:" + clock);
	}

	//displays game values
	public void display() 
	{
		System.out.println("Number of lives remaining: " + lives);
		System.out.println("Clock Time: " + clock);
		System.out.println("Highest flag reached: " + antPlayer.getLastFlagReached());
		System.out.println("Ant food level: " +antPlayer.getFoodLevel());
		System.out.println("Ant's health level: " + antPlayer.getHealthLevel());
		
	}
	
	public void map()
	{
		/*
		for (int i =0; i < gameObjectCollection.size(); i++)
		{
			if(gameObjectCollection.get(i) instanceof Ant)
			{
				((Ant) gameObjectCollection.get(i)).toString();
			}
			
		}
		*/
		for (int i =0; i < gameObjectCollection.size(); i++)
		{
			System.out.println(gameObjectCollection.get(i).toString());
		}
	}

	public void exitPrompt() 
	{
		System.out.println("Are you sure you want to quit? y/n" );
		
	}


	public void notQuitting() 
	{
		System.out.println("Exit canceled.");
		
	}

	public void flagCollide(int currentFlag) 
	{
		// TODO Auto-generated method stub
		
		//if player has already collected inputted Flag
		if(antPlayer.getLastFlagReached() >= currentFlag)
		{
			System.out.println("You have already collected flag " + currentFlag);
			
		}
		//successful outcome if player gets next sequential flag
		else if (antPlayer.getLastFlagReached() +1 == currentFlag)
		{
			antPlayer.setLastFlagReached(currentFlag);
			System.out.println("Flag " + antPlayer.getLastFlagReached() + " secured!");
		}
		
		//error if player tries to skip a sequentialFlag.
		else if(antPlayer.getLastFlagReached() < currentFlag)
		{
			System.out.println("You need to reach flag " + (antPlayer.getLastFlagReached()+1) + " before you can reach flag " + currentFlag );
		}
		
		//if player grabs last flag they win.
		if(antPlayer.getLastFlagReached() == lastFlag)
		{
			System.out.println("Congrats! You have won the game. Your total time is: " +clock );
			System.exit(0);
		}
		
	}
	

}
