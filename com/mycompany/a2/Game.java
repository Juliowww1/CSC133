package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

//game acts as the controller in a model-view architecture
public class Game extends Form 
{
	//create instance of Gameworld
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	public Game()
	{
		//instantiates gameworld, observer of map, and observer of game
		gw = new GameWorld();
		mv = new MapView(gw);
		sv = new ScoreView(gw);
		
		//add observers
		gw.addObserver(mv);
		gw.addObserver(sv);
		
		//set layout
		this.setLayout(new BorderLayout());
		
		//set title
		this.setTitle("Bug Game");
		
		//creating toolbar & title of game
		Toolbar myToolbar = new Toolbar();
		setToolbar(myToolbar);
		Toolbar.setOnTopSideMenu(false);
		myToolbar.setTitle("Bug Game");
		
		//and an empty item 
		//Command sideMenuItem1 = new Command("Side menu item 1");
		//myToolbar.addCommandToSideMenu(sideMenuItem1);
		
		//add empty item to overflow menu
		//Command overFlowMenuItem1 = new Command("Overflow Menu Item 1");
		//myToolbar.addCommandToSideMenu(overFlowMenuItem1);
		
		//add empty item to left side of title bar area
		Command titleBarAreaItem2 = new Command("Options");
		myToolbar.addCommandToLeftBar(titleBarAreaItem2);
		
		/*container organization. North and center regions will use Scoreview and mapview respectively.
		 * west, east, and south will use a different container
		 */
		
		//center container. this will be mapview. set color and border
		mv.getAllStyles().setBgTransparency(255);
		mv.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		mv.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		
		//North container(Scoreview). set padding 300 pixels below of TOP container
		sv.getAllStyles().setPadding(Component.LEFT, 365);
		sv.getAllStyles().setBorder(Border.createLineBorder(4,ColorUtil.GREEN));
		
		
		//east container
		Container east = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		east.getAllStyles().setPadding(Component.TOP, 200);
		east.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.MAGENTA));
		
		//south container
		Container south = new Container(new BoxLayout(BoxLayout.X_AXIS));
		south.getAllStyles().setPadding(Component.LEFT, 450);
		south.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.MAGENTA));
		
		//west container
		Container west = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		west.getAllStyles().setPadding(Component.TOP, 200);
		west.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.MAGENTA));
		
		//command objects to containers and key bindings
		Command accelerate = new AccelerateCommand(gw);
		addKeyListener('a', accelerate);
		
		
		Command frenar = new FrenarCommand(gw);
		addKeyListener('b', frenar);
		
		Command left = new LeftTurnCommand(gw);
		addKeyListener('l' , left);
		
		Command right = new RightTurnCommand(gw);
		addKeyListener('r' , right);
		
		//no key listener for flag collide
		Command flagCollide =new FlagCollideCommand(gw);
		
		Command foodStationCollision = new FoodStationCollisionCommand(gw);
		addKeyListener('l' , foodStationCollision);
		
		Command spiderCollide = new SpiderCollideCommand(gw);
		addKeyListener('g' , spiderCollide);
		
		Command tick = new TickCommand(gw);
		addKeyListener('t' , tick);
		
		Command exit = new ExitCommand();
		addKeyListener('x', exit);
		
		Command sound = new SoundCommand(gw);
		CheckBox soundBox = new CheckBox("Sound");
		
		soundBox.setCommand(sound);
		soundBox.getAllStyles().setBgTransparency(255);
		soundBox.getAllStyles().setBgColor(ColorUtil.BLUE);
		
		Command aboutGame = new AboutGameCommand();
		
		Command helpMe = new HelpCommand();
			
		
		
		//add component to side menu
		myToolbar.addComponentToSideMenu(soundBox);
		
		//add command to side menu
		myToolbar.addCommandToSideMenu(accelerate);
		myToolbar.addCommandToSideMenu(exit);
		myToolbar.addCommandToSideMenu(helpMe);
		myToolbar.addCommandToSideMenu(aboutGame);
		
		//add command to right bar
		myToolbar.addCommandToRightBar(helpMe);

		//create buttons for all comand objects
		
		//west container
		CommandButton accelerateButton = new CommandButton();
		accelerateButton.setCommand(accelerate);
		west.add(accelerateButton);
		
		CommandButton leftButton = new CommandButton();
		leftButton.setCommand(left);
		west.add(leftButton);
		
		//East container
		CommandButton breakButton = new CommandButton();
		breakButton.setCommand(frenar);
		east.add(breakButton);
		
		CommandButton rightButton = new CommandButton();
		rightButton.setCommand(right);
		east.add(rightButton);
		
		//south Container
		CommandButton flagCollideButton = new CommandButton();
		flagCollideButton.setCommand(flagCollide);
		south.add(flagCollideButton);
		
		CommandButton spiderCollisionButton = new CommandButton();
		spiderCollisionButton.setCommand(spiderCollide);
		south.add(spiderCollisionButton);
		
		CommandButton foodCollideButton = new CommandButton();
		foodCollideButton.setCommand(foodStationCollision);
		south.add(foodCollideButton);
		
		CommandButton tickButton = new CommandButton();
		tickButton.setCommand(tick);
		south.add(tickButton);
		
		//add container
		add(BorderLayout.CENTER, mv)
		.add(BorderLayout.NORTH, sv)
		.add(BorderLayout.EAST, east)
		.add(BorderLayout.SOUTH, south)
		.add(BorderLayout.WEST, west);
		
		//make help command
		this.show();

		//set initial state of game
		gw.init();
		
		//update the observers
		sv.update(gw, gw.getAntPlayer());
		mv.update(gw, gw.getGameObjCollection());
		

	}
	
	
	
	
	
	
	/*
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
					

					
						
				}
		
		} //actionPerformed
		} //new ActionListener()
		); //addActionListener
		} //play
		*/
	
}


