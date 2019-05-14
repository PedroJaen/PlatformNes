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
    private int lvlCount;

    public Ammo(TiledMap map, Rectangle rectangle,int lvlCount) {
        this.map = map;
        this.rectangle = rectangle;
        this.lvlCount=lvlCount;
    }

    public void render(SpriteBatch batch){
        TextureRegion region=null;
        switch (lvlCount){
            case 0:
                region= Assets.instance.ataqueAssets.hacha;
                break;
            case 1:
                region= Assets.instance.ataqueAssets.fireball;
                break;
        }
        Utils.drawTextureRegion(batch,region,rectangle.getX(),rectangle.getY());
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

}
