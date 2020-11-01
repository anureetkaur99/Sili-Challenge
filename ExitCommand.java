package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command{
	public ExitCommand() {
		super("Exit");
	}
	
	public void actionPerformed(ActionEvent ev) {
		boolean exitConfirm = Dialog.show("Confirm exit", "Are you sure you want to exit?", "Yes", "No");
		if(exitConfirm)
			Display.getInstance().exitApplication();
	}
}