package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;

/*
 * ScoreView is an observer. It presents a graphical display of game/
 * ant values 
 */
public class ScoreView extends Container implements Observer 
{
	/*
	 * constructor: Observable is passed and adds this object as an observer
	 * Labels are constructed also.
	 */
	
	//private attributes
	private Label healthLevel;
	private Label lives;
	private Label sound;
	private Label time;
	private Label lastFlagReached;
	private Label foodLevel;
	
	/*
	 * constructor: Observable is passed and adds this object as an observer
	 * Labels are constructed and are passed as observables
	 */
	public ScoreView (Observable o)
	{
		o.addObserver(this);
		
		/*
		 * Split each attribute into Label(text) and value.  
		 */
		
		//health Label 
		Label healthLabel = new Label("Health Level: ");
		healthLabel.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		//health value
		healthLevel = new LabelStyler();
		
		//Lives label
		Label livesLabel = new Label("Lives: ");
		livesLabel.getAllStyles().setFgColor(ColorUtil.BLUE); 
		
		//lives value
		lives =new LabelStyler();
		
		//Sound label
		Label soundLabel = new Label("Sound: ");
		soundLabel.getAllStyles().setFgColor(ColorUtil.BLUE); 
		
		//Sound value
		sound =new LabelStyler();
		
		//time label
		Label timeLabel = new Label("Time: ");
		timeLabel.getAllStyles().setFgColor(ColorUtil.BLUE); 
		
		//time value
		time =new LabelStyler();
		
		//LastFlag label
		Label lastFlagReachedLabel = new Label("Last flag reached: ");
		lastFlagReachedLabel.getAllStyles().setFgColor(ColorUtil.BLUE); 
		
		//lastFlag value
		lastFlagReached =new LabelStyler();
		
		//foodLevel label
		Label foodLevelLabel = new Label("Food Level: ");
		foodLevelLabel.getAllStyles().setFgColor(ColorUtil.BLUE); 
		
		//foodLevel value
		foodLevel =new LabelStyler();
		
		//add observables
		this.add(timeLabel);
		this.add(time);
		
		this.add(healthLabel);
		this.add(healthLevel);

		this.add(livesLabel);
		this.add(lives);
		
		this.add(soundLabel);
		this.add(sound);
		
		this.add(lastFlagReachedLabel);
		this.add(lastFlagReached);
		
		this.add(foodLevelLabel);
		this.add(foodLevel);

				
	}
	
	//method to update the header scoreview. game/ant data VALUES are updated
	public void update(Observable o, Object arg)
	{
		System.out.println("\nDisplaying game values: ");
		time.setText("" + ((GameWorld) o).getClock());
		healthLevel.setText(" " + ((GameWorld) o).getAntPlayer().getHealthLevel());
		lives.setText(" " + ((GameWorld) o).getLives());
		foodLevel.setText(" " + ((GameWorld) o).getAntPlayer().getFoodLevel());
		lastFlagReached.setText(""+ ((GameWorld) o).getAntPlayer().getLastFlagReached() );
		
		
		//update the sound corresponding if sound is on or off
		String soundValue;
		
		if (((GameWorld)o ).getSound() == true){
			soundValue = "ON";
		}
		else
			soundValue = "OFF";
		sound.setText("" + soundValue);
		
		//replace values and display
		this.revalidate();
		
		((GameWorld)o ).display();
	}
}
