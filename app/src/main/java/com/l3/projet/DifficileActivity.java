package com.l3.projet;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class DifficileActivity extends AppCompatActivity implements View.OnTouchListener {


    int[] nbrVrai =
                    {1,2,3,4,5,6,7,8,9,
                    4,5,6,7,8,9,1,2,3,
                    7,8,9,1,2,3,4,5,6,
                    2,3,4,5,6,7,8,9,1,
                    5,6,7,8,9,1,2,3,4,
                    8,9,1,2,3,4,5,6,7,
                    3,4,5,6,7,8,9,1,2,
                    6,7,8,9,1,2,3,4,5,
                    9,1,2,3,4,5,6,7,8};
    int[] nombres = new int[81];
    boolean[] nbrMasque = new boolean[81];
    TextView[] element = new TextView[81];

    String difficulter;
    double x;
    double y;
    double x0;
    double y0;
    double angleI;
    int nomb;



    //Fonction pour mélanger les rangés
    public void perm_range(int[] arr, int ran_nomb, int ligne1, int ligne2) {
        int tmp;
        for (int i = 27*ran_nomb; i < 27*ran_nomb+9; i++) {
            tmp = arr[i+ligne1*9];
            arr[i+ligne1*9] = arr[i + ligne2*9];
            arr[i + ligne2*9] = tmp;
        }
        return;
    }

    //Fonction pour mélanger les colonnes
    public void perm_col(int[] arr, int col_nom, int ligne1, int ligne2) {
        int tmp;
        for (int i = 3*col_nom; i <= 3*col_nom+72; i = i + 9) {
            tmp = arr[i+ligne1];
            arr[i+ligne1] = arr[i+ligne2];
            arr[i+ligne2] = tmp;
        }
        return;
    }

    //fonction pour mélanger les rangés dans les zones
    public void perm_zone_range(int[] arr, int rang1, int rang2) {
        int tmp;
        for (int i = 27*rang1; i < 27*rang1+27; i++) {
            tmp = arr[i];
            arr[i] = arr[i+27*(rang2-rang1)];
            arr[i+27*(rang2-rang1)] = tmp;
        }
        return;
    }

    //fonction pour mélanger les colonnes dans les zones
    public void perm_zone_col(int[] arr, int col1, int col2) {
        int tmp;
        for (int j = col1*3; j < col1*3+3; j++) {
            for (int i = j; i <= j+72; i=i+9) {
                tmp = arr[i];
                arr[i] = arr[i+3*(col2-col1)];
                arr[i+3*(col2-col1)] = tmp;
            }
        }
        return;
    }


    public void melange(int[] arr) {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            int aa = (int) Math.floor(Math.random()*3);
            int bb = (int) Math.floor(Math.random()*2);
            int cc = (int) Math.floor(Math.random()*2) + 1;
            perm_range(arr, aa, bb, cc);
            perm_col(arr, aa, bb, cc);
            perm_zone_range(arr, bb, cc);
            perm_zone_col(arr, bb, cc);
        }
    }


    public static void niveau(boolean[] arr, String diff) {

        if (diff.equals("difficile")) {
            int[] indexarr = new int[50];
            for (int i = 0; i < 50; i++) {
                indexarr[i] = (int) Math.floor(Math.random()*81);
                Log.d("", indexarr[i]+"");
            }
            for (int i = 0; i < 81; i++) {
                for (int j = 0; j < 50; j++) {
                    if (i == indexarr[j]){
                        arr[i] = true;
                        break;
                    }
                    else{
                        arr[i] = false;
                    }
                }
            }
        }
    }


    public void Nombres_Masque(int[] nombres, int[] vrai, boolean[] masque) {
        for (int i = 0; i < 81; i++) {
            if (masque[i] == true){
                nombres[i] = 0;
            }
            else{
                nombres[i] = vrai[i];
            }
        }
        return;
    }

    public void victoire()
    {
        Intent myIntent = new Intent(DifficileActivity.this, VictoireActivity.class);
        DifficileActivity.this.startActivity(myIntent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        difficulter = (String) getIntent().getStringExtra("DIFF");
        Log.e("qwe", difficulter);
        Log.e("qwr", "facile");
        Log.e("qwe", (difficulter.equals("facile")) + "");


        melange(nbrVrai);
        niveau(nbrMasque, difficulter);
        Nombres_Masque(nombres, nbrVrai, nbrMasque);


        for (int i=0; i<81; i++) {
            String txtID;
            if (i<10) {
                txtID = "cell0" + i;
            } else {
                txtID = "cell" + i;
            }

            int resID = getResources().getIdentifier(txtID, "id", getPackageName());

            element[i] = findViewById(resID);

            if (nbrMasque[i] == false) {
                element[i].setText(nbrVrai[i] + "");
            }
            if (nbrMasque[i] == true) {
                element[i].setText("");
                element[i].setOnTouchListener(this);
            }
        }
    }

    //===============================================================================================//
    public void boucle(int MIN, int MAX, int PAS, ArrayList<ImageView> liste){

        ImageView num01 = findViewById(R.id.num01);
        ImageView num02 = findViewById(R.id.num02);
        ImageView num03 = findViewById(R.id.num03);
        ImageView num04 = findViewById(R.id.num04);
        ImageView num05 = findViewById(R.id.num05);
        ImageView num06 = findViewById(R.id.num06);
        ImageView num07 = findViewById(R.id.num07);
        ImageView num08 = findViewById(R.id.num08);
        ImageView num09 = findViewById(R.id.num09);

        for (int i = MIN; i < MAX; i=i+PAS) {

            if (nombres[i] == 1) liste.add(num01);
            if (nombres[i] == 2) liste.add(num02);
            if (nombres[i] == 3) liste.add(num03);
            if (nombres[i] == 4) liste.add(num04);
            if (nombres[i] == 5) liste.add(num05);
            if (nombres[i] == 6) liste.add(num06);
            if (nombres[i] == 7) liste.add(num07);
            if (nombres[i] == 8) liste.add(num08);
            if (nombres[i] == 9) liste.add(num09);
        }
    }

    public void inCheck(ArrayList<ImageView> liste){

        ImageView num01 = findViewById(R.id.num01);
        ImageView num02 = findViewById(R.id.num02);
        ImageView num03 = findViewById(R.id.num03);
        ImageView num04 = findViewById(R.id.num04);
        ImageView num05 = findViewById(R.id.num05);
        ImageView num06 = findViewById(R.id.num06);
        ImageView num07 = findViewById(R.id.num07);
        ImageView num08 = findViewById(R.id.num08);
        ImageView num09 = findViewById(R.id.num09);

        if (liste.contains(num01)) num01.setVisibility(View.GONE);
        if (liste.contains(num02)) num02.setVisibility(View.GONE);
        if (liste.contains(num03)) num03.setVisibility(View.GONE);
        if (liste.contains(num04)) num04.setVisibility(View.GONE);
        if (liste.contains(num05)) num05.setVisibility(View.GONE);
        if (liste.contains(num06)) num06.setVisibility(View.GONE);
        if (liste.contains(num07)) num07.setVisibility(View.GONE);
        if (liste.contains(num08)) num08.setVisibility(View.GONE);
        if (liste.contains(num09)) num09.setVisibility(View.GONE);
    }

// vérification des colonnes

    public void check_column( View v ) {

        ArrayList<ImageView> liste = new ArrayList<ImageView>();

        TextView cell00 = findViewById(R.id.cell00);
        TextView cell01 = findViewById(R.id.cell01);
        TextView cell02 = findViewById(R.id.cell02);
        TextView cell03 = findViewById(R.id.cell03);
        TextView cell04 = findViewById(R.id.cell04);
        TextView cell05 = findViewById(R.id.cell05);
        TextView cell06 = findViewById(R.id.cell06);
        TextView cell07 = findViewById(R.id.cell07);
        TextView cell08 = findViewById(R.id.cell08);

        TextView cell09 = findViewById(R.id.cell09);
        TextView cell10 = findViewById(R.id.cell10);
        TextView cell11 = findViewById(R.id.cell11);
        TextView cell12 = findViewById(R.id.cell12);
        TextView cell13 = findViewById(R.id.cell13);
        TextView cell14 = findViewById(R.id.cell14);
        TextView cell15 = findViewById(R.id.cell15);
        TextView cell16 = findViewById(R.id.cell16);
        TextView cell17 = findViewById(R.id.cell17);

        TextView cell18 = findViewById(R.id.cell18);
        TextView cell19 = findViewById(R.id.cell19);
        TextView cell20 = findViewById(R.id.cell20);
        TextView cell21 = findViewById(R.id.cell21);
        TextView cell22 = findViewById(R.id.cell22);
        TextView cell23 = findViewById(R.id.cell23);
        TextView cell24 = findViewById(R.id.cell24);
        TextView cell25 = findViewById(R.id.cell25);
        TextView cell26 = findViewById(R.id.cell26);

        TextView cell27 = findViewById(R.id.cell27);
        TextView cell28 = findViewById(R.id.cell28);
        TextView cell29 = findViewById(R.id.cell29);
        TextView cell30 = findViewById(R.id.cell30);
        TextView cell31 = findViewById(R.id.cell31);
        TextView cell32 = findViewById(R.id.cell32);
        TextView cell33 = findViewById(R.id.cell33);
        TextView cell34 = findViewById(R.id.cell34);
        TextView cell35 = findViewById(R.id.cell35);

        TextView cell36 = findViewById(R.id.cell36);
        TextView cell37 = findViewById(R.id.cell37);
        TextView cell38 = findViewById(R.id.cell38);
        TextView cell39 = findViewById(R.id.cell39);
        TextView cell40 = findViewById(R.id.cell40);
        TextView cell41 = findViewById(R.id.cell41);
        TextView cell42 = findViewById(R.id.cell42);
        TextView cell43 = findViewById(R.id.cell43);
        TextView cell44 = findViewById(R.id.cell44);

        TextView cell45 = findViewById(R.id.cell45);
        TextView cell46 = findViewById(R.id.cell46);
        TextView cell47 = findViewById(R.id.cell47);
        TextView cell48 = findViewById(R.id.cell48);
        TextView cell49 = findViewById(R.id.cell49);
        TextView cell50 = findViewById(R.id.cell50);
        TextView cell51 = findViewById(R.id.cell51);
        TextView cell52 = findViewById(R.id.cell52);
        TextView cell53 = findViewById(R.id.cell53);

        TextView cell54 = findViewById(R.id.cell54);
        TextView cell55 = findViewById(R.id.cell55);
        TextView cell56 = findViewById(R.id.cell56);
        TextView cell57 = findViewById(R.id.cell57);
        TextView cell58 = findViewById(R.id.cell58);
        TextView cell59 = findViewById(R.id.cell59);
        TextView cell60 = findViewById(R.id.cell60);
        TextView cell61 = findViewById(R.id.cell61);
        TextView cell62 = findViewById(R.id.cell62);

        TextView cell63 = findViewById(R.id.cell63);
        TextView cell64 = findViewById(R.id.cell64);
        TextView cell65 = findViewById(R.id.cell65);
        TextView cell66 = findViewById(R.id.cell66);
        TextView cell67 = findViewById(R.id.cell67);
        TextView cell68 = findViewById(R.id.cell68);
        TextView cell69 = findViewById(R.id.cell69);
        TextView cell70 = findViewById(R.id.cell70);
        TextView cell71 = findViewById(R.id.cell71);

        TextView cell72 = findViewById(R.id.cell72);
        TextView cell73 = findViewById(R.id.cell73);
        TextView cell74 = findViewById(R.id.cell74);
        TextView cell75 = findViewById(R.id.cell75);
        TextView cell76 = findViewById(R.id.cell76);
        TextView cell77 = findViewById(R.id.cell77);
        TextView cell78 = findViewById(R.id.cell78);
        TextView cell79 = findViewById(R.id.cell79);
        TextView cell80 = findViewById(R.id.cell80);

        if(v == cell00 ||v == cell09 ||v == cell18 ||v == cell27 ||v == cell36 ||v == cell45 ||v == cell54 ||v == cell63 ||v == cell72) {
            boucle(0,73,9,liste);
            inCheck(liste);
            liste.clear();
        }

        if(v == cell01 ||v == cell10 ||v == cell19 ||v == cell28 ||v == cell37 ||v == cell46 ||v == cell55 ||v == cell64 ||v == cell73) {
            boucle(1,74,9,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell02 ||v == cell11 ||v == cell20 ||v == cell29 ||v == cell38 ||v == cell47 ||v == cell56 ||v == cell65 ||v == cell74 ) {
            boucle(2,75,9,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell03 ||v == cell12 ||v == cell21 ||v == cell30 ||v == cell39 ||v == cell48 ||v == cell57 ||v == cell66 ||v == cell75) {
            boucle(3,76,9,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell04 ||v == cell13 ||v == cell22 ||v == cell31 ||v == cell40 ||v == cell49 ||v == cell58 ||v == cell67 ||v == cell76 ) {
            boucle(4,77,9,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell05 ||v == cell14 ||v == cell23 ||v == cell32 ||v == cell41 ||v == cell50 ||v == cell59 ||v == cell68 ||v == cell77 ) {
            boucle(5,78,9,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell06 ||v == cell15 ||v == cell24 ||v == cell33 ||v == cell42 ||v == cell51 ||v == cell60 ||v == cell69 ||v == cell78) {
            boucle(6,79,9,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell07 ||v == cell16 ||v == cell25 ||v == cell34 ||v == cell43 ||v == cell52 ||v == cell61 ||v == cell70 ||v == cell79) {
            boucle(7,80,9,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell08 ||v == cell17 ||v == cell26 ||v == cell35 ||v == cell44 ||v == cell53 ||v == cell62 ||v == cell71 ||v == cell80) {
            boucle(8,81,9,liste);
            inCheck(liste);
            liste.clear();
        }
    }



// vérification des lignes

    public void check_line( View v ) {

        ArrayList<ImageView> liste = new ArrayList<ImageView>();

        TextView cell00 = findViewById(R.id.cell00);
        TextView cell01 = findViewById(R.id.cell01);
        TextView cell02 = findViewById(R.id.cell02);
        TextView cell03 = findViewById(R.id.cell03);
        TextView cell04 = findViewById(R.id.cell04);
        TextView cell05 = findViewById(R.id.cell05);
        TextView cell06 = findViewById(R.id.cell06);
        TextView cell07 = findViewById(R.id.cell07);
        TextView cell08 = findViewById(R.id.cell08);

        TextView cell09 = findViewById(R.id.cell09);
        TextView cell10 = findViewById(R.id.cell10);
        TextView cell11 = findViewById(R.id.cell11);
        TextView cell12 = findViewById(R.id.cell12);
        TextView cell13 = findViewById(R.id.cell13);
        TextView cell14 = findViewById(R.id.cell14);
        TextView cell15 = findViewById(R.id.cell15);
        TextView cell16 = findViewById(R.id.cell16);
        TextView cell17 = findViewById(R.id.cell17);

        TextView cell18 = findViewById(R.id.cell18);
        TextView cell19 = findViewById(R.id.cell19);
        TextView cell20 = findViewById(R.id.cell20);
        TextView cell21 = findViewById(R.id.cell21);
        TextView cell22 = findViewById(R.id.cell22);
        TextView cell23 = findViewById(R.id.cell23);
        TextView cell24 = findViewById(R.id.cell24);
        TextView cell25 = findViewById(R.id.cell25);
        TextView cell26 = findViewById(R.id.cell26);

        TextView cell27 = findViewById(R.id.cell27);
        TextView cell28 = findViewById(R.id.cell28);
        TextView cell29 = findViewById(R.id.cell29);
        TextView cell30 = findViewById(R.id.cell30);
        TextView cell31 = findViewById(R.id.cell31);
        TextView cell32 = findViewById(R.id.cell32);
        TextView cell33 = findViewById(R.id.cell33);
        TextView cell34 = findViewById(R.id.cell34);
        TextView cell35 = findViewById(R.id.cell35);

        TextView cell36 = findViewById(R.id.cell36);
        TextView cell37 = findViewById(R.id.cell37);
        TextView cell38 = findViewById(R.id.cell38);
        TextView cell39 = findViewById(R.id.cell39);
        TextView cell40 = findViewById(R.id.cell40);
        TextView cell41 = findViewById(R.id.cell41);
        TextView cell42 = findViewById(R.id.cell42);
        TextView cell43 = findViewById(R.id.cell43);
        TextView cell44 = findViewById(R.id.cell44);

        TextView cell45 = findViewById(R.id.cell45);
        TextView cell46 = findViewById(R.id.cell46);
        TextView cell47 = findViewById(R.id.cell47);
        TextView cell48 = findViewById(R.id.cell48);
        TextView cell49 = findViewById(R.id.cell49);
        TextView cell50 = findViewById(R.id.cell50);
        TextView cell51 = findViewById(R.id.cell51);
        TextView cell52 = findViewById(R.id.cell52);
        TextView cell53 = findViewById(R.id.cell53);

        TextView cell54 = findViewById(R.id.cell54);
        TextView cell55 = findViewById(R.id.cell55);
        TextView cell56 = findViewById(R.id.cell56);
        TextView cell57 = findViewById(R.id.cell57);
        TextView cell58 = findViewById(R.id.cell58);
        TextView cell59 = findViewById(R.id.cell59);
        TextView cell60 = findViewById(R.id.cell60);
        TextView cell61 = findViewById(R.id.cell61);
        TextView cell62 = findViewById(R.id.cell62);

        TextView cell63 = findViewById(R.id.cell63);
        TextView cell64 = findViewById(R.id.cell64);
        TextView cell65 = findViewById(R.id.cell65);
        TextView cell66 = findViewById(R.id.cell66);
        TextView cell67 = findViewById(R.id.cell67);
        TextView cell68 = findViewById(R.id.cell68);
        TextView cell69 = findViewById(R.id.cell69);
        TextView cell70 = findViewById(R.id.cell70);
        TextView cell71 = findViewById(R.id.cell71);

        TextView cell72 = findViewById(R.id.cell72);
        TextView cell73 = findViewById(R.id.cell73);
        TextView cell74 = findViewById(R.id.cell74);
        TextView cell75 = findViewById(R.id.cell75);
        TextView cell76 = findViewById(R.id.cell76);
        TextView cell77 = findViewById(R.id.cell77);
        TextView cell78 = findViewById(R.id.cell78);
        TextView cell79 = findViewById(R.id.cell79);
        TextView cell80 = findViewById(R.id.cell80);

        if(v == cell00 ||v == cell01 ||v == cell02 ||v == cell03 ||v == cell04 ||v == cell05 ||v == cell06 ||v == cell07 ||v == cell08) {
            boucle(0,9,1,liste);
            inCheck(liste);
            liste.clear();
        }

        if(v == cell09 ||v == cell10 ||v == cell11 ||v == cell12 ||v == cell13 ||v == cell14 ||v == cell15 ||v == cell16 ||v == cell17) {
            boucle(9,18,1,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell18 ||v == cell19 ||v == cell20 ||v == cell21 ||v == cell22 ||v == cell23 ||v == cell24 ||v == cell25 ||v == cell26 ) {
            boucle(18,27,1,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell27 ||v == cell28 ||v == cell29 ||v == cell30 ||v == cell31 ||v == cell32 ||v == cell33 ||v == cell34 ||v == cell35) {
            boucle(27,36,1,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell36 ||v == cell37 ||v == cell38 ||v == cell39 ||v == cell40 ||v == cell41 ||v == cell42 ||v == cell43 ||v == cell44 ) {
            boucle(36,45,1,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell45 ||v == cell46 ||v == cell47 ||v == cell48 ||v == cell49 ||v == cell50 ||v == cell51 ||v == cell52 ||v == cell53 ) {
            boucle(45,54,1,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell54 ||v == cell55 ||v == cell56 ||v == cell57 ||v == cell58 ||v == cell59 ||v == cell60 ||v == cell61 ||v == cell62) {
            boucle(54,63,1,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell63 ||v == cell64 ||v == cell65 ||v == cell66 ||v == cell67 ||v == cell68 ||v == cell69 ||v == cell70 ||v == cell71) {
            boucle(63,72,1,liste);
            inCheck(liste);
            liste.clear();
        }
        if(v == cell72 ||v == cell73 ||v == cell74 ||v == cell75 ||v == cell76 ||v == cell77 ||v == cell78 ||v == cell79 ||v == cell80) {
            boucle(72,81,1,liste);
            inCheck(liste);
            liste.clear();
        }
    }

// vérification zone

    public void add_liste_zone(int i,ArrayList<ImageView> liste){

        ImageView num01 = findViewById(R.id.num01);
        ImageView num02 = findViewById(R.id.num02);
        ImageView num03 = findViewById(R.id.num03);
        ImageView num04 = findViewById(R.id.num04);
        ImageView num05 = findViewById(R.id.num05);
        ImageView num06 = findViewById(R.id.num06);
        ImageView num07 = findViewById(R.id.num07);
        ImageView num08 = findViewById(R.id.num08);
        ImageView num09 = findViewById(R.id.num09);

        if (nombres[i] == 1) liste.add(num01);
        if (nombres[i] == 2) liste.add(num02);
        if (nombres[i] == 3) liste.add(num03);
        if (nombres[i] == 4) liste.add(num04);
        if (nombres[i] == 5) liste.add(num05);
        if (nombres[i] == 6) liste.add(num06);
        if (nombres[i] == 7) liste.add(num07);
        if (nombres[i] == 8) liste.add(num08);
        if (nombres[i] == 9) liste.add(num09);

    }

    public void check_zone(int[] nombres, View v ){

        TextView cell00 = findViewById(R.id.cell00);
        TextView cell01 = findViewById(R.id.cell01);
        TextView cell02 = findViewById(R.id.cell02);
        TextView cell03 = findViewById(R.id.cell03);
        TextView cell04 = findViewById(R.id.cell04);
        TextView cell05 = findViewById(R.id.cell05);
        TextView cell06 = findViewById(R.id.cell06);
        TextView cell07 = findViewById(R.id.cell07);
        TextView cell08 = findViewById(R.id.cell08);

        TextView cell09 = findViewById(R.id.cell09);
        TextView cell10 = findViewById(R.id.cell10);
        TextView cell11 = findViewById(R.id.cell11);
        TextView cell12 = findViewById(R.id.cell12);
        TextView cell13 = findViewById(R.id.cell13);
        TextView cell14 = findViewById(R.id.cell14);
        TextView cell15 = findViewById(R.id.cell15);
        TextView cell16 = findViewById(R.id.cell16);
        TextView cell17 = findViewById(R.id.cell17);

        TextView cell18 = findViewById(R.id.cell18);
        TextView cell19 = findViewById(R.id.cell19);
        TextView cell20 = findViewById(R.id.cell20);
        TextView cell21 = findViewById(R.id.cell21);
        TextView cell22 = findViewById(R.id.cell22);
        TextView cell23 = findViewById(R.id.cell23);
        TextView cell24 = findViewById(R.id.cell24);
        TextView cell25 = findViewById(R.id.cell25);
        TextView cell26 = findViewById(R.id.cell26);

        TextView cell27 = findViewById(R.id.cell27);
        TextView cell28 = findViewById(R.id.cell28);
        TextView cell29 = findViewById(R.id.cell29);
        TextView cell30 = findViewById(R.id.cell30);
        TextView cell31 = findViewById(R.id.cell31);
        TextView cell32 = findViewById(R.id.cell32);
        TextView cell33 = findViewById(R.id.cell33);
        TextView cell34 = findViewById(R.id.cell34);
        TextView cell35 = findViewById(R.id.cell35);

        TextView cell36 = findViewById(R.id.cell36);
        TextView cell37 = findViewById(R.id.cell37);
        TextView cell38 = findViewById(R.id.cell38);
        TextView cell39 = findViewById(R.id.cell39);
        TextView cell40 = findViewById(R.id.cell40);
        TextView cell41 = findViewById(R.id.cell41);
        TextView cell42 = findViewById(R.id.cell42);
        TextView cell43 = findViewById(R.id.cell43);
        TextView cell44 = findViewById(R.id.cell44);

        TextView cell45 = findViewById(R.id.cell45);
        TextView cell46 = findViewById(R.id.cell46);
        TextView cell47 = findViewById(R.id.cell47);
        TextView cell48 = findViewById(R.id.cell48);
        TextView cell49 = findViewById(R.id.cell49);
        TextView cell50 = findViewById(R.id.cell50);
        TextView cell51 = findViewById(R.id.cell51);
        TextView cell52 = findViewById(R.id.cell52);
        TextView cell53 = findViewById(R.id.cell53);

        TextView cell54 = findViewById(R.id.cell54);
        TextView cell55 = findViewById(R.id.cell55);
        TextView cell56 = findViewById(R.id.cell56);
        TextView cell57 = findViewById(R.id.cell57);
        TextView cell58 = findViewById(R.id.cell58);
        TextView cell59 = findViewById(R.id.cell59);
        TextView cell60 = findViewById(R.id.cell60);
        TextView cell61 = findViewById(R.id.cell61);
        TextView cell62 = findViewById(R.id.cell62);

        TextView cell63 = findViewById(R.id.cell63);
        TextView cell64 = findViewById(R.id.cell64);
        TextView cell65 = findViewById(R.id.cell65);
        TextView cell66 = findViewById(R.id.cell66);
        TextView cell67 = findViewById(R.id.cell67);
        TextView cell68 = findViewById(R.id.cell68);
        TextView cell69 = findViewById(R.id.cell69);
        TextView cell70 = findViewById(R.id.cell70);
        TextView cell71 = findViewById(R.id.cell71);

        TextView cell72 = findViewById(R.id.cell72);
        TextView cell73 = findViewById(R.id.cell73);
        TextView cell74 = findViewById(R.id.cell74);
        TextView cell75 = findViewById(R.id.cell75);
        TextView cell76 = findViewById(R.id.cell76);
        TextView cell77 = findViewById(R.id.cell77);
        TextView cell78 = findViewById(R.id.cell78);
        TextView cell79 = findViewById(R.id.cell79);
        TextView cell80 = findViewById(R.id.cell80);


        ArrayList<ImageView> liste = new ArrayList<ImageView>();

        int i,j;

        int[] zone1 = {0,1,2,9,10,11,18,19,20};
        int[] zone2 = {3,4,5,12,13,14,21,22,23};
        int[] zone3 = {6,7,8,15,16,17,24,25,26};
        int[] zone4 = {27,28,29,36,37,38,45,46,47};
        int[] zone5 = {30,31,32,39,40,41,48,49,50};
        int[] zone6 = {33,34,35,42,43,44,51,52,53};
        int[] zone7 = {54,55,56,63,64,65,72,73,74};
        int[] zone8 = {57,58,59,66,67,68,75,76,77};
        int[] zone9 = {60,61,62,69,70,71,78,79,80};

        if(v == cell00 ||v == cell01 ||v == cell02 ||v == cell09 ||v == cell10 ||v == cell11 ||v == cell18 ||v == cell19 ||v == cell20) {
            for(j=0;j<9;j++){
                i = zone1[j];
                add_liste_zone(i,liste);
            }
            inCheck(liste);
            liste.clear();
        }
        if(v == cell03 ||v == cell04 ||v == cell05 ||v == cell12 ||v == cell13 ||v == cell14 ||v == cell21 ||v == cell22 ||v == cell23) {
            for(j=0;j<9;j++){
                i = zone2[j];
                add_liste_zone(i,liste);
            }
            inCheck(liste);
            liste.clear();
        }
        if(v == cell06 ||v == cell07 ||v == cell08 ||v == cell15 ||v == cell16 ||v == cell17 ||v == cell24 ||v == cell25 ||v == cell26) {
            for(j=0;j<9;j++){
                i = zone3[j];
                add_liste_zone(i,liste);
            }
            inCheck(liste);
            liste.clear();
        }
        if(v == cell27 ||v == cell28 ||v == cell29 ||v == cell36 ||v == cell37 ||v == cell38 ||v == cell45 ||v == cell46 ||v == cell47) {
            for(j=0;j<9;j++){
                i = zone4[j];
                add_liste_zone(i,liste);
            }
            inCheck(liste);
            liste.clear();
        }
        if(v == cell30 ||v == cell31 ||v == cell32 ||v == cell39 ||v == cell40 ||v == cell41 ||v == cell48 ||v == cell49 ||v == cell50) {
            for(j=0;j<9;j++){
                i = zone5[j];
                add_liste_zone(i,liste);
            }
            inCheck(liste);
            liste.clear();
        }
        if(v == cell33 ||v == cell34 ||v == cell35 ||v == cell42 ||v == cell43 ||v == cell44 ||v == cell51 ||v == cell52 ||v == cell53) {
            for(j=0;j<9;j++){
                i = zone6[j];
                add_liste_zone(i,liste);
            }
            inCheck(liste);
            liste.clear();
        }
        if(v == cell54 ||v == cell55 ||v == cell56 ||v == cell63 ||v == cell64 ||v == cell65 ||v == cell72 ||v == cell73 ||v == cell74) {
            for(j=0;j<9;j++){
                i = zone7[j];
                add_liste_zone(i,liste);
            }
            inCheck(liste);
            liste.clear();
        }
        if(v == cell57 ||v == cell58 ||v == cell59 ||v == cell66 ||v == cell67 ||v == cell68 ||v == cell75 ||v == cell76 ||v == cell77) {
            for(j=0;j<9;j++){
                i = zone8[j];
                add_liste_zone(i,liste);
            }
            inCheck(liste);
            liste.clear();
        }
        if(v == cell60 ||v == cell61 ||v == cell62 ||v == cell69 ||v == cell70 ||v == cell71 ||v == cell78 ||v == cell79 ||v == cell80) {
            for(j=0;j<9;j++){
                i = zone9[j];
                add_liste_zone(i,liste);
            }
            inCheck(liste);
            liste.clear();
        }
    }

//===============================================================================================//

    public boolean onTouch(View v, MotionEvent event)
    {

        ImageView num01 = findViewById(R.id.num01);
        ImageView num01wh = findViewById(R.id.num01wh);
        ImageView num02 = findViewById(R.id.num02);
        ImageView num02wh = findViewById(R.id.num02wh);
        ImageView num03 = findViewById(R.id.num03);
        ImageView num03wh = findViewById(R.id.num03wh);
        ImageView num04 = findViewById(R.id.num04);
        ImageView num04wh = findViewById(R.id.num04wh);
        ImageView num05 = findViewById(R.id.num05);
        ImageView num05wh = findViewById(R.id.num05wh);
        ImageView num06 = findViewById(R.id.num06);
        ImageView num06wh = findViewById(R.id.num06wh);
        ImageView num07 = findViewById(R.id.num07);
        ImageView num07wh = findViewById(R.id.num07wh);
        ImageView num08 = findViewById(R.id.num08);
        ImageView num08wh = findViewById(R.id.num08wh);
        ImageView num09 = findViewById(R.id.num09);
        ImageView num09wh = findViewById(R.id.num09wh);


        TextView vw = (TextView) v;
        x = event.getX();
        y = event.getY();
        TextView numbv = findViewById(R.id.nomb);


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x0 = x;
                y0 = y;
                v.setBackgroundColor(0xFF555555);
                vw.setTextColor(Color.WHITE);

                int[] crds = new int[2];
                v.getLocationOnScreen(crds);

                TextView graycover = findViewById(R.id.graycover);
                graycover.setBackgroundColor(0x77000000);

//===============================================================================================//
                num01.setVisibility(View.VISIBLE);
                num02.setVisibility(View.VISIBLE);
                num03.setVisibility(View.VISIBLE);
                num04.setVisibility(View.VISIBLE);
                num05.setVisibility(View.VISIBLE);
                num06.setVisibility(View.VISIBLE);
                num07.setVisibility(View.VISIBLE);
                num08.setVisibility(View.VISIBLE);
                num09.setVisibility(View.VISIBLE);

                // fonctions de vérification des lignes, colonnes et zones
                check_line( v );
                check_column( v );
                check_zone( nombres, v );

//===============================================================================================//


                num01.setX(crds[0]); num01.setY(crds[1] - 120);
                num01wh.setX(crds[0]); num01wh.setY(crds[1] - 120);
                num02.setX(crds[0] + 50); num02.setY(crds[1] - 112);
                num02wh.setX(crds[0] + 50); num02wh.setY(crds[1] - 112);
                num03.setX(crds[0] + 60); num03.setY(crds[1] - 60);
                num03wh.setX(crds[0] + 60); num03wh.setY(crds[1] - 60);
                num04.setX(crds[0] + 60); num04.setY(crds[1] - 10);
                num04wh.setX(crds[0] + 60); num04wh.setY(crds[1] - 10);
                num05.setX(crds[0] + 55); num05.setY(crds[1] + 40);
                num05wh.setX(crds[0] + 55); num05wh.setY(crds[1] + 40);
                num06.setX(crds[0] - 55); num06.setY(crds[1] + 40);
                num06wh.setX(crds[0] - 55); num06wh.setY(crds[1] + 40);
                num07.setX(crds[0] - 60); num07.setY(crds[1] - 10);
                num07wh.setX(crds[0] - 60); num07wh.setY(crds[1] - 10);
                num08.setX(crds[0] - 60); num08.setY(crds[1] - 60);
                num08wh.setX(crds[0] - 60); num08wh.setY(crds[1] - 60);
                num09.setX(crds[0] - 50); num09.setY(crds[1] - 112);
                num09wh.setX(crds[0] - 50); num09wh.setY(crds[1] - 112);


                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0)) >= 50) {
                    if (x > x0 && y <= y0) {
                        angleI = Math.toDegrees(Math.asin((x - x0) / (Math.sqrt(Math.pow(x - x0, 2) + Math.pow(y - y0, 2)))));
                    }
                    if (x >= x0 && y > y0) {
                        angleI = 90 + Math.toDegrees(Math.acos((x - x0) / (Math.sqrt(Math.pow(x - x0, 2) + Math.pow(y - y0, 2)))));
                    }
                    if (x < x0 && y >= y0) {
                        angleI = 90 + Math.toDegrees(Math.acos((x - x0) / (Math.sqrt(Math.pow(x - x0, 2) + Math.pow(y - y0, 2)))));
                    }
                    if (x <= x0 && y < y0) {
                        angleI = 360 + Math.toDegrees(Math.asin((x - x0) / (Math.sqrt(Math.pow(x - x0, 2) + Math.pow(y - y0, 2)))));
                    }

                    num01wh.setVisibility(View.GONE);
                    num02wh.setVisibility(View.GONE);
                    num03wh.setVisibility(View.GONE);
                    num04wh.setVisibility(View.GONE);
                    num05wh.setVisibility(View.GONE);
                    num06wh.setVisibility(View.GONE);
                    num07wh.setVisibility(View.GONE);
                    num08wh.setVisibility(View.GONE);
                    num09wh.setVisibility(View.GONE);

                    if (angleI <= 20 || angleI > 360) {
                        nomb = 1;
                        num01wh.setVisibility(View.VISIBLE);
                    }
                    if (angleI <= 40 && angleI > 20) {
                        nomb = 2;
                        num02wh.setVisibility(View.VISIBLE);
                    }
                    if (angleI <= 80 && angleI > 40) {
                        nomb = 3;
                        num03wh.setVisibility(View.VISIBLE);
                    }
                    if (angleI <= 120 && angleI > 80) {
                        nomb = 4;
                        num04wh.setVisibility(View.VISIBLE);
                    }
                    if (angleI <= 180 && angleI > 120) {
                        nomb = 5;
                        num05wh.setVisibility(View.VISIBLE);
                    }
                    if (angleI <= 240 && angleI > 180) {
                        nomb = 6;
                        num06wh.setVisibility(View.VISIBLE);
                    }
                    if (angleI <= 280 && angleI > 240) {
                        nomb = 7;
                        num07wh.setVisibility(View.VISIBLE);
                    }
                    if (angleI <= 320 && angleI > 280) {
                        nomb = 8;
                        num08wh.setVisibility(View.VISIBLE);
                    }
                    if (angleI <= 340 && angleI > 320) {
                        nomb = 9;
                        num09wh.setVisibility(View.VISIBLE);
                    }

                    numbv.setTextColor(0xFFFFFFFF);
                    numbv.setText(nomb + "");
                } else {
                    numbv.setTextColor(0x00FFFFFF);
                    numbv.setText("");
                }
                break;
            case MotionEvent.ACTION_UP:
                v.setBackgroundColor(Color.GREEN);
                vw.setTextColor(0xFF555555);
                if (Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0)) >= 50) {
                    TextView currentCell = (TextView) v;
                    currentCell.setText(nomb + "");
                    String strid = new String(currentCell.getResources().getResourceName(currentCell.getId()));
                    strid = strid.substring(30);
                    int intid = Integer.parseInt(strid);

                    nombres[intid] = nomb;

                    if (Arrays.equals(nombres, nbrVrai)) {
                        {
                            victoire();
                        }
                        for (int i = 0; i < 81; i++) {
                            element[i].setOnTouchListener(null);
                        }
                    }
                }
                TextView graycover2 = findViewById(R.id.graycover);
                graycover2.setBackgroundColor(0x00000000);
                numbv.setTextColor(0x00FFFFFF);
                numbv.setText("");


                num01.setVisibility(View.GONE);
                num02.setVisibility(View.GONE);
                num03.setVisibility(View.GONE);
                num04.setVisibility(View.GONE);
                num05.setVisibility(View.GONE);
                num06.setVisibility(View.GONE);
                num07.setVisibility(View.GONE);
                num08.setVisibility(View.GONE);
                num09.setVisibility(View.GONE);
                num01wh.setVisibility(View.GONE);
                num02wh.setVisibility(View.GONE);
                num03wh.setVisibility(View.GONE);
                num04wh.setVisibility(View.GONE);
                num05wh.setVisibility(View.GONE);
                num06wh.setVisibility(View.GONE);
                num07wh.setVisibility(View.GONE);
                num08wh.setVisibility(View.GONE);
                num09wh.setVisibility(View.GONE);

                break;
            case MotionEvent.ACTION_CANCEL:
                v.setBackgroundColor(0x00555555);
                vw.setTextColor(Color.WHITE);
                numbv.setTextColor(0x00FFFFFF);
                numbv.setText("");
                break;
        }
        return true;
    }

}
