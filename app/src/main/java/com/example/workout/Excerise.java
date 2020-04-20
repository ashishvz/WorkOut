package com.example.workout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ankushgrover.hourglass.Hourglass;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Excerise extends AppCompatActivity {
    MediaPlayer mp = null;
    public ArrayList<Integer> time_arr;
    int flag = 0;
    public ArrayList<String> names_arr;
    TextView timer_txt, name_txt;
    ImageView img_ex,img1,img2,img3,img4,img5;
    int i = 0;
    CardView card1,card2,card3,card4,card5;
    Hourglass ex_timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_count_down);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final TextView count_txt = dialog.findViewById(R.id.countdown_txt);


        Hourglass count_welcome= new Hourglass(3000, 1000) {
            @Override
            public void onTimerTick(long timeRemaining) {
                dialog.show();
                count_txt.setText(String.valueOf(timeRemaining / 1000));
            }

            @Override
            public void onTimerFinish() {
                dialog.dismiss();
                ex_timer.startTimer();
                mp.start();
            }
        };
        count_welcome.startTimer();



        setContentView(R.layout.activity_excerise);
        time_arr = getIntent().getIntegerArrayListExtra("time_list");
        names_arr = getIntent().getStringArrayListExtra("name_arr");
        name_txt = findViewById(R.id.name_txt);
        img_ex = findViewById(R.id.ex_img);
        timer_txt = findViewById(R.id.time_text);
        img1=findViewById(R.id.img1);
        img2=findViewById(R.id.img2);
        img3=findViewById(R.id.img3);
        img4=findViewById(R.id.img4);
        img5=findViewById(R.id.img5);
        img1.setImageResource(getThumbnail(names_arr.get(0)));
        img2.setImageResource(getThumbnail(names_arr.get(1)));
        img3.setImageResource(getThumbnail(names_arr.get(2)));
        img4.setImageResource(getThumbnail(names_arr.get(3)));
        img5.setImageResource(getThumbnail(names_arr.get(4)));
        card1=findViewById(R.id.card1);
        card2=findViewById(R.id.card2);
        card3=findViewById(R.id.card3);
        card4=findViewById(R.id.card4);
        card5=findViewById(R.id.card5);
        mp = MediaPlayer.create(this,get_randomMedia());
        UpdateUI(0);

    }


    public void UpdateUI(int i)
    {
        name_txt.setText(names_arr.get(i));
        img_ex.setImageResource(getThumbnail(names_arr.get(i)));
        timer(i);
    }






    public void timer(final int i) {
        long a = time_arr.get(i) *60000;
        ex_timer = new Hourglass(a,1000) {
            @Override
            public void onTimerTick(long timeRemaining) {
                int time = (int) timeRemaining;
                int seconds = time / 1000 % 60;
                int minutes = (time / (1000 * 60)) % 60;
                timer_txt.setText(twoDigitString(minutes) + " : " + twoDigitString(seconds));
            }

            private String twoDigitString(long number) {
                if (number == 0) {
                    return "00";
                } else if (number / 10 == 0) {
                    return "0" + number;
                }
                return String.valueOf(number);
            }


            @Override
            public void onTimerFinish() {
                switch (i)
                {
                    case 0 : card1.setCardBackgroundColor(Color.GRAY);break;
                    case 1 : card2.setCardBackgroundColor(Color.GRAY);break;
                    case 2 : card3.setCardBackgroundColor(Color.GRAY);break;
                    case 3 : card4.setCardBackgroundColor(Color.GRAY);break;
                    case 4 : card5.setCardBackgroundColor(Color.GRAY);break;
                }
                System.out.println(i);
                mp.pause();
                if(i==names_arr.size()-1) {
                    Toast.makeText(getApplicationContext(),"Finished the Exercises",Toast.LENGTH_LONG).show();
                    finish();
                }
                else {
                    final Dialog break_dialog = new Dialog(Excerise.this);
                    break_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    break_dialog.setContentView(R.layout.activity_count_down);
                    Objects.requireNonNull(break_dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    final TextView count_txt = break_dialog.findViewById(R.id.countdown_txt);


                    Hourglass count_break = new Hourglass(40000, 1000) {
                        @Override
                        public void onTimerTick(long timeRemaining) {
                            break_dialog.show();
                            count_txt.setText(String.valueOf(timeRemaining / 1000));
                        }

                        @Override
                        public void onTimerFinish() {
                            break_dialog.dismiss();
                            mp=MediaPlayer.create(Excerise.this,get_randomMedia());
                            UpdateUI(i + 1);
                            ex_timer.startTimer();
                            mp.start();

                        }
                    };
                    count_break.startTimer();
                }
            }
        };
    }







    @Override
    public void onBackPressed() {
        if (mp != null) {
            mp.stop();
            super.onBackPressed();
            time_arr.clear();
            names_arr.clear();
        }
    }

    public int getThumbnail(String name) {
        switch (name) {
            case "Push-Ups":
                return R.drawable.pushup;
            case "Stretch":
                return R.drawable.strech;
            case "Yoga":
                return R.drawable.yoga;
            case "Back Exercise":
                return R.drawable.back;
            case "Pull-Ups":
                return R.drawable.pullup;
            case "Dumbal-PushUps":
                return R.drawable.dumbal_pushup;
            default:
                return 0;
        }
    }

    public int get_randomMedia()
    {
        int[] music_arr = new int[]
                {R.raw.music1,
                R.raw.music2,
                R.raw.music3,
                R.raw.music4};

        Random random_music = new Random();
        return music_arr[random_music.nextInt(music_arr.length)];
    }
}
