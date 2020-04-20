package com.example.workout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Mode_activity extends AppCompatActivity {
    CardView daily,random,about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_activity);
        daily=findViewById(R.id.btn_daily);
        random=findViewById(R.id.btn_random_mode);
        about=findViewById(R.id.btn_about);
        random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mode_activity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mode_activity.this,Daily.class);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mode_activity.this,AboutCreator.class);
                startActivity(intent);
            }
        });
    }
}
