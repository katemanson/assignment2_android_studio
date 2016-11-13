package com.example.user.pontoon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by user on 12/11/2016.
 */

public class Main extends AppCompatActivity {

    Button mRulesButton;
    Button mPlayButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("Pontoon", "Main onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRulesButton = (Button)findViewById(R.id.rules_button);
        mPlayButton = (Button)findViewById(R.id.play_button);

        mRulesButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Rules button clicked");
                Intent intentRules = new Intent(Main.this, PontoonRules.class);
                startActivity(intentRules);
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Play button clicked");
                Intent intentPlay = new Intent(Main.this, Pontoon.class);
                startActivity(intentPlay);
            }
        });
    }

}
