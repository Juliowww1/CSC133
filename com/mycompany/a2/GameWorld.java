package com.mycompany.a2;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.List;

//gameworld is observable here, scoreview and mapview are observers
public class GameWorld extends Observable
{
	
	//Player will be Ant
	private Ant antPlayer; 
	
	//GameObjects
	private FoodStation foodStation;
	private FoodStation foodStation2;
	private FoodStation foodStation3;
	private Flag flag1;
	private Flag flag2;
	private Flag flag3;
	private Flag flag4;
	private Flag flag5;

	
	private Spider spider;
	private GameObjectCollection gameObjectCollection; 
	private ArrayList<GameObject> foodStationList;
	
	
	//game setting attributes
	private int clock =0;
	private int lives =3;
	private int lastFlag = 5;
	private boolean spawnFoodStation = true; //checks to see if the current food station is the spawned one.
	private int currentFlag = 1;
	
	//sound is set to false bc checkbox is not checked
	private boolean sound = false;
	

	
	//constructor
	public GameWorld()
	{
		//create collection of gameObjects. Utilizes iterator pattern
		gameObjectCollection = new GameObjectCollection();
	}
	
	public void init()
	{
		
		foodStationList = new ArrayList<GameObject>();
		
		
		//create location for ant and flag
		Point spawnLocation = new Point(300,300);
		
		//ant and spider objects
		//uses the singleton pattern so only one ant gets created
		antPlayer = Ant.getAnt(spawnLocation);
		spider = new Spider();
		
		
		//instantiate fixed objects
		foodStation = new FoodStation();
		foodStation2 = new FoodStation();
		foodStation3 = new FoodStation();
		flag1= new Flag(spawnLocation, 1);
		flag2 = new Flag(2);
		
		//add objects to collection
		gameObjectCollection.add(antPlayer);
		gameObjectCollection.add(spider);
		gameObjectCollection.add(flag1);
		gameObjectCollection.add(flag2);
		gameObjectCollection.add(flag3);
		gameObjectCollection.add(flag4);
		gameObjectCollection.add(flag5);
		
		//add food stations to our list
		foodStationList.add(foodStation);
		foodStationList.add(foodStation2);
		foodStationList.add(foodStation3);
		
		gameObjectCollection.add(foodStation);
		gameObjectCollection.add(foodStation2);
		gameObjectCollection.add(foodStation3);
		

		
		
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
		
		this.setChanged();
		this.notifyObservers();
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
		this.setChanged();
		this.notifyObservers(this);
		
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
		this.setChanged();
		this.notifyObservers(this);
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
		this.setChanged();
		this.notifyObservers(this);
	}

	public void consumption() 
	{
		
		antPlayer.setFoodConsumptionRate();
		System.out.println("Food consumption set successfully to " + antPlayer.getFoodConsumptionRate() +"\n");
		this.setChanged();
		this.notifyObservers(this);
		
	}

	//collides with food station. needs to be random
	public void foodStation() 
	{
		
		if (foodStationList.size() == 0)
		{
			System.out.println("There's no more food station left!");
			
		}
		else
		{
			System.out.println("Food station collision!");
			Random rand = new Random();
			
			//randomly selects foodStation
			FoodStation randFoodStation = (FoodStation) (foodStationList.get(rand.nextInt(foodStationList.size())));
			
			//add food level with food station capacity
			int newFoodLevel = antPlayer.getFoodLevel() +  randFoodStation.getCapacity();
			antPlayer.setFoodLevel(newFoodLevel);
			
			//set foodStation capacity to 0.
			randFoodStation.setCapacity(0);
			randFoodStation.setColor(ColorUtil.rgb(144,238,144));//set color to light green
			
			//remove food station
			foodStationList.remove(foodStation);
			
			this.setChanged();
			this.notifyObservers(this);
		}
		
		
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
				antPlayer.setHealthLevel(10);
				init();
			}
		}
		
		this.setChanged();
		this.notifyObservers(this);
		
	}

	/*ticks clock by one game interval. Utilizes iterator pattern
	*to iterate through each type object and updating headings/location
	*/
	public void tick() 
	{
		//elapse game clock
		clock++;
		
		//Instantiate iterator for all objects
		IIterator spiderIterator = gameObjectCollection.getIterator();
		IIterator moveableIterator = gameObjectCollection.getIterator();
		
		
		//spider updates heading
		while(spiderIterator.hasNext())
		{
			//get next object
			GameObject o = spiderIterator.getNext();
			
			//check if object is spider
			if (o instanceof Spider)
			{
				((Spider) o).changeHeadingRandomly();
			}
			
		}

		//all movable objects update positions according to heading and speed
		while(moveableIterator.hasNext())
		{
			//get next object
			GameObject o = moveableIterator.getNext();
			
			//check if object is spider
			if (o instanceof Moveable)
			{
				((Moveable) o).move();
			}
			
		}
		
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
		this.setChanged();
		this.notifyObservers(this);
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
		//use iterator pattern to map through objects
		IIterator objectIterator = gameObjectCollection.getIterator();
		while (objectIterator.hasNext())
		{
			System.out.println(objectIterator.getNext().toString());
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
			this.currentFlag = currentFlag;
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
		
		this.setChanged();
		this.notifyObservers();
	}
	
	//new methods in A2.
	public GameObjectCollection getGameObjCollection()
	{
		return this.gameObjectCollection;
	}
	
	
	public int getClock()
	{
		return this.clock;
	}
	
	public boolean getSound()
	{
		return this.sound;
	}
	
	//method to set sound, also notifies observers (SV)
	public void setSound(boolean sound)
	{
		this.sound = sound;
		
		this.setChanged();
		this.notifyObservers();
		
		if(sound == true)
		{
			System.out.println("Sound is ON");
		}
		else
			System.out.println("Sound is OFF");
	}
	
	public Ant getAntPlayer()
	{
		return this.antPlayer;
	}
	

	public int getLastFlag()
	{
		return this.lastFlag;
	}
	
	/*
	 * method to return number of lives players has. Necessary to 
	 * Update the score view observable
	 */
	
	public int getLives()
	{
		return lives;
	}
	
	public int getCurrentFlag()
	{
		return this.currentFlag;
	}
	

	

}
