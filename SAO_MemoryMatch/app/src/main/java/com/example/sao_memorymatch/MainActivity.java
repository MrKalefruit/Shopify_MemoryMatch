package com.example.sao_memorymatch;

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
        Button exit = findViewById(R.id.btn_exit);

        game1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to the next screen
                openGame1();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //move to the next screen
                finish();
            }
        });
    }

    public void openGame1() {
        Intent intent = new Intent(this, gamemode1.class);
        startActivity(intent);
    }
}