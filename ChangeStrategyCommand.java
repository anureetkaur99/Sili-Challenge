package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class ChangeStrategyCommand extends Command{
	private GameWorld gw;
	public ChangeStrategyCommand(GameWorld gw) {
		super("Change Strategy");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		gw.changeStrategy();
	}
}
