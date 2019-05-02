package com.jaen.pedro.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jaen.pedro.utils.Constants;

public class VictoryOverlay {
    private Viewport viewport;
    private BitmapFont font;

    public VictoryOverlay(Viewport viewport) {
        this.viewport=viewport;

        font = new BitmapFont(Gdx.files.internal(Constants.FONT_FILE));
        font.getData().setScale(Constants.FONT_SCALE);
    }

    public void render(SpriteBatch batch){
        batch.setProjectionMatrix(viewport.getCamera().combined);
        font.draw(batch,
                Constants.VICTORY_MESSAGE,
                viewport.getWorldWidth() / 2,
                viewport.getWorldHeight() / 2.5f,
                0,
                Align.center,
                false);
    }
}
