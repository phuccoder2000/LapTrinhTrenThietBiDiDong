package net.simplifiedcoding.spacefighter;

import java.util.Random;

/**
 * Created by Sỹ Hùng on 06/12/2020.
 */
public class Star {
    private int x;
    private int y;
    private int speed;

    private int maxX;
    private int maxY;
    private int minX;
    private int minY;



    public Star(int screenX, int screenY) {
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;
        Random generator = new Random();
        speed = generator.nextInt(10);

        // tạo một tọa độ ngẫu nhiên
        // nhưng vẫn giữ tọa độ bên trong kích thước màn hình
        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY);
    }

    public void update(int playerSpeed) {
        // tạo hoạt ảnh ngôi sao theo chiều ngang bên trái
        // bằng cách giảm tọa độ x với tốc độ của người chơi
        x -= playerSpeed;
        x -= speed;
        // nếu ngôi sao đến mép trái của màn hình
        if (x < 0) {
            // lại bắt đầu ngôi sao từ cạnh phải
            // điều này sẽ tạo ra hiệu ứng nền cuộn vô hạn
            x = maxX;
            Random generator = new Random();
            y = generator.nextInt(maxY);
            speed = generator.nextInt(15);
        }
    }

    public float getStarWidth() {
        // Tạo chiều rộng dấu sao ngẫu nhiên để
        // nó sẽ cho một cái nhìn thực sự
        float minX = 1.0f;
        float maxX = 4.0f;
        Random rand = new Random();
        float finalX = rand.nextFloat() * (maxX - minX) + minX;
        return finalX;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
