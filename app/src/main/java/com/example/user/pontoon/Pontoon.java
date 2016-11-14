package com.example.user.pontoon;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 12/11/2016.
 */

public class Pontoon extends AppCompatActivity {

    Button mDealButton;
    TextView mAppCards;
    TextView mUserCards;
    TextView mOutcome;
    Button mNewHandButton;
    Button mQuitButton;
    Button mStickButton;
    Button mTwistButton;

    PontoonGame mPontoonGame = new PontoonGame();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d("Pontoon", "Pontoon onCreate called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pontoon);

        mDealButton = (Button) findViewById(R.id.deal_button);
        mAppCards = (TextView) findViewById(R.id.app_cards);
        mUserCards = (TextView) findViewById(R.id.user_cards);
        mOutcome = (TextView) findViewById(R.id.outcome_text);
        mNewHandButton = (Button) findViewById(R.id.new_hand_button);
        mQuitButton = (Button) findViewById(R.id.quit_button);
        mStickButton = (Button) findViewById(R.id.stick_button);
        mTwistButton = (Button) findViewById(R.id.twist_button);

        mNewHandButton.setVisibility(View.GONE);
        mQuitButton.setVisibility(View.GONE);
        mStickButton.setVisibility(View.GONE);
        mTwistButton.setVisibility(View.GONE);

        mDealButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Deal button clicked");

                // removes button on click
                //ToDo: ?difference between this and .setVisibility (as below)?
                ViewGroup parentView = (ViewGroup) view.getParent();
                parentView.removeView(view);

                //ToDo: ?is setUpNewDeck in the right place? Differs if starting game after a Pontoon...
                mPontoonGame.setUpNewDeck();
                mPontoonGame.deal(2);

                mAppCards.setText(mPontoonGame.showAppHandText());
                mUserCards.setText(mPontoonGame.showUserHandText());

                String outcomeInitialDeal = mPontoonGame.checkInitialDeal();
                if (outcomeInitialDeal != null) {

                    mOutcome.setText(outcomeInitialDeal);
                    mNewHandButton.setVisibility(View.VISIBLE);
                    mQuitButton.setVisibility(View.VISIBLE);
                }
                // if outcome is null, move on to user hand

                String outcomeUserTurn = mPontoonGame.checkUserHand();
                if (outcomeUserTurn != null ) {

                    mOutcome.setText(outcomeUserTurn);

                    if ( mPontoonGame.userHasToTwist() ) {

                        mStickButton.setVisibility(View.VISIBLE);
                        mTwistButton.setVisibility(View.VISIBLE);
                        mStickButton.setEnabled(false);
                    }
                    if ( mPontoonGame.userCanStickOrTwist() ) {

                        mStickButton.setVisibility(View.VISIBLE);
                        mTwistButton.setVisibility(View.VISIBLE);
                    }
                    if ( mPontoonGame.checkIfUserBust() ) {

                        mNewHandButton.setVisibility(View.VISIBLE);
                        mQuitButton.setVisibility(View.VISIBLE);
                    }
                }
                // if outcome is null or user Five Card Trick, move on to app hand

            }
        });

        mNewHandButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "New Hand button clicked");

                //ToDo: ?Won't work on some earlier APIs?
                recreate();
            }
        });

        mQuitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Quit button clicked");

                Intent intent = new Intent(Pontoon.this, Main.class);
                startActivity(intent);
            }
        });

        mStickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Stick button clicked");

                //ToDo: stick button action
            }
        });

        mTwistButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Twist button clicked");

                //ToDo: twist button action -- different if user or app??
                
            }
        });
    }
}