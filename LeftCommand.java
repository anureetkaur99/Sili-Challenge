package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class LeftCommand extends Command{
	private GameWorld gw;
	public LeftCommand(GameWorld gw) {
		super("Left");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.left();
	}
}