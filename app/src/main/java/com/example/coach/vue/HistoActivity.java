package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;

import java.util.ArrayList;
import java.util.Collections;

public class HistoActivity extends AppCompatActivity {

    private Controle controle;
    private ImageButton btnAcceuilHisto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_histo);
        init();
    }

    private void init(){
        controle = Controle.getInstance(null);
        btnAcceuilHisto = (ImageButton) findViewById(R.id.btnAcceuilHisto);
        btnAcceuilHisto.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                Intent intent = new Intent(HistoActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        creerListe();
    }


    private void creerListe(){
        ArrayList<Profil> liste = controle.getLesProfils();
        if(liste.size() > 0){
            Collections.sort(liste, Collections.<Profil>reverseOrder());
        }
        if(liste != null){
            ListView listView = findViewById(R.id.lstHisto);
            HistoListAdapter adapter = new HistoListAdapter(liste, HistoActivity.this);
            listView.setAdapter(adapter);
        }
    }
}