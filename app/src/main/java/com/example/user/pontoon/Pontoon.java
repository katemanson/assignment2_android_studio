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
                //ToDo: ?difference between this and .setVisibility?
                ViewGroup parentView = (ViewGroup) view.getParent();
                parentView.removeView(view);

                //ToDo: ?is setUpNewDeck in the right place? Differs if starting game after a Pontoon...
                mPontoonGame.setUpNewDeck();
                mPontoonGame.deal(2);

                mAppCards.setText(mPontoonGame.hideAppCards());
                mUserCards.setText(mPontoonGame.showUserCards());

                if (mPontoonGame.checkInitialDeal() != null) {

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mAppCards.setText(mPontoonGame.showAppCards());
                            mOutcome.setText(mPontoonGame.checkInitialDeal());
                        }
                    }, 500);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mNewHandButton.setVisibility(View.VISIBLE);
                            mQuitButton.setVisibility(View.VISIBLE);
                        }
                    }, 1000);
                }
                else if (mPontoonGame.checkInitialDeal() == null) {
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            userPlay();
                        }
                    }, 1000);
                }
            }
        });

        mNewHandButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "New Hand button clicked");

                mNewHandButton.setVisibility(View.GONE);
                mQuitButton.setVisibility(View.GONE);
                //ToDo: ?Won't work on some earlier APIs?
                recreate();
            }
        });

        mQuitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Quit button clicked");

                mNewHandButton.setVisibility(View.GONE);
                mQuitButton.setVisibility(View.GONE);
                Intent intent = new Intent(Pontoon.this, Main.class);
                startActivity(intent);
            }
        });

        mStickButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Stick button clicked");

                mStickButton.setVisibility(View.GONE);
                mTwistButton.setVisibility(View.GONE);
                mAppCards.setText(mPontoonGame.showAppCards());
                mUserCards.setText(mPontoonGame.hiddenUserHandText());

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        appPlay();
                    }
                }, 1000);
            }
        });

        mTwistButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Log.d("Pontoon", "Twist button clicked");

                mStickButton.setVisibility(View.GONE);
                mTwistButton.setVisibility(View.GONE);
                mPontoonGame.userTwist();
                mUserCards.setText(mPontoonGame.showUserCards());

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        userPlay();
                    }
                }, 1000);
            }
        });
    }

    public void userPlay() {

        String outcomeUserTurn = mPontoonGame.checkUserHand();
        if ( mPontoonGame.checkUserHand() != null ) {

            mOutcome.setText(outcomeUserTurn);

            if ( mPontoonGame.userHasToTwist() ) {

                mStickButton.setVisibility(View.VISIBLE);
                mTwistButton.setVisibility(View.VISIBLE);
                mStickButton.setEnabled(false);
            }
            if ( mPontoonGame.userCanStickOrTwist() ) {

                mStickButton.setVisibility(View.VISIBLE);
                mTwistButton.setVisibility(View.VISIBLE);
                mStickButton.setEnabled(true);
            }
            if ( mPontoonGame.checkIfUserBust() ) {

                mAppCards.setText(mPontoonGame.showAppCards());
                mNewHandButton.setVisibility(View.VISIBLE);
                mQuitButton.setVisibility(View.VISIBLE);
            }
            if ( mPontoonGame.checkForUserFCT() ) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mUserCards.setText(mPontoonGame.hiddenUserHandText());
                        appPlay(); }
                }, 3000);
            }
        }
    }

    public void appPlay() {

        String outcomeAppTurn = mPontoonGame.checkAppHand();
        if ( mPontoonGame.checkAppHand() != null ) {

            mOutcome.setText(outcomeAppTurn);

            if ( mPontoonGame.appHasToTwist() ) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPontoonGame.appTwist();
                        mAppCards.setText(mPontoonGame.showAppCards());
                    }
                }, 2000);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        appPlay();
                    }
                }, 2000);
            }
            if ( mPontoonGame.checkIfAppBust() ) {

                //ToDo: Not sure this works?
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mNewHandButton.setVisibility(View.VISIBLE);
                        mQuitButton.setVisibility(View.VISIBLE);
                        return;
                    }
                }, 2000);

            }
            if ( mPontoonGame.checkForAppFCT() ) {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        endGame();
                    }
                }, 3000);
            }
            if ( mPontoonGame.appStrategyTwist() ) {

                //ToDo: ?not working -- want to delay on dealer twist, to show text
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPontoonGame.appTwist();
                        mAppCards.setText(mPontoonGame.showAppCards());
                    }
                }, 2000);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        appPlay();
                    }
                }, 2000);
            }
            if ( mPontoonGame.appStrategyStick() ) {

                //ToDo: ?not working -- want to delay on dealer stick, to show text
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        endGame();
                    }
                }, 3000);
            }
        }
    }

    public void endGame() {

        mUserCards.setText(mPontoonGame.showUserCards());

        String outcomeCompareHands = mPontoonGame.compareHands();
        mOutcome.setText(outcomeCompareHands);

        mNewHandButton.setVisibility(View.VISIBLE);
        mQuitButton.setVisibility(View.VISIBLE);
    }

}