package com.jaen.pedro.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.jaen.pedro.objects.Ammo;
import com.jaen.pedro.objects.Death;
import com.jaen.pedro.objects.Exit;
import com.jaen.pedro.objects.Floor;
import com.jaen.pedro.objects.Fruit;
import com.jaen.pedro.objects.Hero;
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


        Hero hero=null;
        //recorremos el objeto heroe
        for(MapObject object:map.getLayers().get(Constants.LVL_HEROE).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            hero=new Hero(map,rectangle);

        }
        level.setHero(hero);

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

        DelayedRemovalArray<Key> key=new DelayedRemovalArray<Key>(1);
        //recorremos el objeto llave
        for(MapObject object:map.getLayers().get(Constants.LVL_LLAVE).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            key.add(new Key(map,rectangle));
        }
        level.setKey(key);

        DelayedRemovalArray<Fruit> fruits=new DelayedRemovalArray<Fruit>();
        //recorremos el objeto fruta
        for(MapObject object:map.getLayers().get(Constants.LVL_FRUTA).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            fruits.add(new Fruit(map,rectangle));
        }
        level.setFruits(fruits);

        DelayedRemovalArray<Ammo> ammunition=new DelayedRemovalArray<Ammo>();
        //recorremos el objeto ammo
        for(MapObject object:map.getLayers().get(Constants.LVL_AMMO).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            ammunition.add(new Ammo(map,rectangle));
        }
        level.setAmmunition(ammunition);

        return level;

    }
}
