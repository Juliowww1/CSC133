package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command 
{
	//constructor
	public ExitCommand()
	{
		super("Exit");
	}
	
	//method overriding parent method. asks if they want to quit
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String exitPrompt = "Are you sure you want to quit? ";
		
		boolean userExit = Dialog.show("Confirm Exit", exitPrompt, "Yes", "No");

		if (userExit)
		{
			System.exit(0);
		}
	}
	
}
