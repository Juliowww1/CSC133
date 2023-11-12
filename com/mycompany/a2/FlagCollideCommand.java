package com.mycompany.a2;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

public class FlagCollideCommand extends Command
{
	//attributes
	private GameWorld gw;
	
	//constructor
	public FlagCollideCommand(GameWorld gw)
	{
		super("Flag Collision!");
		this.gw= gw;
		
	}
	
	//Override method
	@Override
	public void actionPerformed(ActionEvent event)
	{
		Command cOK = new Command("OK");
		Command cCancel = new Command("Cancel");
		Command [] cmds = new Command[] {cOK, cCancel};
		TextField myTF = new TextField();
		
		//get last flag reached
		int lastFlagNum = gw.getAntPlayer().getLastFlagReached(); //###
		
		String userInput;
		int userInputFlag= 0;
		
		Command input = Dialog.show("Enter a Flag Number", myTF, cmds);
		
		if (input == cOK)
		{
			//get input from user
			userInput = myTF.getText();
			
			try
			{
				//cast user input to int
				userInputFlag = Integer.parseInt(userInput);
				
				//need to check if the flag is not greater than last flag
				//if (userInputFlag >= gw.getLastFlag())
				//{
				//	boolean range = Dialog.show("Warning", "Inputted number is greater than last flag reached"  , "OK", null);
				//}
				
				
				if(gw.getCurrentFlag() >= userInputFlag)
				{
					boolean alreadyCollected = Dialog.show("Warning", "You have already collected that flag"  , "OK", null);
					
				}
				
				//error if player tries to skip a sequentialFlagmake sure its not +1 
				else if(gw.getCurrentFlag() < userInputFlag && gw.getCurrentFlag() +1 != userInputFlag)
				{
					String skippedFlag = "You need to reach flag " + (gw.getCurrentFlag()+1) + " before you can reach flag " + userInputFlag ;
					boolean alreadyCollected = Dialog.show("Warning", skippedFlag , "OK", null);
				}
				
				
			}
			catch (Exception e)
			{
				boolean warning = Dialog.show("Warning", "Invalid input. Enter a number 1-5." , "OK", null);
			}
			
			//collide with flag
			gw.flagCollide(userInputFlag);
			
			//if player grabs last flag they win.
			if(gw.getCurrentFlag() == gw.getLastFlag())
			{
				boolean finalMsg = Dialog.show("Congratulations!", "You have won the game. Time: " + gw.getClock() , "OK", null);
				System.exit(0);
			}
		}
	}
}
