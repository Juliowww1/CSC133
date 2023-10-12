package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

public class Game extends Form 
{
	//create instance of Gameworld
	private GameWorld gw;
	
	public Game()
	{
		//instantiates gameworld
		gw = new GameWorld();
		
		//set initial state of game
		gw.init();
		
		//play game
		play();
	}
	
	private void play()
	{
		Label myLabel=new Label("Enter a Command:");
		this.addComponent(myLabel);
		final TextField myTextField=new TextField();
		this.addComponent(myTextField);
		this.show();
		
		myTextField.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt) 
			{
				String sCommand=myTextField.getText().toString();
				myTextField.clear();
				
				if(sCommand.length() != 0)
				{
					System.out.println("\n");
					switch (sCommand.charAt(0)) 
					{
					case 'a':
						gw.accelerate();
						break;
					case 'b':
						gw.frenar(); //to literally break
						break;
					case 'l':
						gw.left();
						break;
					case 'r':
						gw.right();
						break;
					case 'c':
						gw.consumption();
						break;
					case 'f':
						gw.foodStation();
						break;
					case 'g':
						gw.spiderCollision();
						break;
					case 't':
						gw.tick();
						break;
					case 'd':
						gw.display();
						break;
					case 'm':
						gw.map();
						break;
					case 'x':
						gw.exitPrompt();
						break;
					case 'y':
						System.out.println("Exiting game. ");
						System.exit(0);
						break;
					case 'n':
						gw.notQuitting();
						break;
					case '1':
						gw.flagCollide(1);
						break;
					case '2':
						gw.flagCollide(2);
						break;
					case '3':
						gw.flagCollide(3);
						break;
					case '4':
						gw.flagCollide(4);
						break;
					case '5':
						gw.flagCollide(5);
						break;
					case '6':
						gw.flagCollide(6);
						break;
					case '7':
						gw.flagCollide(7);
						break;
					case '8':
						gw.flagCollide(8);
						break;
					case '9':
						gw.flagCollide(9);
						break;
					default:
						System.out.println("Invalid expression. ");
					}
					/*
					try
					{
						String userNumInput = sCommand.charAt(0) + "";
						int num = Integer.parseInt(userNumInput);
						gw.flagCollide(num);
					}
					catch(Exception e)
					{
						
					}
					*/

					
						
				}
	
		} //actionPerformed
		} //new ActionListener()
		); //addActionListener
		} //play
}

