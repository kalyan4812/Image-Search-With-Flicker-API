package com.example.volley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import static com.example.volley.MainActivity.myphoto;
import static com.example.volley.MainActivity.mytext;


public class adaptor extends BaseAdapter {

    @Override
    public int getCount() {
        return myphoto.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(MainActivity.main);
        View v=inflater.inflate(R.layout.adapterlayout,null);
        ImageView himg=v.findViewById(R.id.img);
        TextView tv1=v.findViewById(R.id.tv1);

        Picasso.get().load(myphoto.get(position)).into(himg);
        tv1.setText(mytext.get(position));
        // tv2.setText(hr3[position]);



        return v;
    }
}
