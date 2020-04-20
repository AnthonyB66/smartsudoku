package com.l3.projet;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView startButton;
    TextView FabButton;
    TextView EButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        startButton = findViewById(R.id.startButton);


        View.OnClickListener oclStartButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DifficulteActivity.class);
                startActivity(intent);


            }
        };

        startButton.setOnClickListener(oclStartButton);


        FabButton = findViewById(R.id.FabButton);


        View.OnClickListener oclFabButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReglesActivity.class);
                startActivity(intent);


            }
        };

        FabButton.setOnClickListener(oclFabButton);


        EButton  =  findViewById(R.id.EButton);
        EButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Exit");
                builder.setMessage("Voulez-vous vraiment quitter ??");
                builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        finish();

                    }
                });

                builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}