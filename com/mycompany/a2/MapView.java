package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;

public class MapView extends Container implements Observer 
{
	
	//contructor
	public MapView ()
	{
		
	}
	
	//constructor: Observable is passed and adds this object as an observer
	public MapView (Observable o)
	{
		o.addObserver(this);
	}

	@Override
	public void update(Observable observable, Object data) 
	{
		System.out.println("Outputting map: \n");
		((GameWorld) observable).map();

	}

}
