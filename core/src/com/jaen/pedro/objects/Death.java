package com.jaen.pedro.objects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class Death {
    TiledMap map;
    Rectangle rectangle;

    public Death(TiledMap map, Rectangle rectangle) {
        this.map = map;
        this.rectangle = rectangle;
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
