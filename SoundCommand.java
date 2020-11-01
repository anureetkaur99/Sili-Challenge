package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command {
	private GameWorld gw;
	private Toolbar toolbar;
	private BGSound bgSound;
	
	public SoundCommand(GameWorld gw, Toolbar toolbar, BGSound bgSound) {
		super("Sound");
		this.gw = gw;
		this.toolbar = toolbar;
		this.bgSound = bgSound;
	}
	
	public void actionPerformed(ActionEvent evt) {
		gw.toggleSound();
		if(gw.getSound())
			bgSound.pause();
		else 
			bgSound.play();
		toolbar.closeSideMenu();
	}
}
