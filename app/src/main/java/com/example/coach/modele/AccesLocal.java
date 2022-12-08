package com.example.coach.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.coach.outils.MesOutils;
import com.example.coach.outils.MySQLiteOpenHelper;

import java.util.Date;

public class AccesLocal {
    private static final String nomBase = "bdCoach.sqlite";
    private static final int versionBase = 1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase bd;

    public AccesLocal(Context context){
        this.accesBD = new MySQLiteOpenHelper(context, nomBase, versionBase);
    }

    public void ajout(Profil profil){
        this.bd = accesBD.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("poids", profil.getPoids());
        values.put("taille", profil.getTaille());
        values.put("age", profil.getAge());
        values.put("sexe", profil.getSexe());
        values.put("datemesure", (profil.getDateMesure()).toString());
        bd.insert("profil", null, values);
        bd.close();
    }

    public Profil recupDernier(){
        Profil profil = null;
        this.bd = accesBD.getReadableDatabase();
        String req = "SELECT * FROM profil";
        Cursor curseur = bd.rawQuery(req, null);
        curseur.moveToLast();
        if(curseur.isAfterLast() == false){
            profil = new Profil(curseur.getInt(4), curseur.getInt(1), curseur.getInt(2)
                    , curseur.getInt(3), MesOutils.convertStringToDate(curseur.getString(0)));
        }
        curseur.close();
        return profil;
    }

}
