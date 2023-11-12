package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FoodStationCollisionCommand extends Command 
{
	//private attributes 
	private GameWorld gw;
	
	//constructor 
	public FoodStationCollisionCommand(GameWorld gw)
	{
		super("Food Station Collision");
		this.gw = gw;
	}
	
	//override method 
	@Override
	public void actionPerformed(ActionEvent ev)
	{
		System.out.println("\nAnt has collided with food station");
		gw.foodStation();
	}
	
	
}
