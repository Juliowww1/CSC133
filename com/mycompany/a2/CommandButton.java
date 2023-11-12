package com.mycompany.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Border;

/*
 * Styles command button on game form.
 */
public class CommandButton extends Button
{
	public CommandButton()
	{
		super();
		this.getAllStyles().setPadding(Component.BOTTOM, 5);
		this.getAllStyles().setPadding(Component.TOP, 5);
		
		this.getUnselectedStyle().setBgTransparency(255);
		this.getUnselectedStyle().setBgColor(ColorUtil.WHITE);
		this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		this.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		

	}
}
