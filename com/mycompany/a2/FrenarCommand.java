package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class FrenarCommand extends Command 
{
	//attributes
	private GameWorld gw;
	
	public FrenarCommand(GameWorld gw)
	{
		super("Brake");
		this.gw = gw;
	}
	
	//override method
	@Override
	public void actionPerformed(ActionEvent ev)
	{
		gw.frenar();
	}
}
