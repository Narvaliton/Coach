package com.example.coach.controleur;

import static com.example.coach.outils.Serializer.deSerialize;
import static com.example.coach.outils.Serializer.serialize;
import android.content.Context;
import com.example.coach.modele.Profil;


public final class Controle {

    private static String nomFic = "saveprofil";


    /**
     * Singleton de l'instance Controle
     */
    private static Controle instance = null;

    /**
     * Attribut de type profil
     */
    private static Profil profil;

    /**
     * Constructeur du controleur
     */
    private Controle() {
        super();
    }

    /**
     * Retourne l'instance du controleur
     * @return
     */
    public final static Controle getInstance(Context context){
        if(instance == null){
            Controle.instance = new Controle();
            recupSerialize(context);
        }
        return Controle.instance;
    }

    /**
     * Création du profil
     * @param taille en cm
     * @param poids
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(int taille, int poids, int age, int sexe, Context context){
        profil = new Profil(sexe, poids, taille, age);
        serialize(nomFic, profil, context);
    }

    /**
     * Retourne l'img du profil
     * @return
     */
    public float getImg(){
        return profil.getImg();
    }

    /**
     * Retourne le message correspondant à l'img du profil
     * @return
     */
    public String getMessage(){
        return profil.getMessage();
    }

    public Integer getTaille(){
        if (profil == null){
            return null;
        }else{
            return profil.getTaille();
        }
    }

    public Integer getPoids(){
        if (profil == null){
            return null;
        }else{
            return profil.getPoids();
        }
    }

    public Integer getAge(){
        if (profil == null){
            return null;
        }else{
            return profil.getAge();
        }
    }

    public Integer getSexe(){
        if (profil == null){
            return null;
        }else{
            return profil.getSexe();
        }
    }

    public static void recupSerialize(Context context){
        profil = (Profil)deSerialize(nomFic, context);
    }
}
