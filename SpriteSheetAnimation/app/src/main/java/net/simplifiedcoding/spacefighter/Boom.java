package net.simplifiedcoding.spacefighter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by Sỹ Hùng on 06/12/2020.
 */
public class Boom {

    // đối tượng bitmap
    private Bitmap bitmap;

    // các biến tọa độ
    private int x;
    private int y;

    //Hàm
    public Boom(Context context) {
        // lấy hình ảnh bùng nổ từ tài nguyên có thể vẽ
        bitmap = BitmapFactory.decodeResource
                (context.getResources(), R.drawable.boom);

        // thiết lập tọa độ bên ngoài màn hình
        // để nó không hiển thị trên màn hình
        // nó sẽ chỉ hiển thị trong một phần giây
        // sau khi collission
        x = -150;
        y = -150;
    }

    // bộ thiết lập cho x và y để hiển thị nó ở vị trí va chạm
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    //getters
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
