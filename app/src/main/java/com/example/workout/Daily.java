package com.example.workout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Daily extends AppCompatActivity {
    private TextView week_txt,card_set;;
    private RecyclerView recyclerView;
    private Card_adapter adapter;
    public CardView btn_start;
    private List<card_data> cardDataList;
    public List<Object> change_arr = new ArrayList<>();
    public ArrayList<Integer> time_arr = new ArrayList<Integer>();
    ArrayList<String> wo_names = new ArrayList<>();
    public LinearLayout ll;
    EditText sets;
    int sets_value;
    int[] covers = new int[]{
            R.drawable.pushup,
            R.drawable.back,
            R.drawable.pullup,
            R.drawable.dumbal_pushup,
            R.drawable.strech,
            R.drawable.yoga
    };
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
                    dialog.dismiss();
                }
            }
        });

        setContentView(R.layout.activity_daily);
        ll=findViewById(R.id.btn_start_layout);
        ll.setVisibility(View.INVISIBLE);
        btn_start=findViewById(R.id.btn_start_daily);
        card_set=findViewById(R.id.card_set);
        week_txt=findViewById(R.id.week_txt);
        recyclerView=findViewById(R.id.daily_recycler);
        cardDataList = new ArrayList<>();
        adapter = new Card_adapter(this, cardDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        switch (day)
        {
            case Calendar.SUNDAY: week_txt.append(" "+"SUNDAY");break;
            case Calendar.MONDAY:week_txt.append(" "+"MONDAY");break;
            case Calendar.TUESDAY:week_txt.append(" "+"TUESDAY");break;
            case Calendar.WEDNESDAY:week_txt.append(" "+"WEDNESDAY");break;
            case Calendar.THURSDAY:week_txt.append(" "+"THURSDAY");break;
            case Calendar.FRIDAY:week_txt.append(" "+"FRIDAY");break;
            case Calendar.SATURDAY:week_txt.append(" "+"SATURDAY");break;
        }
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Daily.this,Excerise.class);
                intent.putIntegerArrayListExtra("time_list", time_arr);
                intent.putStringArrayListExtra("name_arr", wo_names);
                startActivity(intent);
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

}
