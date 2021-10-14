package com.example.flappybird;

import android.app.Activity;
import android.os.Bundle;

public class StartGame extends Activity {
    GameView gameView;
    @Override
    protected  void onCreate(Bundle  savedInstanceState ){
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }
}
