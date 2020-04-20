package com.example.workout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public LinearLayout ll;
    public CardView btn_start, btn_random;
    public TextView txt_random,card_set;
    public List<Object> change_arr = new ArrayList<>();
    public ArrayList<Integer> time_arr = new ArrayList<Integer>();
    ArrayList<String> wo_names = new ArrayList<>();
    int sets_value;
    int[] covers = new int[]{
            R.drawable.pushup,
            R.drawable.back,
            R.drawable.pullup,
            R.drawable.dumbal_pushup,
            R.drawable.strech,
            R.drawable.yoga
    };
    private Card_adapter adapter;
    private List<card_data> cardDataList;
    EditText sets;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Dialog dialog = new Dialog(this);

        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.activity_sets);
        sets = dialog.findViewById(R.id.sets_txt);
        dialog.show();
        dialog.findViewById(R.id.set_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sets.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please! Enter the Number of Sets", Toast.LENGTH_SHORT).show();
                    dialog.show();
                } else {
                    sets_value = Integer.parseInt(String.valueOf(sets.getText()));
                    prepareData(sets_value);
                    ll.setVisibility(View.VISIBLE);
                    //System.out.println(str);
                    dialog.dismiss();
                }
            }
        });




        setContentView(R.layout.activity_main);
        ll=findViewById(R.id.btn_layout);
        ll.setVisibility(View.INVISIBLE);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        card_set=findViewById(R.id.card_set);
        btn_start = findViewById(R.id.btn_start);
        btn_random = findViewById(R.id.btn_random);
        txt_random = findViewById(R.id.txt_rand);
        cardDataList = new ArrayList<>();
        adapter = new Card_adapter(this, cardDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);





        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, Excerise.class);
                intent.putIntegerArrayListExtra("time_list", time_arr);
                intent.putStringArrayListExtra("name_arr", wo_names);
                startActivity(intent);
            }
        });

        btn_random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeData();
                prepareData(sets_value);
            }
        });
    }



    private void prepareData(int set) {


        card_data cd = new card_data("Push-Ups", 1, covers[0],set);
        card_data cd1 = new card_data("Stretch", 1, covers[4],set);
        card_data cd2 = new card_data("Yoga", 1, covers[5],set);
        card_data cd3 = new card_data("Back Exercise", 1, covers[1],set);
        card_data cd4 = new card_data("Pull-Ups", 1, covers[2],set);
        card_data cd5 = new card_data("Dumbal-PushUps", 1, covers[3],set);

        List<Object> arr = new ArrayList<Object>();
        arr.add(cd);
        arr.add(cd1);
        arr.add(cd2);
        arr.add(cd3);
        arr.add(cd4);
        arr.add(cd5);
        Random r = new Random();

        for (int i = 0; i < 5; i++) {
            Object ob;
            ob = arr.get(r.nextInt(arr.size()));
            cardDataList.add((card_data) ob);
            change_arr.add(ob);
            int time = ((card_data) ob).getTime();
            String name = ((card_data) ob).getName();
            wo_names.add(name);
            time_arr.add(time);
            arr.remove(ob);

        }
        adapter.notifyDataSetChanged();
    }

    public void removeData() {
        for (int i = 0; i < 5; i++) {
            cardDataList.remove(cardDataList.get(0));
        }
        adapter.notifyDataSetChanged();
        wo_names.clear();
        time_arr.clear();
    }


}



