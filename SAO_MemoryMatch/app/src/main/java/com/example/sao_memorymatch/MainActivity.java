package com.example.memorymatchgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.sao_memorymatch.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button game1 = findViewById(R.id.btn_game1);
        Button game2 = findViewById(R.id.btn_game2);

        game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to the next screen
                openGame1();
            }
        });
    }

    public void openGame1() {
        Intent intent = new Intent(this, Gamemode1Activity.class);
        startActivity(intent);
    }
}