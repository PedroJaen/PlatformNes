package com.jaen.pedro.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.jaen.pedro.objects.Death;
import com.jaen.pedro.objects.Exit;
import com.jaen.pedro.objects.Floor;
import com.jaen.pedro.objects.Fruit;
import com.jaen.pedro.objects.Key;

public class WorldCreator {

    public WorldCreator(TiledMap map) {
        //recorremos los objetos suelo
        for(MapObject object:map.getLayers().get(Constants.LVL_GROUND).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            new Floor(map,rectangle);
        }

        //recorremos el objeto salida
        for(MapObject object:map.getLayers().get(Constants.LVL_EXIT).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            new Exit(map,rectangle);

        }

        //recorremos el objeto muerte
        for(MapObject object:map.getLayers().get(Constants.LVL_DEATH).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            new Death(map,rectangle);

        }

        //recorremos el objeto llave
        for(MapObject object:map.getLayers().get(Constants.LVL_LLAVE).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            new Key(map,rectangle);
        }

        //recorremos el objeto fruta
        for(MapObject object:map.getLayers().get(Constants.LVL_FRUTA).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            new Fruit(map,rectangle);
        }
    }
}
