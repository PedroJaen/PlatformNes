package com.jaen.pedro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;

public class Preferencias {
    private Preferences prefs;
    private boolean mute;
    private TreeMap<Long,String> puntuaciones;

    public Preferencias() {
        prefs = Gdx.app.getPreferences(Constants.PREFERENCES);
        this.puntuaciones=new TreeMap<Long,String>(java.util.Collections.reverseOrder());

        cargaPuntuaciones();
        cargaMute();
    }

    public void guardarDatos(){
        int contador=0;
        Iterator<Long> it=puntuaciones.keySet().iterator();
        while(contador<5){
            if(it.hasNext()){
                long puntuacion=it.next();
                String letras=puntuaciones.get(puntuacion);
                prefs.putLong("puntuacion"+contador, puntuacion);
                prefs.putString("letras"+contador,letras);
            }else{
                prefs.putLong("puntuacion"+contador, 0);
                prefs.putString("letras"+contador,"AAA");
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

    public void addPuntuacion(long score,String letras){
        puntuaciones.put(score,letras);
    }

    public TreeMap<Long,String> getPuntuaciones() {
        return puntuaciones;
    }

    public void cargaPuntuaciones(){
        for(int i=0;i<5;i++){
            puntuaciones.put(
                    prefs.getLong("puntuacion"+i),
                    prefs.getString("letras"+i));
        }
    }

    public void cargaMute(){
        mute=prefs.getBoolean("mute");
    }
}
