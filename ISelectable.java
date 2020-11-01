package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

public interface ISelectable {
	void setSelected(boolean yesNo);
	boolean isSelected();
	boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
	void draw(Graphics g, Point pCmpRelPrnt);
}