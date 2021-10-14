package net.simplifiedcoding.spacefighter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Sỹ Hùng on 06/12/2020.
 */
public class Enemy {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 1;

    private int maxX;
    private int minX;

    private int maxY;
    private int minY;

    Context context;
    Activity activity;

    // tạo một đối tượng trực tràng
    private Rect detectCollision;

    public Enemy(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        this.context = context;

        activity = (Activity) context;
        Random generator = new Random();
        speed = generator.nextInt(6) + 10;
        x = screenX;
        y = generator.nextInt(maxY) - bitmap.getHeight();

        // khởi tạo đối tượng trực tràng
        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void update(int playerSpeed) {
        x -= playerSpeed;
        x -= speed;
        if (x < minX - bitmap.getWidth()) {
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x = maxX;
            y = generator.nextInt(maxY) - bitmap.getHeight();
        }
        // Thêm trên cùng, trái, dưới và phải vào đối tượng trực tràng
        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }


    // thêm một setter vào tọa độ x để chúng ta có thể thay đổi nó sau khi va chạm
    public void setX(int x){

        this.x = x;

    }

    // một getter nữa để lấy đối tượng trực tràng
    public Rect getDetectCollision() {
        return detectCollision;
    }

    //getters
    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }




    public int getSpeed() {
        return speed;
    }

}
