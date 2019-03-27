package com.jaen.pedro.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.jaen.pedro.objects.Death;
import com.jaen.pedro.objects.Exit;
import com.jaen.pedro.objects.Floor;
import com.jaen.pedro.objects.Fruit;
import com.jaen.pedro.objects.Key;
import com.jaen.pedro.objects.Level;

public class WorldCreator {
    TiledMap map;
    Level level;

    public WorldCreator(TiledMap map) {
        this.map = map;
    }

    public Level worldCreator() {
        level= new Level();

        //recorremos el objeto heroe?

        Array<Floor> floors=new Array<Floor>();
        //recorremos los objetos suelo
        for(MapObject object:map.getLayers().get(Constants.LVL_GROUND).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            floors.add(new Floor(map,rectangle));

        }
        level.setFloors(floors);

        Exit exit=null;
        //recorremos el objeto salida
        for(MapObject object:map.getLayers().get(Constants.LVL_EXIT).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            exit=new Exit(map,rectangle);

        }
        level.setExit(exit);

        Array<Death> deaths=new Array<Death>();
        //recorremos el objeto muerte
        for(MapObject object:map.getLayers().get(Constants.LVL_DEATH).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            deaths.add(new Death(map,rectangle));

        }
        level.setDeaths(deaths);

        Key key=null;
        //recorremos el objeto llave
        for(MapObject object:map.getLayers().get(Constants.LVL_LLAVE).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            key=new Key(map,rectangle);
        }
        level.setKey(key);

        DelayedRemovalArray<Fruit> fruits=new DelayedRemovalArray<Fruit>();
        //recorremos el objeto fruta
        for(MapObject object:map.getLayers().get(Constants.LVL_FRUTA).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            fruits.add(new Fruit(map,rectangle));
        }
        level.setFruits(fruits);

        return level;

    }
}
