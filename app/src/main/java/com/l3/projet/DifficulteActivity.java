package com.l3.projet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DifficulteActivity extends AppCompatActivity {
    View facileButton;
    View moyenButton;
    View difficileButton;
    View ultraButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulte);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        facileButton = findViewById(R.id.facileButton);
        View.OnClickListener facileModeStart = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficulteActivity.this, GameActivity.class);
                intent.putExtra("DIFF", "facile");
                startActivity(intent);

            }
        };
        facileButton.setOnClickListener(facileModeStart);


        moyenButton = findViewById(R.id.moyenButton);
        View.OnClickListener moyenModeStart = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficulteActivity.this, MoyenActivity.class);
                intent.putExtra("DIFF", "moyen");
                startActivity(intent);

            }
        };
        moyenButton.setOnClickListener(moyenModeStart);


        difficileButton = findViewById(R.id.difficileButton);
        View.OnClickListener difficileModeStart = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficulteActivity.this, DifficileActivity.class);
                intent.putExtra("DIFF", "difficile");
                startActivity(intent);

            }
        };
        difficileButton.setOnClickListener(difficileModeStart);


        ultraButton = findViewById(R.id.ultraButton);
        View.OnClickListener ultraModeStart = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DifficulteActivity.this, UltraActivity.class);
                intent.putExtra("DIFF", "ultra");
                startActivity(intent);

            }
        };
        ultraButton.setOnClickListener(ultraModeStart);


    }
}
