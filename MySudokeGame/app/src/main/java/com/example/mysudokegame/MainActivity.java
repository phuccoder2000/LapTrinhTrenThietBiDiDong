package com.example.mysudokegame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_main);
        View newButton = findViewById(R.id.New_button);
        newButton.setOnClickListener(this);
        View continue_Button = findViewById(R.id.Continue_button);
        continue_Button.setOnClickListener(this);
        View aboutButton = findViewById(R.id.About_button);
        aboutButton.setOnClickListener(this);
        View exit_Button = findViewById(R.id.Exit_button);
        exit_Button.setOnClickListener(this);
    }

    public  void onClick(View v){
        switch (v.getId()){
            case R.id.About_button:
                Intent i = new Intent(this, About.class);
                startActivity(i);
                break;
            case R.id.New_button:
                openNewGameDialog();
                break;
            case R.id.Exit_button:
                finish();
                break;
        }
    }
    private void startGame(int i){
        //start game here
        Intent intent = new Intent(MainActivity.this,Game.class);
        intent.putExtra(Game.KEY_DIFFICULTY,i);
        startActivity(intent);
    }

    public void openNewGameDialog(){
        new AlertDialog.Builder(this).setTitle(R.string.new_game_title).setItems(R.array.difficulty, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        startGame(i);
                    }
        }).show();
    }
}