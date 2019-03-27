package com.jaen.pedro;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.jaen.pedro.screens.DifficultScreen;
import com.jaen.pedro.screens.GameScreen;
import com.jaen.pedro.screens.MenuScreen;
import com.jaen.pedro.screens.ScoreScreen;
import com.jaen.pedro.screens.StartScreen;
import com.jaen.pedro.utils.Assets;
import com.jaen.pedro.utils.Enums;
import com.jaen.pedro.utils.Preferencias;

public class PlatformNesGame extends Game {
    private Preferencias preferencias;
    private boolean mute;
    private Music music;

    @Override
	public void create() {
        preferencias=new Preferencias();

        preferencias.cargaMute();
        mute=preferencias.isMute();

        AssetManager am = new AssetManager();
        Assets.instance.init(am);

        setStartScreen();
	}

	public void setStartScreen(){
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

    public Preferencias getPreferencias() {
        return preferencias;
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void dispose() {
        super.dispose();
        Assets.instance.dispose();
        music.dispose();

    }
}
