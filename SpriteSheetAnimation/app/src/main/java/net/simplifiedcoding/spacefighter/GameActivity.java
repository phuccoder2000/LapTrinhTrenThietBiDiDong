package net.simplifiedcoding.spacefighter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;

public class GameActivity extends AppCompatActivity {


    // khai báo gameview
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Lấy đối tượng hiển thị
        Display display = getWindowManager().getDefaultDisplay();

        // Lấy độ phân giải màn hình vào đối tượng điểm
        Point size = new Point();
        display.getSize(size);

        // Khởi tạo đối tượng xem trò chơi
        // lần này chúng ta cũng đang chuyển kích thước màn hình tới hàm tạo GameView
        gameView = new GameView(this, size.x, size.y);

        // thêm nó vào contentview
        setContentView(gameView);
    }

    // tạm dừng trò chơi khi tạm dừng hoạt động
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }
    // chạy trò chơi khi hoạt động được tiếp tục
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
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
