package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.coach.R;
import com.example.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    private Controle controle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        creerMenu();
    }

    private void init(){
        controle = Controle.getInstance(MainActivity.this);
    }

    private void creerMenu(){
        ecouteMenu((ImageButton) findViewById(R.id.btnMonIMG), CalculActivity.class);
        ecouteMenu((ImageButton) findViewById(R.id.btnMonHistorique), HistoActivity.class);
    }

    private void ecouteMenu(ImageButton imgBtn, final Class classe){
        imgBtn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, classe);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }

        });
    }
}