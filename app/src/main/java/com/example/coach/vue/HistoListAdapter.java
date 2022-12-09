package com.example.coach.vue;

import static com.example.coach.outils.MesOutils.*;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.coach.R;
import com.example.coach.controleur.Controle;
import com.example.coach.modele.Profil;

import java.util.ArrayList;

public class HistoListAdapter extends BaseAdapter {

    private ArrayList<Profil> lesProfils;
    private LayoutInflater inflater;
    private Controle controle;
    private Context context;

    public HistoListAdapter(ArrayList liste, Context context){
        controle = Controle.getInstance(null);
        this.context = context;
        lesProfils = new ArrayList<Profil>(liste);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lesProfils.size();
    }

    @Override
    public Object getItem(int i) {
        return lesProfils.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewProperties{
        TextView txtListeDate;
        TextView txtListIMG;
        ImageButton btnListSuppr;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
       ViewProperties viewProperties;
       if(convertView == null){
           viewProperties = new ViewProperties();
           convertView = inflater.inflate(R.layout.layout_liste_histo, null);
           viewProperties.txtListeDate = ((TextView) convertView.findViewById(R.id.txtListeDate));
           viewProperties.txtListIMG = ((TextView) convertView.findViewById(R.id.txtListIMG));
           viewProperties.btnListSuppr = ((ImageButton) convertView.findViewById(R.id.btnListSuppr));
           convertView.setTag(viewProperties);
       }else {
           viewProperties = (ViewProperties) convertView.getTag();
       }
       Profil profil = (Profil) getItem(i);
       viewProperties.txtListIMG.setText("" + format2Decimal(profil.getImg()));
       viewProperties.txtListeDate.setText("" + convertDateToString(profil.getDateMesure()));


       /*
       Ajout de l'evenement sur le bouton btnListSuppr
        */
       viewProperties.btnListSuppr.setTag(i);
       viewProperties.btnListSuppr.setOnClickListener(new Button.OnClickListener(){
           public void onClick(View v){
               int indice = (Integer)v.getTag();
               controle.delProfil(lesProfils.get(indice));
               lesProfils.remove(indice);
               notifyDataSetChanged();
           }
       });

       return convertView;
    }




}
