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

import java.util.Arrays;
import java.util.Random;

public class UltraActivity extends AppCompatActivity implements View.OnTouchListener {

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
        for (int i = 27 * ran_nomb; i < 27 * ran_nomb + 9; i++) {
            tmp = arr[i + ligne1 * 9];
            arr[i + ligne1 * 9] = arr[i + ligne2 * 9];
            arr[i + ligne2 * 9] = tmp;
        }
        return;
    }

    //Fonction pour mélanger les colonnes
    public void perm_col(int[] arr, int col_nom, int ligne1, int ligne2) {
        int tmp;
        for (int i = 3 * col_nom; i <= 3 * col_nom + 72; i = i + 9) {
            tmp = arr[i + ligne1];
            arr[i + ligne1] = arr[i + ligne2];
            arr[i + ligne2] = tmp;
        }
        return;
    }

    //fonction pour mélanger les rangés dans les zones
    public void perm_zone_range(int[] arr, int rang1, int rang2) {
        int tmp;
        for (int i = 27 * rang1; i < 27 * rang1 + 27; i++) {
            tmp = arr[i];
            arr[i] = arr[i + 27 * (rang2 - rang1)];
            arr[i + 27 * (rang2 - rang1)] = tmp;
        }
        return;
    }

    //fonction pour mélanger les colonnes dans les zones
    public void perm_zone_col(int[] arr, int col1, int col2) {
        int tmp;
        for (int j = col1 * 3; j < col1 * 3 + 3; j++) {
            for (int i = j; i <= j + 72; i = i + 9) {
                tmp = arr[i];
                arr[i] = arr[i + 3 * (col2 - col1)];
                arr[i + 3 * (col2 - col1)] = tmp;
            }
        }
        return;
    }


    //fonction de mélange de tableau
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

    //fonction pour créer le niveau avec des cases vides
    public void niveau(boolean[] arr, String diff) {

        if (diff.equals("ultra")) {
            int[] indexarr = new int[60];
            for (int i = 0; i < 60; i++) {
                indexarr[i] = (int) Math.floor(Math.random()*81);
                Log.d("", indexarr[i]+"");
            }
            for (int i = 0; i < 81; i++) {
                for (int j = 0; j < 60; j++) {
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

    //fonction pour créer un tableau de l'état actuel
    public void NombresMasque(int[] nombres, int[] vrai, boolean[] masque) {
        for (int i = 0; i < 81; i++) {
            if (masque[i] == true){
                nombres[i] = 0;
            } else{
                nombres[i] = vrai[i];
            }
        }
        return;
    }

    public void victoire(){
        Intent myIntent = new Intent(UltraActivity.this, VictoireActivity.class);
        UltraActivity.this.startActivity(myIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultra);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        difficulter = (String) getIntent().getStringExtra("DIFF");
        Log.e("qwe", difficulter);
        Log.e("qwr", "facile");
        Log.e("qwe", (difficulter.equals("facile")) + "");


        // mélange au hasard le tableau
        melange(nbrVrai);
        //création du niveau avec des cases vides
        niveau(nbrMasque, difficulter);
        NombresMasque(nombres, nbrVrai, nbrMasque);
        //remplir les cellules du tableau
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


    public boolean onTouch(View v, MotionEvent event)
    {

        ImageView num01   = findViewById(R.id.num01);
        ImageView num01wh = findViewById(R.id.num01wh);
        ImageView num02   = findViewById(R.id.num02);
        ImageView num02wh = findViewById(R.id.num02wh);
        ImageView num03   = findViewById(R.id.num03);
        ImageView num03wh = findViewById(R.id.num03wh);
        ImageView num04   = findViewById(R.id.num04);
        ImageView num04wh = findViewById(R.id.num04wh);
        ImageView num05   = findViewById(R.id.num05);
        ImageView num05wh = findViewById(R.id.num05wh);
        ImageView num06   = findViewById(R.id.num06);
        ImageView num06wh = findViewById(R.id.num06wh);
        ImageView num07   = findViewById(R.id.num07);
        ImageView num07wh = findViewById(R.id.num07wh);
        ImageView num08   = findViewById(R.id.num08);
        ImageView num08wh = findViewById(R.id.num08wh);
        ImageView num09   = findViewById(R.id.num09);
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

                //rend les numéros visibles
                num01.setVisibility(View.VISIBLE);
                num02.setVisibility(View.VISIBLE);
                num03.setVisibility(View.VISIBLE);
                num04.setVisibility(View.VISIBLE);
                num05.setVisibility(View.VISIBLE);
                num06.setVisibility(View.VISIBLE);
                num07.setVisibility(View.VISIBLE);
                num08.setVisibility(View.VISIBLE);
                num09.setVisibility(View.VISIBLE);

                //défini les coordonnées de la sélection de chiffres
                num01.setX(crds[0]); num01.setY(crds[1] - 120);
                num01wh.setX(crds[0]); num01wh.setY(crds[1] - 120);
                num02.setX(crds[0] + 50); num02.setY(crds[1] - 112);
                num02wh.setX(crds[0] + 50); num02wh.setY(crds[1] - 112);
                num03.setX(crds[0] + 60); num03.setY(crds[1] - 60);
                num03wh.setX(crds[0] + 60); num03wh.setY(crds[1] - 60);
                num04.setX(crds[0] + 55); num04.setY(crds[1] - 10);
                num04wh.setX(crds[0] + 55); num04wh.setY(crds[1] - 10);
                num05.setX(crds[0] + 41); num05.setY(crds[1] + 40);
                num05wh.setX(crds[0] + 41); num05wh.setY(crds[1] + 40);
                num06.setX(crds[0] - 41); num06.setY(crds[1] + 40);
                num06wh.setX(crds[0] - 41); num06wh.setY(crds[1] + 40);
                num07.setX(crds[0] - 55); num07.setY(crds[1] - 10);
                num07wh.setX(crds[0] - 55); num07wh.setY(crds[1] - 10);
                num08.setX(crds[0] - 60); num08.setY(crds[1] - 60);
                num08wh.setX(crds[0] - 60); num08wh.setY(crds[1] - 60);
                num09.setX(crds[0] - 50); num09.setY(crds[1] - 112);
                num09wh.setX(crds[0] - 50); num09wh.setY(crds[1] - 112);

                break;
            case MotionEvent.ACTION_MOVE: // mouvement
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

                    if (angleI <= 20 || angleI > 340) {
                        nomb = 1;
                        num01wh.setVisibility(View.VISIBLE);
                        num02wh.setVisibility(View.GONE);
                        num03wh.setVisibility(View.GONE);
                        num04wh.setVisibility(View.GONE);
                        num05wh.setVisibility(View.GONE);
                        num06wh.setVisibility(View.GONE);
                        num07wh.setVisibility(View.GONE);
                        num08wh.setVisibility(View.GONE);
                        num09wh.setVisibility(View.GONE);
                    }
                    if (angleI <= 60 && angleI > 20) {
                        nomb = 2;
                        num01wh.setVisibility(View.GONE);
                        num02wh.setVisibility(View.VISIBLE);
                        num03wh.setVisibility(View.GONE);
                        num04wh.setVisibility(View.GONE);
                        num05wh.setVisibility(View.GONE);
                        num06wh.setVisibility(View.GONE);
                        num07wh.setVisibility(View.GONE);
                        num08wh.setVisibility(View.GONE);
                        num09wh.setVisibility(View.GONE);
                    }
                    if (angleI <= 100 && angleI > 60) {
                        nomb = 3;
                        num01wh.setVisibility(View.GONE);
                        num02wh.setVisibility(View.GONE);
                        num03wh.setVisibility(View.VISIBLE);
                        num04wh.setVisibility(View.GONE);
                        num05wh.setVisibility(View.GONE);
                        num06wh.setVisibility(View.GONE);
                        num07wh.setVisibility(View.GONE);
                        num08wh.setVisibility(View.GONE);
                        num09wh.setVisibility(View.GONE);
                    }
                    if (angleI <= 140 && angleI > 100) {
                        nomb = 4;
                        num01wh.setVisibility(View.GONE);
                        num02wh.setVisibility(View.GONE);
                        num03wh.setVisibility(View.GONE);
                        num04wh.setVisibility(View.VISIBLE);
                        num05wh.setVisibility(View.GONE);
                        num06wh.setVisibility(View.GONE);
                        num07wh.setVisibility(View.GONE);
                        num08wh.setVisibility(View.GONE);
                        num09wh.setVisibility(View.GONE);
                    }
                    if (angleI <= 180 && angleI > 140) {
                        nomb = 5;
                        num01wh.setVisibility(View.GONE);
                        num02wh.setVisibility(View.GONE);
                        num03wh.setVisibility(View.GONE);
                        num04wh.setVisibility(View.GONE);
                        num05wh.setVisibility(View.VISIBLE);
                        num06wh.setVisibility(View.GONE);
                        num07wh.setVisibility(View.GONE);
                        num08wh.setVisibility(View.GONE);
                        num09wh.setVisibility(View.GONE);
                    }
                    if (angleI <= 220 && angleI > 180) {
                        nomb = 6;
                        num01wh.setVisibility(View.GONE);
                        num02wh.setVisibility(View.GONE);
                        num03wh.setVisibility(View.GONE);
                        num04wh.setVisibility(View.GONE);
                        num05wh.setVisibility(View.GONE);
                        num06wh.setVisibility(View.VISIBLE);
                        num07wh.setVisibility(View.GONE);
                        num08wh.setVisibility(View.GONE);
                        num09wh.setVisibility(View.GONE);
                    }
                    if (angleI <= 260 && angleI > 220) {
                        nomb = 7;
                        num01wh.setVisibility(View.GONE);
                        num02wh.setVisibility(View.GONE);
                        num03wh.setVisibility(View.GONE);
                        num04wh.setVisibility(View.GONE);
                        num05wh.setVisibility(View.GONE);
                        num06wh.setVisibility(View.GONE);
                        num07wh.setVisibility(View.VISIBLE);
                        num08wh.setVisibility(View.GONE);
                        num09wh.setVisibility(View.GONE);
                    }
                    if (angleI <= 300 && angleI > 260) {
                        nomb = 8;
                        num01wh.setVisibility(View.GONE);
                        num02wh.setVisibility(View.GONE);
                        num03wh.setVisibility(View.GONE);
                        num04wh.setVisibility(View.GONE);
                        num05wh.setVisibility(View.GONE);
                        num06wh.setVisibility(View.GONE);
                        num07wh.setVisibility(View.GONE);
                        num08wh.setVisibility(View.VISIBLE);
                        num09wh.setVisibility(View.GONE);
                    }
                    if (angleI <= 340 && angleI > 300) {
                        nomb = 9;
                        num01wh.setVisibility(View.GONE);
                        num02wh.setVisibility(View.GONE);
                        num03wh.setVisibility(View.GONE);
                        num04wh.setVisibility(View.GONE);
                        num05wh.setVisibility(View.GONE);
                        num06wh.setVisibility(View.GONE);
                        num07wh.setVisibility(View.GONE);
                        num08wh.setVisibility(View.GONE);
                        num09wh.setVisibility(View.VISIBLE);
                    }

                    numbv.setTextColor(0xFFFFFFFF);
                    numbv.setText(nomb + "");
                } else {
                    numbv.setTextColor(0x00FFFFFF);
                    numbv.setText("");


                    num01wh.setVisibility(View.GONE);
                    num02wh.setVisibility(View.GONE);
                    num03wh.setVisibility(View.GONE);
                    num04wh.setVisibility(View.GONE);
                    num05wh.setVisibility(View.GONE);
                    num06wh.setVisibility(View.GONE);
                    num07wh.setVisibility(View.GONE);
                    num08wh.setVisibility(View.GONE);
                    num09wh.setVisibility(View.GONE);
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
                TextView graycover2 =  findViewById(R.id.graycover);
                graycover2.setBackgroundColor(0x00000000);
                numbv.setTextColor(0x00FFFFFF);
                numbv.setText("");

                //désactive la sélection des numéros
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

