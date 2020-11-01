package com.mycompany.a3;
import com.codename1.charts.models.Point;

public abstract class Fixed extends GameObject implements ISelectable{

	private boolean selected;

	//constructor
	public Fixed(int size, int r, int g, int b, Point location) {
		super(size, r, g, b, location);
	}

	//not allowed to change the location
		
	public void setSelected(boolean value) {
		selected = value;
	}
	
	public boolean getSelected() {
		return selected;
	}
	
	public boolean isSelected() {
		Boolean b = selected;
		return b;
	}

	public String toString() {
		return super.toString();
	}
}
