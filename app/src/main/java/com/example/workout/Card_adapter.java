package com.example.workout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Card_adapter extends RecyclerView.Adapter<Card_adapter.MyViewHolder> {
    public CardView btn_cv;
    private Context mContext;
    private List<card_data> cardList;


    public Card_adapter(Context mContext, List<card_data> cardList) {
        this.mContext = mContext;
        this.cardList = cardList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        card_data cardData = cardList.get(position);
        holder.woName.setText(cardData.getName());
        holder.time.setText(String.valueOf(cardData.getTime()));
        Glide.with(mContext).load(cardData.getThumbnail()).into(holder.thumbnail);
        holder.set_txt.setText(String.valueOf(cardData.getSets()));
        holder.mcardview.setTag(position);

    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CardView mcardview;
        public TextView woName, time,set_txt;
        public ImageView thumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mcardview = itemView.findViewById(R.id.card_view);
            woName = itemView.findViewById(R.id.name);
            time = itemView.findViewById(R.id.time);
            thumbnail = itemView.findViewById(R.id.image_view);
            set_txt=itemView.findViewById(R.id.card_set);
        }


    }

}
