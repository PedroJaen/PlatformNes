package com.jaen.pedro.objects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;

public class Fruit {
    TiledMap map;
    Rectangle rectangle;

    public Fruit(TiledMap map, Rectangle rectangle) {
        this.map = map;
        this.rectangle = rectangle;
    }
}
