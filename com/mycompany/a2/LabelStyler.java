package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Label;

/*
 * This class adds our own customized features to the labels for the Game.
 */
public class LabelStyler extends Label 
{
	public LabelStyler()
	{
		getAllStyles().setFgColor(ColorUtil.BLUE);
		getAllStyles().setPadding(LEFT, 2);
		getAllStyles().setPadding(RIGHT, 5);
	}
}
