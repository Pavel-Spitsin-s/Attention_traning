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
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count_of_bombs_int = Integer.parseInt(count_of_bombs.getText().toString());
                int count_of_androids_int = Integer.parseInt(count_of_androids.getText().toString());
                int count_of_red_balls_int = Integer.parseInt(count_of_red_balls.getText().toString());
            }
        };
    }
}

