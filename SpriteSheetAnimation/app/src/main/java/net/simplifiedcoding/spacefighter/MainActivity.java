package net.simplifiedcoding.spacefighter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;




public class MainActivity extends Activity implements View.OnClickListener {


    //nút ảnh
    private ImageButton buttonPlay;

    //nút điểm cao
    private ImageButton buttonScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // thiết lập hướng sang ngang
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);



        // lấy nút
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);

        // khởi tạo nút điểm cao
        buttonScore = (ImageButton) findViewById(R.id.buttonScore);

        // đặt nút lắng nghe sự kiện khi nhấp vào nút điểm cao
        buttonScore.setOnClickListener(this);
        //đặt nút lắng nghe sự kiện khi click vào nút play
        buttonPlay.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v==buttonPlay){

            // quá trình chuyển đổi từ MainActivity sang GameActivity
            startActivity(new Intent(MainActivity.this, GameActivity.class));
        }
        if(v==buttonScore){
            // chuyển đổi từ hoạt động MainActivity sang HighScore
            startActivity(new Intent(MainActivity.this,HighScore.class));
        }


    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        GameView.stopMusic();
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}