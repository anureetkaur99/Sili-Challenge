package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command{
	public HelpCommand() {
		super("Help");
	}
	
	@Override
	public void actionPerformed(ActionEvent ev) {
		String info = "\'a\': " + "Accelerate Cyborg's speed\n" 
				+ "\'b\': " + "Decelerate Cyborg's speed\n" 
				+ "\'l\': " + "Steer Cyborg's heading to the left (-5 degrees)\n" 
				+ "\'r\': " + "Steer Cyborg's heading to the right (5 degrees)\n" 
				+ "\'e\': " + "Simulate collision with Energy station\n" 
				+ "\'g\': " + "Simulate collision with Drone\n" 
				+ "\'t\': " + "Notify Game World of Clock tick\n";

		Dialog.show("About", info, new Command("ok"));
	}
}