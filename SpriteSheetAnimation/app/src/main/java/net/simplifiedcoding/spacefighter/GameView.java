package net.simplifiedcoding.spacefighter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable {

    volatile boolean playing;
    private Thread gameThread = null;
    private Player player;

    //a screenX holder : giữ holder
    int screenX;

    // ngữ cảnh được sử dụng trong onTouchEvent để chuyển đổi hoạt động from GameAvtivity to MainActivity.
    Context context;

    // người ghi điểm
    int score;

    //lịch sử điểm cao
    int highScore[] = new int[4];


    // Chia sẻ để lưu trữ Điểm cao
    SharedPreferences sharedPreferences;


    //to count the number of Misses
    int countMisses;

    // chỉ báo rằng kẻ thù vừa vào màn hình trò chơi
    boolean flag ;
    // chỉ báo nếu trò chơi kết thúc
    private boolean isGameOver ;

    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    private Enemy enemies;

    //tạo một tham chiếu của lớp Friend
    private Friend friend;


    private ArrayList<Star> stars = new
            ArrayList<Star>();

    // xác định đối tượng boom để hiển thị blast
    private Boom boom;


    // các đối tượng của trình trung gian để định cấu hình nhạc nền
    // trình phát trung gian objects to configure the background music
    static  MediaPlayer gameOnsound;

    final MediaPlayer killedEnemysound;

    final MediaPlayer gameOversound;



    public GameView(Context context, int screenX, int screenY) {
        super(context);
        player = new Player(context, screenX, screenY);


        surfaceHolder = getHolder();
        paint = new Paint();

        // khởi tạo ngữ cảnh
        this.context = context;

        int starNums = 100;
        for (int i = 0; i < starNums; i++) {
            Star s = new Star(screenX, screenY);
            stars.add(s);
        }




        enemies = new Enemy(context,screenX,screenY);

        // khởi tạo đối tượng boom
        boom = new Boom(context);
        // khởi tạo đối tượng lớp Friend
        friend = new Friend(context, screenX, screenY);
        // ban đầu đặt điểm thành 0
        score = 0;

        // cài đặt countMisses thành 0 ban đầu
        countMisses = 0;


        this.screenX = screenX;


        isGameOver = false;

        // khởi tạo Preferences được chia sẻ
        sharedPreferences = context.getSharedPreferences("SHAR_PREF_NAME",Context.MODE_PRIVATE);


        // khởi tạo điểm số cao của mảng với các giá trị trước đó
       highScore[0] = sharedPreferences.getInt("score1",0);
       highScore[1] = sharedPreferences.getInt("score2",0);
       highScore[2] = sharedPreferences.getInt("score3",0);
       highScore[3] = sharedPreferences.getInt("score4",0);

       // khởi tạo trình phát đa phương tiện cho âm thanh trò chơi
       gameOnsound = MediaPlayer.create(context,R.raw.gameon);
        killedEnemysound = MediaPlayer.create(context,R.raw.killedenemy);
        gameOversound = MediaPlayer.create(context,R.raw.gameover);

        // khởi tạo đa phương tiện phát cho trò chơi âm thanh
        gameOnsound.start();

    }





    @Override
    public void run() {

        while (playing) {

            update();
            draw();
            control();

        }


    }


    private void update() {

        //incrementing score as time passes
        score++;

        player.update();

        //setting boom outside the screen
        boom.setX(-250);
        boom.setY(-250);

        for (Star s : stars) {

            s.update(player.getSpeed());
        }

        //setting the flag true when the enemy just enters the screen
        if(enemies.getX()==screenX){

            flag = true;
        }


        enemies.update(player.getSpeed());
                //if collision occurs with player
                if (Rect.intersects(player.getDetectCollision(), enemies.getDetectCollision())) {

                    //displaying boom at that location
                    boom.setX(enemies.getX());
                    boom.setY(enemies.getY());


                    //playing a sound at the collision between player and the enemy
                    killedEnemysound.start();

                    enemies.setX(-200);
                }

                else{// the condition where player misses the enemy

                    //if the enemy has just entered
                    if(flag){

                        //if player's x coordinate is equal to enemies's y coordinate
                        if(player.getDetectCollision().exactCenterX()>=enemies.getDetectCollision().exactCenterX()){

                            //increment countMisses
                            countMisses++;

                            //setting the flag false so that the else part is executed only when new enemy enters the screen
                            flag = false;

                            //if no of Misses is equal to 3, then game is over.
                            if(countMisses==3){

                                //setting playing false to stop the game.
                                playing = false;
                                isGameOver = true;


                                //stopping the gameon music
                                gameOnsound.stop();
                                //play the game over sound
                                gameOversound.start();

                                //Assigning the scores to the highscore integer array
                                for(int i=0;i<4;i++){
                                    if(highScore[i]<score){

                                        final int finalI = i;
                                        highScore[i] = score;
                                        break;
                                    }
                                }

                                //storing the scores through shared Preferences
                                SharedPreferences.Editor e = sharedPreferences.edit();

                                for(int i=0;i<4;i++){

                                    int j = i+1;
                                    e.putInt("score"+j,highScore[i]);
                                }
                                e.apply();

                            }

                        }
                        }

                }



        //updating the friend ships coordinates
        friend.update(player.getSpeed());
                //checking for a collision between player and a friend
                if(Rect.intersects(player.getDetectCollision(),friend.getDetectCollision())){

                    //displaying the boom at the collision
                    boom.setX(friend.getX());
                    boom.setY(friend.getY());
                    //setting playing false to stop the game
                    playing = false;
                    //setting the isGameOver true as the game is over
                    isGameOver = true;



                    //stopping the gameon music
                    gameOnsound.stop();
                    //play the game over sound
                    gameOversound.start();

                //Assigning the scores to the highscore integer array
                    for(int i=0;i<4;i++){

                        if(highScore[i]<score){

                            final int finalI = i;
                            highScore[i] = score;
                            break;
                        }


                    }
                    //storing the scores through shared Preferences
                    SharedPreferences.Editor e = sharedPreferences.edit();

                    for(int i=0;i<4;i++){

                        int j = i+1;
                        e.putInt("score"+j,highScore[i]);
                    }
                    e.apply();

                }

    }


    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);



            paint.setColor(Color.WHITE);
            paint.setTextSize(20);

            for (Star s : stars) {
                paint.setStrokeWidth(s.getStarWidth());
                canvas.drawPoint(s.getX(), s.getY(), paint);
            }

            canvas.drawBitmap(
                    player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);


                canvas.drawBitmap(
                        enemies.getBitmap(),
                        enemies.getX(),
                        enemies.getY(),
                        paint

                );

            //drawing the score on the game screen
            paint.setTextSize(30);
            canvas.drawText("Score:"+score,100,50,paint);


            //drawing boom image
            canvas.drawBitmap(
                    boom.getBitmap(),
                    boom.getX(),
                    boom.getY(),
                    paint
            );






            //drawing friends image
            canvas.drawBitmap(

                    friend.getBitmap(),
                    friend.getX(),
                    friend.getY(),
                    paint
            );


            //draw game Over when the game is over
            if(isGameOver){
                paint.setTextSize(150);
                paint.setTextAlign(Paint.Align.CENTER);

                int yPos=(int) ((canvas.getHeight() / 2) - ((paint.descent() + paint.ascent()) / 2));
                canvas.drawText("Game Over",canvas.getWidth()/2,yPos,paint);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);

        }
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
        }
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    //stop the music on exit
    public static void stopMusic(){

        gameOnsound.stop();
    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {


        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                player.stopBoosting();
                break;
            case MotionEvent.ACTION_DOWN:
                player.setBoosting();
                break;

        }
//if the game's over, tappin on game Over screen sends you to MainActivity
        if(isGameOver){

            if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){

                context.startActivity(new Intent(context,MainActivity.class));

            }

        }

        return true;

    }




}

