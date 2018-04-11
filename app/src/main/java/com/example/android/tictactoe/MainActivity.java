package com.example.android.tictactoe;
//a beginner's try at making a tic tac toe app
// I followed a tutorial on youtube to understand the basic principle of the game

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//victor just did something here
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //variable declaration
    private Button[][] buttons = new Button[3][3]; // buttons will be numbered matrix style like i.e first row - 00, 01,02  first column - 00,10,20, etc
    private boolean firstPlayerGoes = true; // the first player always starts
    private int count; // the x's and 0's are written 9 times, this helps us keep count
    private int x, y;//
    private int firstPlayerScore;
    private int secondPlayerScore;

    private TextView textViewFirstPlayer;
    private TextView textViewSecondPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewFirstPlayer = findViewById(R.id.text_view_first);
        textViewSecondPlayer = findViewById(R.id.text_view_second);

//helps us number the buttons without listing them individually, using an array
        for (x = 0; x < 3; x++) {
            for (y = 0; y < 3; y++) {
                String buttonNO = "button_" + x + y;
                int ButtonName = getResources().getIdentifier(buttonNO, "id", getPackageName());
                buttons[x][y] = findViewById(ButtonName);
                buttons[x][y].setOnClickListener(this);
            }
        }

        Button resetButton = findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGAME();
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }
        if (firstPlayerGoes) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        count++;

        if (winningPlayer()) {
            if (firstPlayerGoes) {
                firstPlayerScores();
            } else {
                secondPlayerScores();
            }
        } else if (count == 9) {
            tie();
        } else {
            firstPlayerGoes = !firstPlayerGoes;
        }


    }

    private boolean winningPlayer() {
        String[][] array = new String[3][3];
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                array[x][y] = buttons[x][y].getText().toString();

            }
        }

        for (int x = 0; x < 3; x++) {
            if (array[x][0].equals(array[x][1]) && array[x][0])
                    && array[x][0].equals(array[x][2])
                    && !array[x][0].equals("")) {
                return true;
            }
        }

        for (int x = 0; x < 3; x++) {
            if (array[0][x].equals(array[1][x]) && array[0][x])
                    && array[0][x].equals(array[2][x])
                    && !array[0][1].equals("")) {
                return true;
            }
        }

        if (array[0][0].equals(array[1][1])
                && array[0][0].equals(array[2][2])
                && !array[0][0].equals("")) {
            return true;
        }

        if (array[0][2].equals(array[1][1])
                && array[0][2].equals(array[2][0])
                && !array[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void firstPlayerScores() {
        firstPlayerScore++;
        Toast.makeText(this, "First Player wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }

    private void secondPlayerScores() {
        secondPlayerScore++;
        Toast.makeText(this, "Second Player wins!", Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();
    }

    private void tie() {
        Toast.makeText(this, "Evenly Tied", Toast.LENGTH_SHORT).show();
        resetBoard();

    }

    private void updatePointsText() {
        textViewFirstPlayer.setText("First Player: " + firstPlayerScore);
        textViewSecondPlayer.setText("Second Player: " + secondPlayerScore);
    }

    private void resetBoard() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                buttons[x][y].setText("");
            }
        }
        count = 0;
        firstPlayerGoes = true;
    }

    private void resetGAME() {
        firstPlayerScore = 0;
        secondPlayerScore = 0;
        updatePointsText();
        resetBoard();

    }
}

