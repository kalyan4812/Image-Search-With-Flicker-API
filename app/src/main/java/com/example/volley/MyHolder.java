package com.example.volley;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyHolder extends RecyclerView.ViewHolder {
    ImageView himg;
    TextView tv1;
    public MyHolder(@NonNull View itemView) {
        super(itemView);
        himg=itemView.findViewById(R.id.img);
         tv1=itemView.findViewById(R.id.tv1);
    }
}
