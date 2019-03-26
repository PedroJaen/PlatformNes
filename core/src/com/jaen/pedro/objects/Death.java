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
}
