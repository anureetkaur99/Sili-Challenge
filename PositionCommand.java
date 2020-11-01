package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PositionCommand extends Command {
	private GameWorld gw;
	
	public PositionCommand(GameWorld gw, MapView mv) {
		super("Position");
		this.gw = gw;
	}

	@Override 
	public void actionPerformed(ActionEvent ev) {
		gw.setPosition();
	}
}
