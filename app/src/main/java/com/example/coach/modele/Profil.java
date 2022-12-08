package com.example.coach.modele;

import static com.example.coach.outils.MesOutils.convertDateToString;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Profil implements Serializable {



    private static final Integer minFemme = 15; //maigre si en dessous
    private static final Integer maxFemme = 30; //gros si au dessus
    private static final Integer minHomme = 10; //maigre si en dessous
    private static final Integer maxHomme = 25; //gros si au dessus

    private int sexe;
    private int poids;
    private int taille;
    private int age;
    private float img;
    private String message;
    private Date dateMesure;

    /**
     * Constructeur de Profil
     * @param sexe
     * @param poids
     * @param taille
     * @param age
     */
    public Profil(int sexe, int poids, int taille, int age, Date date) {
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.dateMesure = date;
        calculIMG();
        resultIMG();
    }

    public int getSexe() {
        return sexe;
    }

    public int getPoids() {
        return poids;
    }

    public int getTaille() {
        return taille;
    }

    public int getAge() {
        return age;
    }

    public float getImg() {
        return img;
    }

    public String getMessage() {
        return message;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    private void calculIMG(){
        this.img =  (float)(((1.2 * (float)getPoids()) / Math.pow((float)getTaille()/100, 2))
                        + (0.23 * (float)getAge()) - (10.83 *  (float)getSexe()) - 5.4);
    }

    private void resultIMG(){
        if (getSexe() == 0){
            if(getImg() < 15){
                this.message = "trop maigre";
            }
            else if(getImg() > 30){
                this.message = "trop élevé";
            }
            else{
                this.message = "normal";
            }
        }
        else{
            if(getImg() < 10){
                this.message = "trop maigre";
            }
            else if(getImg() > 25){
                this.message = "trop de graisse";
            }
            else{
                this.message = "normal";
            }
        }
    }

    public JSONArray convertToJSONArray(){
        List liste = new ArrayList();
        liste.add(convertDateToString(getDateMesure()));
        liste.add(getPoids());
        liste.add(getTaille());
        liste.add(getAge());
        liste.add(getSexe());
        return new JSONArray(liste);
    }
}
