package com.example.coach.modele;

import static com.example.coach.outils.MesOutils.convertStringToDate;

import android.util.Log;

import com.example.coach.controleur.Controle;
import com.example.coach.outils.AccesHTTP;
import com.example.coach.outils.AsyncResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
            } if(message[0].equals("tous")){
                try {
                    JSONArray jsonArray = new JSONArray((message[1]));
                    ArrayList<Profil> listeProfil = new ArrayList<Profil>();
                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        int poids = jsonObject.getInt("poids");
                        int taille = jsonObject.getInt("taille");
                        int sexe = jsonObject.getInt("sexe");
                        int age = jsonObject.getInt("age");
                        Date datemesure = convertStringToDate(jsonObject.getString("datemesure"), "yyyy-MM-dd HH:mm:ss");
                        Profil profil = new Profil(sexe, poids, taille, age, datemesure);
                        listeProfil.add(profil);
                    }
                    controle.setLesProfils(listeProfil);
                } catch (JSONException e) {
                    System.out.print("MArchepas");
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
