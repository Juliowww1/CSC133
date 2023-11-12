package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SpiderCollideCommand extends Command 
{
	//attributes
	private GameWorld gw;
	
	//constructor
	public SpiderCollideCommand(GameWorld gw)
	{
		super("Collision with Spider");
		this.gw= gw;
	}
	
	//override method
	@Override 
	public void actionPerformed(ActionEvent ev)
	{
		gw.spiderCollision();
	}
	
}
