package com.sodohyun.dicegameapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spn_diceNumbers = (Spinner) findViewById(R.id.spn_diceNumbers);

        final TextView tv_CompDice1 = (TextView) findViewById(R.id.tv_CompDice1);
        final TextView tv_CompDice2 = (TextView) findViewById(R.id.tv_CompDice2);
        final TextView tv_CompSum = (TextView) findViewById(R.id.tv_CompSum);
        final TextView tv_CompMod = (TextView) findViewById(R.id.tv_CompMod);
        final TextView tv_CompWinner = (TextView) findViewById(R.id.tv_CompWinner);
        final TextView tv_PlayerDice1 = (TextView) findViewById(R.id.tv_PlayerDice1);
        final TextView tv_PlayerDice2 = (TextView) findViewById(R.id.tv_PlayerDice2);
        final TextView tv_PlayerSum = (TextView) findViewById(R.id.tv_PlayerSum);
        final TextView tv_PlayerMod = (TextView) findViewById(R.id.tv_PlayerMod);
        final TextView tv_PlayerWinner = (TextView) findViewById(R.id.tv_PlayerWinner);

        //set a OnClickListener to the player button
        final Button btn_Roll = (Button) findViewById(R.id.btn_Roll);
        btn_Roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Reset Board

                tv_CompWinner.setText("");
                tv_PlayerWinner.setText("");

                //code here executes on main thread after user presses button
                //Player selects a number & the number display as Player Dice 1
                final String playerSelectedValue = spn_diceNumbers.getSelectedItem().toString();
                tv_PlayerDice1.setText(playerSelectedValue);

                //Player's Dice2 random number generation and dsiplays.
                final int random = new Random().nextInt(6) + 1;
                tv_PlayerDice2.setText(Integer.toString(random));

                //Sum of Player's Dices 1 and 2 and displays
                final int sumPlayerDices = Integer.parseInt(playerSelectedValue) + random;
                tv_PlayerSum.setText(Integer.toString(sumPlayerDices));

                //Computer Dice 1 random number generation and displays
                final int compRandom1 = new Random().nextInt(6) + 1;
                tv_CompDice1.setText(Integer.toString(compRandom1));

                //Computer Dice 2 random number generaiton and displays
                final int compRandom2 = new Random().nextInt(6) + 1;
                tv_CompDice2.setText(Integer.toString(compRandom2));

                //Sum of Computer Dices 1 and 2
                final int sumCompDices = compRandom1 + compRandom2;
                tv_CompSum.setText(Integer.toString(sumCompDices));

                //Calculate Modulo and displays
                final int modCompDices = sumCompDices % 6;
                final int modPlayerDices = sumPlayerDices % 6;

                tv_CompMod.setText(Integer.toString(modCompDices));
                tv_PlayerMod.setText(Integer.toString(modPlayerDices));

            }
        });

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.spn_diceNumbers);
        //create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3", "4", "5", "6"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
    }
}

