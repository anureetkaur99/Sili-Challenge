package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.util.UITimer;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

public class Game extends Form implements Runnable {

	// instance variables
	private GameWorld gw;
	private MapView mv; // new in A2
	private ScoreView sv; // new in A2

	// timer variables
	private UITimer timer;
	private int updateTime;
//	private float elapsedTime;

	// sound variable
	private BGSound bgSound;

	// play/pause variables
	private boolean pauseGame = false;

	// custom buttons
	private Toolbar myToolbar;
	private CustomButton accelerateButton;
	private CustomButton brakeButton;
	private CustomButton leftButton;
	private CustomButton rightButton;
	private CustomButton changeStrategyButton;
	private CustomButton pauseButton;
	private CustomButton positionButton;

	// commands
	private AccelerateCommand accel;
	private BrakeCommand brak;
	private LeftCommand le;
	private RightCommand ri;
	private ExitCommand exi;
	private ChangeStrategyCommand change;
	private AboutCommand abou;
	private HelpCommand hel;
	private PauseCommand pau;
	private PositionCommand pos;
	private SoundCommand soun;

	// constructor
	public Game() {
		//pauseGame = false;
		// add timer
		timer = new UITimer(this);
		//elapsedTime = 0;
		updateTime = 20;
		// observable GameWorld
		gw = GameWorld.getGameWorld(); // create "Observable" GameWorld

		// Add observers and register them
		mv = new MapView(this);
		sv = new ScoreView();
		gw.addObserver(mv);
		gw.addObserver(sv);
		myForm();
		this.show();
		//gw.createSounds();
		gw.setX(mv.getWidth());
		gw.setY(mv.getHeight());
		gw.init();
		timer.schedule(updateTime, true, this);
	}

	@Override
	public void run() {
		//elapsedTime += updateTime;
		gw.clockTick(updateTime);
	}

//	public float getTime() {
//		return elapsedTime;
//	}

	public boolean isPaused() {
		return pauseGame;
	}
	public void resetSelection() {
		IIterator iterator = gw.getCollection().getIterator();
		while (iterator.hasNext()) {
			GameObject ob = iterator.getNext();
			if (ob instanceof Fixed) {
				Fixed fix = (Fixed) ob;
				fix.setSelected(false);
			}
		}
		gw.updateObservers();
	}

	private void myForm() {
		setLayout(new BorderLayout());
		// Top Tool bar (part of North area)
		myToolbar = new Toolbar();
		setToolbar(myToolbar);

		// create BGSound
		bgSound = new BGSound("borg_flyby.mp3");
		bgSound.play();

		// instantiate commands
		accel = new AccelerateCommand(gw);
		brak = new BrakeCommand(gw);
		le = new LeftCommand(gw);
		ri = new RightCommand(gw);
		exi = new ExitCommand();
		change = new ChangeStrategyCommand(gw);
		soun = new SoundCommand(gw, myToolbar, bgSound);
		abou = new AboutCommand();
		hel = new HelpCommand();
		pau = new PauseCommand(this);
		pos = new PositionCommand(gw, mv);

		// keybinding
		addKeyListener('a', accel);
		addKeyListener('b', brak);
		addKeyListener('l', le);
		addKeyListener('r', ri);

		// NORTH
		// Title
		Label title = new Label("Sili Challenge");
		title.getAllStyles().setFgColor(ColorUtil.BLACK);
		myToolbar.setTitleComponent(title);
		// Toolbar.setOnTopSideMenu(false);
		// Side Menu Accelerate Button
		CustomButton sideAccel = new CustomButton("Accelerate", new AccelerateCommand(gw));
		myToolbar.addComponentToSideMenu(sideAccel);
		// Side Menu Sound Button
		CheckBox sound = new CheckBox("Sound");
		sound.getAllStyles().setBgColor(ColorUtil.GRAY);
		sound.getAllStyles().setBgTransparency(255);
		sound.setCommand(soun);
		myToolbar.addComponentToSideMenu(sound);
		// Side Menu About Button
		CustomButton about = new CustomButton("About", abou);
		myToolbar.addComponentToSideMenu(about);
		// Side Menu Exit Button
		CustomButton exit = new CustomButton("Exit", exi);
		myToolbar.addComponentToSideMenu(exit);
		// Help Button on Right
		HelpCommand help = new HelpCommand();
		myToolbar.addCommandToRightBar(help);
		add(BorderLayout.NORTH, sv);
		this.show();

		// CENTER
		add(BorderLayout.CENTER, mv);

		// WEST
		// Container Designing
		Container leftContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		leftContainer.getAllStyles().setPadding(Component.TOP, 50);
		leftContainer.getAllStyles().setPadding(Component.BOTTOM, 50);
		leftContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		// Accelerate Button
		accelerateButton = new CustomButton("Accelerate", new AccelerateCommand(gw));
		leftContainer.add(accelerateButton);
		// Left Button
		leftButton = new CustomButton("Left", le);
		leftContainer.add(leftButton);
		// Change Strategies Button
		changeStrategyButton = new CustomButton("Change Strategies", change);
		leftContainer.add(changeStrategyButton);
		add(BorderLayout.WEST, leftContainer);

		// EAST container with Box Layout
		// Container Designing
		Container rightContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		rightContainer.getAllStyles().setPadding(Component.TOP, 50);
		rightContainer.getAllStyles().setPadding(Component.BOTTOM, 50);
		rightContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		// Brake Button
		brakeButton = new CustomButton("Brake", brak);
		rightContainer.add(brakeButton);
		// Right Button
		rightButton = new CustomButton("Right", ri);
		rightContainer.add(rightButton);
		add(BorderLayout.EAST, rightContainer);

		// SOUTH container with Flow Layout
		// Container Designing
		Container bottomContainer = new Container(new FlowLayout(Component.CENTER));
		bottomContainer.getAllStyles().setPadding(Component.LEFT, 10);
		bottomContainer.getAllStyles().setPadding(Component.RIGHT, 10);
		bottomContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLACK));
		// Position Button
		positionButton = new CustomButton("Position", pos);
		bottomContainer.add(positionButton);
		positionButton.setEnabled(false);
		// Pause Button
		pauseButton = new CustomButton("Pause", pau);
		bottomContainer.add(pauseButton);
		add(BorderLayout.SOUTH, bottomContainer);

		this.show();
	}
	public void pause() {
		this.pauseGame = !pauseGame;
		if (pauseGame) {
			bgSound.pause();
			pauseButton.setText("Play");
			timer.cancel();
			accelerateButton.setEnabled(false);
			brakeButton.setEnabled(false);
			leftButton.setEnabled(false);
			rightButton.setEnabled(false);
			changeStrategyButton.setEnabled(false);
			myToolbar.setEnabled(false);
			positionButton.setEnabled(true);
			removeKeyListener('a', new AccelerateCommand(gw));
			removeKeyListener('b', brak);
			removeKeyListener('l', le);
			removeKeyListener('r', ri);
			mv.setSelectable(true);
		} else {
			bgSound.play();
			pauseButton.setText("Pause");
			timer.schedule(updateTime, true, this);
			accelerateButton.setEnabled(true);
			brakeButton.setEnabled(true);
			leftButton.setEnabled(true);
			rightButton.setEnabled(true);
			myToolbar.setEnabled(true);
			changeStrategyButton.setEnabled(true);
			positionButton.setEnabled(false);
			addKeyListener('a', new AccelerateCommand(gw));
			addKeyListener('b', brak);
			addKeyListener('l', le);
			addKeyListener('r', ri);
			mv.setSelectable(false);
			this.resetSelection();
		}
	}
}
