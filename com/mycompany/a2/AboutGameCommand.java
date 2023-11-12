package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutGameCommand extends Command
{
	
	//constructor to display about section about the game
	public AboutGameCommand()
	{
		super("About");
	}
	
	@Override
	public void actionPerformed(ActionEvent event)
	{
		String gameInfo = "Julian Pulido.\n CSC133 Object Oriented Computer Graphics\nProfessor Pinar";
		
		boolean c = Dialog.show("About", gameInfo, "OK", null);
	}
	
}
