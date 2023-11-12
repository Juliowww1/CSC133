package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AccelerateCommand extends Command 
{
	//game world object to manipulate it
	private GameWorld gw;
	
	//constructor
	public AccelerateCommand(GameWorld gw)
	{
		super("Accelerate");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev)
	{
		gw.accelerate();
	}
}
