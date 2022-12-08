package com.example.coach.controleur;

import static com.example.coach.outils.Serializer.deSerialize;

import android.content.Context;
import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.Profil;
import com.example.coach.vue.CalculActivity;
import org.json.JSONArray;

import java.util.Date;


public final class Controle {

    private static String nomFic = "saveprofil";
    private static AccesDistant accesDistant;
    private static Context context;
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
    public final static Controle getInstance(Context contextValoriser){
        if(instance == null){
            if (contextValoriser != null){
                context = contextValoriser;
            }
            Controle.instance = new Controle();
            accesDistant = new AccesDistant();
            accesDistant.envoi("dernier", new JSONArray());
            //profil = accesLocal.recupDernier();
            //recupSerialize(context);
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
        profil = new Profil(sexe, poids, taille, age, new Date());
        accesDistant.envoi("enreg", profil.convertToJSONArray());
        //accesLocal.ajout(profil);
        //serialize(nomFic, profil, context);
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

    public void setProfil(Profil profil){
        this.profil = profil;
        ((CalculActivity)context).recupProfil();
    }

    public static void recupSerialize(Context context){
        profil = (Profil)deSerialize(nomFic, context);
    }
}
