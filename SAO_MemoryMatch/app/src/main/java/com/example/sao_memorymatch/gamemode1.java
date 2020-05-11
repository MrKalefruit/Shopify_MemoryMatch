package com.example.memorymatchgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import android.widget.TextView;


import com.example.sao_memorymatch.Model.CardModel;
import com.example.sao_memorymatch.R;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class gamemode1 extends AppCompatActivity implements Observer, View.OnClickListener {

    private CardModel deck;
    private TextView score;

    private int counter;
    private int dp1;

    private Drawable asuna;
    private Drawable alice;
    private Drawable klain;
    private Drawable kirito;
    private Drawable slica;
    private Drawable sinon;
    private Drawable leafa;
    private Drawable yui;
    private Drawable backcard;
    private Drawable emptycard;

    private ArrayList<Button> btnList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamemode1);

        dp1 = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1,
                this.getResources().getDisplayMetrics());

        score = findViewById(R.id.tv_Score);

        backcard = getDrawable(R.drawable.card_facedown);
        emptycard = getDrawable(R.drawable.card_cleared);
        asuna = getDrawable(R.drawable.card_asuna);
        alice= getDrawable(R.drawable.alice);
        klain = getDrawable(R.drawable.card_klain);
        kirito = getDrawable(R.drawable.card_kirito);
        slica = getDrawable(R.drawable.god_kun);
        sinon = getDrawable(R.drawable.sinon);
        leafa = getDrawable(R.drawable.leafa);
        yui = getDrawable(R.drawable.yui);

        deck = new CardModel();
        deck.addObserver(this);

        counter = 0;
        btnList = new ArrayList<>();

        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gameGrid);

        gridLayout.removeAllViews();
        int total = 16; //Totall what???
        int column = 4; //
        int row = total / column;

        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row + 1);

        for(int i =0, c = 0, r = 0; i < total; i++, c++){
            if(c == column){
                c = 0;
                r++;
            }

            Button btnCard = new Button(this);

            btnCard.setBackground(backcard);
            btnCard.setOnClickListener(this);
            btnCard.setId(i);
            btnList.add(btnCard);

            GridLayout.LayoutParams param =new GridLayout.LayoutParams();
            param.height = 110*dp1;
            param.width = 110*dp1;
            param.rightMargin = 10 * dp1;
            param.topMargin = 10 * dp1;
            param.leftMargin = 10 * dp1;
            param.bottomMargin = 10 * dp1;
            param.setGravity(Gravity.CENTER);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            btnCard.setLayoutParams (param);
            gridLayout.addView(btnCard);
        }

    }

    public void onClick(View v) {

        switch(v.getId()) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
                Log.d("Test", "Button: " + v.getId() + "was clicked");
                deck.flipACard(v.getId());

                if (deck.checkMatchingPair(btnList) && deck.getCardType(v.getId()) == 0) {
                    deck.setMultiplier(2);
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setCancelable(true);
                    builder.setTitle("Klain!");
                    builder.setMessage("You Found Klain, all points from now on are doubled!");

                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();

                }

                if(deck.checkWin()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);

                    builder.setCancelable(true);
                    builder.setTitle("Win Screen");
                    builder.setMessage("Congrats you win");

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });

                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            openMainMenu();
                        }
                    });

                    builder.show();
                }

        }
    }

    @Override
    public void update(Observable o, Object arg) {

        for(int i = 0; i < 16; i++) {
            Log.d("Test", "Card:" + i + " is currently: " + deck.getOrientation(i));

            if(deck.getOrientation(i) == 1) {
                Drawable image = getBackgroundImage(deck.getCardType(i));
                btnList.get(i).setBackground(image);

            } else if(deck.getOrientation(i) == 0) {
                btnList.get(i).setBackground(backcard);
            } else if(deck.getOrientation(i) == 2) {
                btnList.get(i).setBackground(emptycard); //disable the button
                btnList.get(i).setEnabled(false);
            }


        }

        score.setText("Score: " + deck.getScore()); //This thing isnt getting called

    }

    public void openMainMenu() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private Drawable getBackgroundImage(int cardId) {
        Drawable image = null;

        switch (cardId) {
            case 0:
                image = klain; break;
            case 1:
                image = alice; break;
            case 2:
                image = asuna; break;
            case 3:
                image = kirito; break;
            case 4:
                image = god; break;
            case 5:
                image = sinon; break;
            case 6:
                image = yuna; break;
            case 7:
                image = yui; break;

        }

        return image;
    }
}
