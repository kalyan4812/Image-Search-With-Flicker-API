package com.example.volley;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import static com.example.volley.MainActivity.myphoto;
import static com.example.volley.MainActivity.mytext;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyHolder> {
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View v=inflater.inflate(R.layout.adapterlayout,parent,false);
        MyHolder myHolder=new MyHolder(v);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

     // usin glide with bitmap.
     //   myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
     //   holder.imageView.setImageBitmap(myBitmap);
        Glide.with(holder.himg.getContext()).load(myphoto.get(position)).into(holder.himg);
        holder.tv1.setText(mytext.get(position));
    }

    @Override
    public int getItemCount() {
        return myphoto.size();
    }
}
