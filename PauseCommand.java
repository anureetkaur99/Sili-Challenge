package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class PauseCommand extends Command {
	private Game game;

	public PauseCommand(Game game) {
		super("Pause");
		this.game = game;
	}
	
	@Override 
	public void actionPerformed(ActionEvent ev) {
		game.pause();
	}
}