package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.plaf.Border;
public class CustomButton extends Button{
	
	public CustomButton(String name, Command cmd) {
		super(name);
		this.getUnselectedStyle().setBgTransparency(255);
		this.getUnselectedStyle().setBgColor(ColorUtil.BLUE);
		this.getUnselectedStyle().setFgColor(ColorUtil.WHITE);
		this.getUnselectedStyle().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		this.getAllStyles().setPadding(Component.TOP, 3);
		this.getAllStyles().setPadding(Component.BOTTOM, 3);
		this.getAllStyles().setPadding(Component.RIGHT, 4);
		this.setCommand(cmd);
	}
}
