package com.example.sao_memorymatch.Model;

import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Observable;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;


public class CardModel extends Observable {

    private int[][] deck;
    private ArrayList<Integer> pairs;
    private int score = 0;
    private int multiplier = 1;


    public CardModel() {
        deck = new int[16][2];
        pairs = new ArrayList<>();
        score = 0;

        for(int i = 0; i < 16; i++) {
            deck[i][0] = 0;
            pairs.add((int)(Math.floor(i/2) + 16));
        }

        int numCards = -1;
        int index = -1;
        for(int i = 0; i < 16; i++) {
            numCards = pairs.size();
            index = (int)(Math.random()*numCards);

            int assign = pairs.get(index) - 16;
            deck[i][1] = assign;

            //Log.d("Test", "Pair: " + deck[i][1]);
            pairs.remove(index);

        }
    }

    private void resetallCards(final ArrayList<Button> btnList) {
        final Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                for(int i = 0; i < 16; i++) {
                    if(deck[i][0] == 1) {
                        deck[i][0] = 0;
                    }
                }

                setChanged();
                buttonsEnable(btnList, true);
                notifyObservers();
            }
        }, 1000);


    }

    public void buttonsEnable(ArrayList<Button> buttons, boolean enabled) {

        if(enabled) {
            for(int i = 0; i < 16; i++) {
                if(deck[i][0] != 2) {
                    buttons.get(i).setEnabled(true);
                }
            }
        } else {
            for (Button btn : buttons) {
                btn.setEnabled(false);
            }
        }
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;

        setChanged();
        notifyObservers(); //Find out what this does?
    }

    public int getOrientation(int cardId) {
        return deck[cardId][0];
    }

    public int getCardType(int cardId) {
        return deck[cardId][1];
    }

    public boolean checkWin() {

        for(int i = 0;  i < 16; i ++) {

            if( deck[i][0] != 2) {
                return false;
            }
        }
        return true;
    }

    public void setMultiplier (int multiplier) {
        this.multiplier = multiplier;
    }

    public boolean checkMatchingPair(ArrayList<Button> btnList) {
        int cardsUp = 0;
        int pairs[] = new int[2];

        for(int i = 0; i < 16; i++) {

            if(deck[i][0] == 1) {
                pairs[cardsUp] = i;
                cardsUp++;
            }

            if(cardsUp == 2) {
                buttonsEnable(btnList, false);
                //check if their ids match
                if(deck[pairs[0]][1] == deck[pairs[1]][1]) {

                    //clear the card
                    deck[pairs[0]][0] = 2;
                    deck[pairs[1]][0] = 2;
                    setScore(getScore() + multiplier);
                    buttonsEnable(btnList, true);
                    setChanged();
                    notifyObservers();
                    return true;
                } else {
                    resetallCards(btnList);
                }
                break;
            }

        }

        setChanged();
        notifyObservers();
        return false;
    }

    public void flipACard(int cardId) {
        deck[cardId][0] = 1;

        setChanged();
        notifyObservers();

    }


    //check match
}
