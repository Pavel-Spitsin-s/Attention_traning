package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;

import java.util.Date;
import java.util.Random;

public class DrawThread extends Thread{
    interface OnPostExecute{
    void doOnPost();
}
    private SurfaceHolder surfaceHolder;
    private Bitmap bitmap;
    private Bitmap bitmap_rb;
    private Bitmap bitmap_andr;
    private static final int capacity = 5;
    private OnPostExecute onPostExecute = null;
    public static int check_bombs;
    public static int check_androids;
    public static int check_red_balls;
    public static int secs = 3;
    private volatile boolean running = true;//флаг для остановки потока
    public static Context context;
    private Paint backgroundPaint = new Paint();
    {
        backgroundPaint.setColor(Color.BLUE);
        backgroundPaint.setStyle(Paint.Style.FILL);
    }
    private Paint TimerPaint = new Paint();
    {
        TimerPaint.setColor(Color.BLACK);
        TimerPaint.setStyle(Paint.Style.FILL);
        TimerPaint.setTextSize(250);
    }




    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        DrawThread.context = context;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.br);
        bitmap_andr = BitmapFactory.decodeResource(context.getResources(), R.drawable.andr);
        bitmap_rb = BitmapFactory.decodeResource(context.getResources(), R.drawable.red_ball);
        this.surfaceHolder = surfaceHolder;
    }

    public void requestStop() {
        running = false;
    }
    int k = 0;
    int pk = -1;
    int x, y;
    @Override
    public void run() {
        while (running) {
            if (k == 0){
                TimeCounter.run(String.valueOf(secs));
            }
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    k = Integer.parseInt(TimeCounter.secs_to_output);
                    if (TimeCounter.secs_to_output.equals("0")){
                        running = false;
                    }
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
                    canvas.drawText(TimeCounter.secs_to_output, (float)canvas.getWidth() / (float)2 - 150, 200, TimerPaint);

                    int size = Math.min(canvas.getWidth() / capacity, canvas.getHeight() / capacity);
                    Random random = new Random();
                    if (k != pk){
//                        if (k <= 50) check_bombs += 1;
//                        else if (k <= 100) check_androids += 1;
//                        else{
//                            check_red_balls += 1;
//                        }
                        if (k % 3 == 0){
                            DrawThread.check_bombs += 1;
                        }
                        else if (k % 3 == 2){
                            DrawThread.check_androids += 1;
                        }
                        else{
                            DrawThread.check_red_balls += 1;
                        }
                        x = size * random.nextInt(canvas.getWidth() / size);
                        y = size * random.nextInt(canvas.getHeight() / size);
                    }
                    System.out.println("x = " + x + " " + y);
                    if (k % 3 == 0){
                        bitmap = BitmapFactory.decodeResource(DrawThread.context.getResources(), R.drawable.br);
                        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                                new Rect(x, y, x + size, y + size), backgroundPaint);


                    }
                    else if (k % 3 == 2){

                        bitmap = bitmap_andr;
                        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                                new Rect(x, y, x + size, y + size), backgroundPaint);
                    }
                    else{

                        bitmap = bitmap_rb;
                        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                                new Rect(x, y, x + size, y + size), backgroundPaint);
                    }
                    pk = k;

                    System.out.println(check_bombs + " " + check_androids + " "  +check_red_balls);
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);

                }
            }
        }
        handler.sendMessage(handler.obtainMessage());
    }

    public void setOnPostExecuteAction(OnPostExecute onPostExecute){
        this.onPostExecute = onPostExecute;
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (onPostExecute != null) onPostExecute.doOnPost();
        }
    };
}
