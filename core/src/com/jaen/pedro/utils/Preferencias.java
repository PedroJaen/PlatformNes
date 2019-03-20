package com.jaen.pedro.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.ArrayList;
import java.util.Collections;

public class Preferencias {
    private Preferences prefs;
    private boolean mute;
    private ArrayList<Long> puntuaciones;

    public Preferencias() {
        prefs = Gdx.app.getPreferences("preferences");
        this.puntuaciones=new ArrayList<Long>(6);

        if(puntuaciones.size()==0){
            for(int i=0;i<puntuaciones.size();i++){
                puntuaciones.add(i,0l);
            }
        }
        
    }

    public void guardarDatos(){
        for(int i=0;i<5;i++){
            prefs.putLong("puntuacion"+i,puntuaciones.get(i));
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
        Collections.sort(puntuaciones);
        Collections.reverse(puntuaciones);
        puntuaciones.add(5,score);
        Collections.sort(puntuaciones);
        Collections.reverse(puntuaciones);
    }

    public ArrayList<Long> getPuntuaciones() {
        return puntuaciones;
    }

    public void cargaPuntuaciones(){
        for(int i=0;i<5;i++){
            puntuaciones.add(i,prefs.getLong("puntuacion"+i));
        }
    }

    public void cargaMute(){
        mute=prefs.getBoolean("mute");
    }
}
