package com.jaen.pedro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.Map;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.jaen.pedro.objects.Ammo;
import com.jaen.pedro.objects.Death;
import com.jaen.pedro.objects.Enemy;
import com.jaen.pedro.objects.Exit;
import com.jaen.pedro.objects.Floor;
import com.jaen.pedro.objects.Fruit;
import com.jaen.pedro.objects.Hero;
import com.jaen.pedro.objects.Key;
import com.jaen.pedro.objects.Level;

import java.util.Iterator;

public class WorldCreator {
    private TiledMap map;
    private Level level;
    private Enums.Difficulty difficulty;

    public WorldCreator(TiledMap map, Enums.Difficulty difficulty) {
        this.map = map;
        this.difficulty=difficulty;
    }

    public Level worldCreator() {
        level= new Level(difficulty);

        Array<Floor> floors=new Array<Floor>();
        //recorremos los objetos suelo
        for(MapObject object:map.getLayers().get(Constants.LVL_GROUND).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            floors.add(new Floor(map,rectangle));

        }

        DelayedRemovalArray<Enemy> enemies=new DelayedRemovalArray<Enemy>();
        //recorremos los objetos suelo que contienen enemigo
        for(MapObject object:map.getLayers().get(Constants.LVL_ENEMIE).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            Floor f=new Floor(map,rectangle);
            floors.add(f);
            enemies.add(new Enemy(f,difficulty,level));

        }
        level.setFloors(floors);
        level.setEnemies(enemies);

        Exit exit=null;
        //recorremos el objeto salida
        for(MapObject object:map.getLayers().get(Constants.LVL_EXIT).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            exit=new Exit(map,rectangle);

        }
        level.setExit(exit);

        Hero hero=null;
        //recorremos el objeto heroe
        for(MapObject object:map.getLayers().get(Constants.LVL_HEROE).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            hero=new Hero(map,rectangle,level);

        }

        Array<Death> deaths=new Array<Death>();
        //recorremos el objeto muerte
        for(MapObject object:map.getLayers().get(Constants.LVL_DEATH).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle =((RectangleMapObject)object).getRectangle();

            deaths.add(new Death(map,rectangle));

        }
        hero.setDeaths(deaths);
        level.setHero(hero);

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
