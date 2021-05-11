package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

public class Game extends AppCompatActivity {
    private ImageSwitcher mImageSwitcher;

    private int[] mImageIds = {R.drawable.andr, R.drawable.br, R.drawable.red_ball};

    private int mCurIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GameView game = new GameView(this, new DrawThread.OnPostExecute() {
            @Override
            public void doOnPost() {
                Intent intent = new Intent();
                intent.setClass(Game.this, MainActivity.class);
                startActivity(intent);
            }
        });
        setContentView(game);
    }
}

