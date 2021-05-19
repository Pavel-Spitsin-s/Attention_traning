package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.DrawThread;
import com.example.myapplication.GameView;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class InputActivity extends Activity {
    private ImageSwitcher mImageSwitcher;

    private int[] mImageIds = {R.drawable.andr, R.drawable.br, R.drawable.red_ball};

    private int mCurIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_layout);
        EditText count_of_bombs = (EditText) findViewById(R.id.count_of_bombs);
        EditText count_of_androids = (EditText) findViewById(R.id.count_of_androids);
        EditText count_of_red_balls = (EditText) findViewById(R.id.count_of_red_balls);
        Button btn = (Button) findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count_of_bombs_int = Integer.parseInt(count_of_bombs.getText().toString());
                int count_of_androids_int = Integer.parseInt(count_of_androids.getText().toString());
                int count_of_red_balls_int = Integer.parseInt(count_of_red_balls.getText().toString());
//                count_of_androids_int = 0;
//                count_of_bombs_int = 0;
//                count_of_red_balls_int = 0;
//                System.out.println(DrawThread.check_androids);
//                System.out.println(DrawThread.check_bombs);
//                System.out.println(DrawThread.check_red_balls);
                if (count_of_androids_int == DrawThread.check_androids &&
                        count_of_bombs_int == DrawThread.check_bombs &&
                        count_of_red_balls_int == DrawThread.check_red_balls) {
                    DrawThread.secs += 5 - DrawThread.secs % 5;
                    DrawThread.check_bombs = 0;
                    DrawThread.check_red_balls = 0;
                    DrawThread.check_androids = 0;
                    Intent intent = new Intent();
                    intent.setClass(InputActivity.this, Game.class);
                    startActivity(intent);
                }
                else {
                    System.out.println(DrawThread.check_bombs + " bombs");
                    System.out.println(DrawThread.check_red_balls + " red_balls");
                    System.out.println(DrawThread.check_androids + " androids");
                    Intent intent = new Intent();
                    intent.setClass(InputActivity.this, GameOver.class);
                    startActivity(intent);
                }
//                DrawThread.check_bombs = 0;
//                DrawThread.check_red_balls = 0;
//                DrawThread.check_androids = 0;
            }
        });

    }
}

