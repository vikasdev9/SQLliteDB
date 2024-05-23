package com.example.sqlitenotesapp;

import static android.widget.Toast.makeText;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myAdaptor extends RecyclerView.Adapter<myAdaptor.MYViewHolder> {
    Context context;
    ArrayList book_id,book_title,book_author,book_pages;

    Activity activity;

    Animation animation;
    public myAdaptor(Activity activity,Context context, ArrayList book_id,
    ArrayList book_title, ArrayList book_author, ArrayList book_pages) {
        this.activity = activity;
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;

    }

    @NonNull
    @Override
    public MYViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MYViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MYViewHolder holder, int position) {
//        this.position=position;
   holder.book_id1.setText(String.valueOf(book_id.get(position)));
   holder.book_title1.setText(String.valueOf(book_title.get(position)));
   holder.book_author1.setText(String.valueOf(book_author.get(position)));
   holder.book_pages1.setText(String.valueOf(book_pages.get(position)));
   holder.mainLayout.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent intent=new Intent(context, UpdateActivity.class);
           intent.putExtra("id",String.valueOf(book_id.get(position)));
           intent.putExtra("title",String.valueOf(book_title.get(position)));
           intent.putExtra("author",String.valueOf(book_author.get(position)));
           intent.putExtra("pages",String.valueOf(book_pages.get(position)));
           activity.startActivityForResult(intent,1);
           Log.i("check", String.valueOf(book_title.get(position)));
           Log.i("check", "Hello DB");
           Toast.makeText(activity,"You clicked on "+book_title.get(position),Toast.LENGTH_SHORT).show();
       }
   });
    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MYViewHolder extends RecyclerView.ViewHolder{
        TextView book_id1,book_title1,book_author1,book_pages1;
        CardView mainLayout;
        public MYViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id1=itemView.findViewById(R.id.text_id);
            book_title1=itemView.findViewById(R.id.text_title);
            book_author1=itemView.findViewById(R.id.text_author);
            book_pages1=itemView.findViewById(R.id.text_pages);
            animation= AnimationUtils.loadAnimation(context,R.anim.translate_animation);
            mainLayout=itemView.findViewById(R.id.mainLayout);
            mainLayout.setAnimation(animation);
        }
    }
}
