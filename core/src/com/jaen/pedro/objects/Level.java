package com.jaen.pedro.objects;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;

public class Level {
    private Hero hero;
    private Key key;
    private DelayedRemovalArray<Fruit> fruits;
    private Array<Floor> floors;
    private Exit exit;
    private Array<Death> deaths;
}
