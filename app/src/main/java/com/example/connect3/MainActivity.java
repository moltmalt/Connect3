package com.example.connect3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener  {

    Button restart;
    TextView playerWho;

    String player = "Player 1";

    int[][] colorArray = {{1,1,1,1,1},
                                {1,1,1,1,1},
                                {1,1,1,1,1},
                                {1,1,1,1,1},
                                {1,1,1,1,1}};

    boolean player1 = true;

    MaterialButton[][] arrbuttons = new MaterialButton[5][5];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null){
            for(int i = 0; i<5; i++){
                colorArray[i] = savedInstanceState.getIntArray("color" + i);
            }

            player1 = savedInstanceState.getBoolean("player");
            player = savedInstanceState.getString("display");
        }else{
            player1 = true;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    colorArray[i][j] = 1;
                }
            }
        }
        setContentView(R.layout.activity_main);

        playerWho = findViewById(R.id.playerWho);
        playerWho.setText(player);
        playerWho.setTextColor(Color.BLUE);


        restart = findViewById(R.id.restart);

        int index = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int buttonId = getResources().getIdentifier("btn" + index, "id", getPackageName());
                arrbuttons[i][j] = findViewById(buttonId);
                index++;
            }
        }
        refreshColor();

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i<5; i++){
                    for(int j = 0; j<5; j++){
                        colorArray[i][j] = 1;
                        player1 = true;
                        player = "Player 1";
                        playerWho.setText(player);
                        if(player == "Player 1"){
                            playerWho.setTextColor(Color.BLUE);
                        }else{
                            playerWho.setTextColor(Color.RED);
                        }
                        enableButtons();
                    }
                }
                refreshColor();
            }
        });

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            arrbuttons[0][i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(colorArray[0][finalI] != 1){
                        return;
                    }

                    if(checkAll()){
                        player = "Tie!";
                        playerWho.setText(player);
                        playerWho.setTextColor(Color.BLACK);
                        return;
                    }
                    if (player1) {
                        if (colorArray[4][finalI] == 1) {
                            arrbuttons[4][finalI].setBackgroundColor(Color.BLUE);
                            colorArray[4][finalI] = 2;
                        } else if (colorArray[3][finalI] == 1) {
                            arrbuttons[3][finalI].setBackgroundColor(Color.BLUE);
                            colorArray[3][finalI] = 2;
                        } else if (colorArray[2][finalI] == 1) {
                            arrbuttons[2][finalI].setBackgroundColor(Color.BLUE);
                            colorArray[2][finalI] = 2;
                        } else if (colorArray[1][finalI] == 1) {
                            arrbuttons[1][finalI].setBackgroundColor(Color.BLUE);
                            colorArray[1][finalI] = 2;
                        } else if (colorArray[0][finalI] == 1) {
                            arrbuttons[0][finalI].setBackgroundColor(Color.BLUE);
                            colorArray[0][finalI] = 2;
                        }
                        player1 = false;
                        player = "Player 2";
                        playerWho.setText(player);
                        playerWho.setTextColor(Color.RED);
                    } else {
                        if (colorArray[4][finalI] == 1) {
                            arrbuttons[4][finalI].setBackgroundColor(Color.RED);
                            colorArray[4][finalI] = 3;
                        } else if (colorArray[3][finalI] == 1) {
                            arrbuttons[3][finalI].setBackgroundColor(Color.RED);
                            colorArray[3][finalI] = 3;
                        } else if (colorArray[2][finalI] == 1) {
                            arrbuttons[2][finalI].setBackgroundColor(Color.RED);
                            colorArray[2][finalI] = 3;
                        } else if (colorArray[1][finalI] == 1) {
                            arrbuttons[1][finalI].setBackgroundColor(Color.RED);
                            colorArray[1][finalI] = 3;
                        } else if (colorArray[0][finalI] == 1) {
                            arrbuttons[0][finalI].setBackgroundColor(Color.RED);
                            colorArray[0][finalI] = 3;
                        }
                        player1 = true;
                        player = "Player 1";
                        playerWho.setText(player);
                        playerWho.setTextColor(Color.BLUE);
                    }
                    refreshColor();
                    if (checkWin() == 1) {
                        player = "Player 1 wins!";
                        playerWho.setText(player);
                        playerWho.setTextColor(Color.BLUE);
                        disableButtons();
                    } else if(checkWin() == 2){
                        player = "Player 2 wins!";
                        playerWho.setText(player);
                        playerWho.setTextColor(Color.RED);
                        disableButtons();
                    }
                }
            });
        }
    }

    @Override
    public void onClick(View view) {

    }

    public int checkWin() {
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 5; j++) {

                try {
                    if (colorArray[i][j]==(2) && colorArray[i][j + 1]==(2) && colorArray[i][j + 2]==(2)) {
                        return 1;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }

                try {
                    if (colorArray[i][j]==(3) && colorArray[i][j + 1]==(3) && colorArray[i][j + 2]==(3)) {
                        return 2;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }

                try {
                    if (colorArray[i][j]==(2) && colorArray[i + 1][j]==(2) && colorArray[i + 2][j]==(2)) {
                        return 1;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }

                try {
                    if (colorArray[i][j]==(3) && colorArray[i + 1][j]==(3) && colorArray[i + 2][j]==(3)) {
                        return 2;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }

                try {
                    if (colorArray[i][j]==(2) && colorArray[i - 1][j + 1]==(2) && colorArray[i - 2][j + 2]==(2)) {
                        return 1;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }

                try {
                    if (colorArray[i][j]==(3) && colorArray[i - 1][j + 1]==(3) && colorArray[i - 2][j + 2]==(3)) {
                        return 2;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }

                try {
                    if (colorArray[i][j]==(2) && colorArray[i - 1][j - 1]==(2) && colorArray[i - 2][j - 2]==(2)) {
                        return 1;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }

                try {
                    if (colorArray[i][j]==(3) && colorArray[i - 1][j - 1]==(3) && colorArray[i - 2][j - 2]==(3)) {
                        return 2;
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }

            }
        }
        return 0;
    }

    public void disableButtons(){
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                arrbuttons[i][j].setEnabled(false);
            }
        }
    }

    public void enableButtons(){
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                arrbuttons[i][j].setEnabled(true);
            }
        }
    }

    public boolean checkAll(){
        int ctr = 0;
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                if(colorArray[i][j]== 2  || colorArray[i][j] == (3)){
                    ctr++;
                }
            }
        }

        return ctr == 24 ? true : false;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        for(int i = 0; i<5; i++){
            outState.putIntArray("color" + i, colorArray[i]);
        }

        outState.putBoolean("player", player1);
        outState.putString("display", player);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for(int i = 0; i<5; i++){
            colorArray[i] = savedInstanceState.getIntArray("color" + i);
        }

        player1 = savedInstanceState.getBoolean("player");
        player = savedInstanceState.getString("display");
    }

    protected void refreshColor(){
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                switch(colorArray[i][j]){
                    case 1:
                        arrbuttons[i][j].setBackgroundColor(Color.WHITE);
                        break;
                    case 2:
                        arrbuttons[i][j].setBackgroundColor(Color.BLUE);
                        break;
                    case 3:
                        arrbuttons[i][j].setBackgroundColor(Color.RED);
                        break;
                }
            }
        }
    }
}
