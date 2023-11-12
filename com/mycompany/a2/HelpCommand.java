package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command 
{
	public HelpCommand()
	{
		super("Help");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev)
	{
		String helpInfo = "\n'a' = Accelerate\n'b' =Brake\n'l' Turn Left\n'R' = Turn Right\n 'f' = Food Station Collision\n'g' =Spider Collision\n't' = Tick the Clock\n'x' = Exit Game";
		
		//help, help info. ok button
		boolean c = Dialog.show("Help", helpInfo, "OK", null);
	}
}
