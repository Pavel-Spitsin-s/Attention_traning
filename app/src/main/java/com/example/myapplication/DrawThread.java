package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.SurfaceHolder;

import java.util.Random;

public class DrawThread extends Thread{
    private SurfaceHolder surfaceHolder;
    private Bitmap bitmap;
    private static final int capacity = 5;

    private volatile boolean running = true;//флаг для остановки потока


    private Paint backgroundPaint = new Paint();
    {
        backgroundPaint.setColor(Color.BLUE);
        backgroundPaint.setStyle(Paint.Style.FILL);
    }


    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.br);
        this.surfaceHolder = surfaceHolder;
    }

    public void requestStop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), backgroundPaint);
                    int size = Math.min(canvas.getWidth() / capacity, canvas.getHeight() / capacity);
                    Random random = new Random();
                    int x = size * (random.nextInt() % (canvas.getWidth() / size));
                    int y = size * (random.nextInt() % (canvas.getHeight() / size));
                    canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                            new Rect(x, y, x + size, y + size), backgroundPaint);
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);

                }
            }
        }
    }
}
