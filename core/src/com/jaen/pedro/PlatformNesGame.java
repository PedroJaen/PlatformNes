package com.jaen.pedro;

import com.badlogic.gdx.Game;
import com.jaen.pedro.screens.DifficultScreen;
import com.jaen.pedro.screens.GameScreen;
import com.jaen.pedro.screens.MenuScreen;
import com.jaen.pedro.screens.ScoreScreen;
import com.jaen.pedro.screens.StartScreen;
import com.jaen.pedro.utils.Enums;

public class PlatformNesGame extends Game {

	@Override
	public void create() {
		setScreen(new StartScreen(this));
	}

	public void setMenuScreen(){
		setScreen(new MenuScreen(this));
	}

	public void setScoreScreen(){
		setScreen(new ScoreScreen(this));
	}

	public void setDifficultyScreen(){
		setScreen(new DifficultScreen(this));
	}

	public void setGameScreen(Enums.Difficulty difficulty){
		setScreen(new GameScreen(this,difficulty));
	}
}
