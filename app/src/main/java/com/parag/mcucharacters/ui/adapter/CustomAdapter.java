package com.parag.mcucharacters.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.parag.mcucharacters.R;
import com.parag.mcucharacters.ui.UpdateActivity;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList Char_id, Char_name, Char_desc, Char_movies;

    public CustomAdapter(Activity activity, Context context, ArrayList Char_id, ArrayList Char_name, ArrayList Char_desc,
                         ArrayList Char_movies){
        this.activity = activity;
        this.context = context;
        this.Char_id = Char_id;
        this.Char_name = Char_name;
        this.Char_desc = Char_desc;
        this.Char_movies = Char_movies;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.char_id_txt.setText(String.valueOf(Char_id.get(position)));
        holder.char_name_txt.setText(String.valueOf(Char_name.get(position)));
        holder.char_desc_txt.setText(String.valueOf(Char_desc.get(position)));
        holder.char_movies_txt.setText(String.valueOf(Char_movies.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(Char_id.get(position)));
                intent.putExtra("character", String.valueOf(Char_name.get(position)));
                intent.putExtra("desc", String.valueOf(Char_desc.get(position)));
                intent.putExtra("movies", String.valueOf(Char_movies.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return Char_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView char_id_txt, char_name_txt, char_desc_txt, char_movies_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            char_id_txt = itemView.findViewById(R.id.char_id_txt);
            char_name_txt = itemView.findViewById(R.id.char_name_txt);
            char_desc_txt = itemView.findViewById(R.id.char_desc_txt);
            char_movies_txt = itemView.findViewById(R.id.char_movies_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
