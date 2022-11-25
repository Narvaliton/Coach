package com.example.coach.modele;

public class Profil {



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

    public Profil(int sexe, int poids, int taille, int age) {
        this.sexe = sexe;
        this.poids = poids;
        this.taille = taille;
        this.age = age;
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

}
