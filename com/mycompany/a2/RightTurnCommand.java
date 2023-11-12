package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class RightTurnCommand extends Command 
{
	//private attributes
	private GameWorld gw;
	
	//constructor
	public RightTurnCommand(GameWorld gw)
	{
		super("Taking a right!");
		this.gw = gw;
	}
	
	//Overide method
	@Override
	public void actionPerformed(ActionEvent ev)
	{
		gw.right();
	}
	
}
