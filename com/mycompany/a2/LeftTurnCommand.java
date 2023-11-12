package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LeftTurnCommand extends Command 
{
	//attribute
	private GameWorld gw;
	
	//constructor 
	public LeftTurnCommand(GameWorld gw)
	{
		super("Taking a left! ");
		this.gw = gw;
	}
	
	//Overriding method
	@Override
	public void actionPerformed(ActionEvent ev)
	{
		gw.left();
	}
}
