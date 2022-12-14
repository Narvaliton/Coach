package com.example.coach.vue;

import static com.example.coach.outils.MesOutils.*;
import static java.lang.String.format;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coach.R;
import com.example.coach.controleur.Controle;

public class CalculActivity extends AppCompatActivity {

    private RadioButton rdHomme;
    private RadioButton rdFemme;
    private EditText txtPoids;
    private EditText txtAge;
    private EditText txtTaille;
    private TextView lblIMG;
    private ImageView imgSmiley;
    private Button btnCalc;
    private Controle controleur;


    private void init(){
    rdHomme = (RadioButton) findViewById(R.id.rdHomme);
    rdFemme = (RadioButton) findViewById(R.id.rdFemme);
    txtPoids = (EditText) findViewById(R.id.txtPoids);
    txtAge = (EditText) findViewById(R.id.txtAge);
    txtTaille = (EditText) findViewById(R.id.txtTaille);
    lblIMG = (TextView) findViewById(R.id.lblIMG);
    imgSmiley = (ImageView) findViewById(R.id.imgSmiley);
    btnCalc = (Button) findViewById(R.id.btnCalc);
    controleur = Controle.getInstance(CalculActivity.this);
    ecouteCalcul();
    //recupProfil();
    }

    private void ecouteCalcul(){
        btnCalc.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                int poids = 0;
                int taille = 0;
                int age = 0;
                try {
                    poids = Integer.parseInt(txtPoids.getText().toString());
                    taille = Integer.parseInt(txtTaille.getText().toString());
                    age = Integer.parseInt(txtAge.getText().toString());
                } catch(Exception e){}
                int sexe = 0;

                if(rdHomme.isChecked()){
                    sexe = 1;
                }

                if(poids == 0 || age == 0 || taille == 0){
                    Toast.makeText(CalculActivity.this, "Veuillez saisir tout les champs", Toast.LENGTH_SHORT).show();
                }else{
                    controleur.creerProfil(taille, poids, age, sexe);
                    String message = controleur.getMessage();
                    float img = controleur.getImg();
                    if(message == "trop maigre"){
                        imgSmiley.setImageResource(R.drawable.maigre);
                        lblIMG.setTextColor(Color.RED);
                    }else if(message == "normal"){
                        imgSmiley.setImageResource(R.drawable.normal);
                        lblIMG.setTextColor(Color.GREEN);
                    }else{
                        imgSmiley.setImageResource(R.drawable.graisse);
                        lblIMG.setTextColor(Color.RED);
                    }
                    lblIMG.setText("IMG " + message + " : " + format2Decimal(img));

                }
            }
        });
        ((ImageButton) findViewById(R.id.btnAcceuil)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Intent intent = new Intent(CalculActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    public void recupProfil(){
        if(controleur.getTaille() != null){
            txtAge.setText("" + controleur.getAge());
            txtPoids.setText("" + controleur.getPoids());
            txtTaille.setText("" + controleur.getTaille());
            if (controleur.getSexe() == 1){
                rdHomme.setChecked(true);
                rdFemme.setChecked(false);
            }
            btnCalc.performClick();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcul);
        init();
    }
}