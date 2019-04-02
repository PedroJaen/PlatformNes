package com.jaen.pedro.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.jaen.pedro.utils.Assets;
import com.jaen.pedro.utils.Utils;

public class Enemy {
    private Floor floor;
    private TextureRegion region;
    private Vector2 position;
    private long startTime;

    public Enemy(Floor floor) {
        this.floor = floor;

        position=new Vector2(floor.getLeft(),floor.getTop());
        startTime = TimeUtils.nanoTime();
    }

    public void render(SpriteBatch batch){
        float elapsedTime = Utils.secondsSince(startTime);
        region= (TextureRegion) Assets.instance.enemigoAssets.caracol.getKeyFrame(elapsedTime,true);
        Utils.drawTextureRegion(batch,region,position.x,position.y);
    }
}
