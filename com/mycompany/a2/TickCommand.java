package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class TickCommand extends Command 
{
	//private attributes
	private GameWorld gw;
	
	//constructor
	public TickCommand(GameWorld gw)
	{
		super("Tick");
		this.gw = gw;
	}
	
	//Override method, notify observers that change has been made
	@Override
	public void actionPerformed(ActionEvent e)
	{
		gw.tick();
		gw.notifyObservers();
	}
}
