package com.jaen.pedro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeSet;

public class Preferencias {
    private Preferences prefs;
    private boolean mute;
    private TreeSet<Long> puntuaciones;

    public Preferencias() {
        prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
        this.puntuaciones=new TreeSet<Long>();

        cargaPuntuaciones();
        cargaMute();
    }

    public void guardarDatos(){
        int contador=0;
        Iterator<Long> it=puntuaciones.iterator();
        while(contador<5){
            if(it.hasNext()){
                prefs.putLong("puntuacion"+contador, it.next());
            }else{
                prefs.putLong("puntuacion"+contador, 0);
            }
            contador++;
        }

        prefs.putBoolean("mute",mute);
        prefs.flush();
    }

    public boolean isMute() {
        return mute;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public void addPuntuacion(long score){
        puntuaciones.add(score);
    }

    public TreeSet<Long> getPuntuaciones() {
        return puntuaciones;
    }

    public void cargaPuntuaciones(){
        for(int i=0;i<5;i++){
            puntuaciones.add(prefs.getLong("puntuacion"+i));
        }
    }

    public void cargaMute(){
        mute=prefs.getBoolean("mute");
    }
}
