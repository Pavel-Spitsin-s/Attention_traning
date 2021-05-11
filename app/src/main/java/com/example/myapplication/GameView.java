package com.example.myapplication;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.logging.Handler;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private DrawThread drawThread;
    private DrawThread.OnPostExecute onPostExecute;

    public GameView(Context context, DrawThread.OnPostExecute onPostExecute) {
        super(context);
        getHolder().addCallback(this);
        this.onPostExecute = onPostExecute;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getContext(),getHolder());
        drawThread.setOnPostExecuteAction(onPostExecute);
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // изменение размеров SurfaceView
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        drawThread.requestStop();
        boolean retry = true;
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
                //
            }
        }
    }
}
