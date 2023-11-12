package com.mycompany.a2;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command 
{
	//private attributes 
	private GameWorld gw;
	
	
	public SoundCommand(GameWorld gw)
	{
		super("Sound");
		this.gw = gw;
	}
	
	
	//
	@Override 
	public void actionPerformed(ActionEvent event)
	{
		if (((CheckBox) event.getComponent()).isSelected())
		{
			gw.setSound(true);
		}
		else
		{
			gw.setSound(false);
		}
	}
}
