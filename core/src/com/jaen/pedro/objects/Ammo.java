package com.jaen.pedro.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.jaen.pedro.utils.Assets;
import com.jaen.pedro.utils.Utils;

public class Ammo {
    TiledMap map;
    Rectangle rectangle;

    public Ammo(TiledMap map, Rectangle rectangle) {
        this.map = map;
        this.rectangle = rectangle;
    }

    public void render(SpriteBatch batch){
        TextureRegion region= Assets.instance.ataqueAssets.hacha;
        Utils.drawTextureRegion(batch,region,rectangle.getX(),rectangle.getY());
    }
}
