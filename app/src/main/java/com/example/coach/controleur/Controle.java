package com.example.coach.controleur;

import com.example.coach.modele.Profil;

public final class Controle {

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
    public final static Controle getInstance(){
        if(instance == null){
            Controle.instance = new Controle();
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
    public void creerProfil(int taille, int poids, int age, int sexe){
        profil = new Profil(sexe, poids, taille, age);
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
}
