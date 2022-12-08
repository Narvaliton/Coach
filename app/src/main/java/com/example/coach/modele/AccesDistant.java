package com.example.coach.modele;

import static com.example.coach.outils.MesOutils.convertStringToDate;

import android.util.Log;

import com.example.coach.controleur.Controle;
import com.example.coach.outils.AccesHTTP;
import com.example.coach.outils.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class AccesDistant implements AsyncResponse {
    public final static String SERVERADDR = "http://192.168.0.17/coach/serveurcoach.php";
    private static Controle controle;

    public AccesDistant(){
        controle = Controle.getInstance(null);
    }

    @Override
    public void processFinish(String output) {
        Log.d("serveur", "************" + output);
        String[] message = output.split("%");
        if(message.length > 1){
            if(message[0].equals("enreg")){
                Log.d(message[0], message[1]);
            } if(message[0].equals("dernier")){
                try {
                    JSONObject info = new JSONObject(message[1]);
                    Date date = convertStringToDate(info.getString("datemesure"), "yyyy-MM-dd hh:mm:ss");
                    Integer poids = info.getInt("poids");
                    Integer taille = info.getInt("taille");
                    Integer age = info.getInt("age");
                    Integer sexe = info.getInt("sexe");
                    Profil profil = new Profil(sexe, poids, taille, age, date);
                    controle.setProfil(profil);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //controle.setProfil();
            } if(message[0].equals("Erreur !")){
                Log.d(message[0], message[1]);
            }
        }
    }

    public void envoi(String operation, JSONArray lesDonneesJSON){
        AccesHTTP accesDonnees = new AccesHTTP();
        accesDonnees.delegate = this;
        accesDonnees.addParam("operation", operation);
        accesDonnees.addParam("lesdonnees", lesDonneesJSON.toString());
        accesDonnees.execute(SERVERADDR);
    }
}
