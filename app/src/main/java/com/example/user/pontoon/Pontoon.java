package com.example.user.pontoon;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by user on 12/11/2016.
 */

public class Pontoon extends AppCompatActivity {

    Button mDealButton;
    TextView mAppCards;
    TextView mUserCards;
    TextView mPrompts;
    Button mTwistButton;
    Button mStickButton;
    Button mPlayAgainButton;
    Button mQuitButton;

    PontoonGame mPontoonGame = new PontoonGame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("Pontoon", "Pontoon onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontoon);

        mDealButton = (Button)findViewById(R.id.deal_button);
        mAppCards = (TextView)findViewById(R.id.app_cards);
        mUserCards = (TextView)findViewById(R.id.user_cards);
        mPrompts = (TextView)findViewById(R.id.prompts);
        mTwistButton = (Button)findViewById(R.id.twist_button);
        mStickButton = (Button)findViewById(R.id.stick_button);
        mPlayAgainButton = (Button)findViewById(R.id.play_again_button);
        mQuitButton = (Button)findViewById(R.id.quit_button);

        mDealButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Deal button clicked");

            }
        });

        mTwistButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Twist button clicked");

            }
        });

        mStickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Stick button clicked");

            }
        });

        mPlayAgainButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Play again button clicked");

            }
        });

        mQuitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Quit button clicked");

            }
        });
    }

}
