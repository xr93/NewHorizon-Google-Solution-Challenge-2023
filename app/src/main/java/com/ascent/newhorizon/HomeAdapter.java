package com.ascent.newhorizon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    List<String> title1;
    List<String> button;
    List<String> description;
    LayoutInflater homeInflater;
    private OnItemClickListener listener;

    public HomeAdapter(Context context1, List<String> title1, List<String> description, List<String> button, OnItemClickListener listener) {
        this.title1 = title1;
        this.description = description;
        this.button = button;
        this.homeInflater = LayoutInflater.from(context1);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parentHome, int viewType1) {
        LayoutInflater homeInflater = LayoutInflater.from(parentHome.getContext());
        View viewHome = homeInflater.inflate(R.layout.home_list,parentHome,false);
        ViewHolder viewHolder = new ViewHolder(viewHome);
        return viewHolder;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textTitle.setText(title1.get(position));
        holder.description.setText(description.get(position));
        holder.button.setText(button.get(position));
    }

    @Override
    public int getItemCount() {
        return title1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView description;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.featureTitle);
            description = itemView.findViewById(R.id.homeDesc);
            button = itemView.findViewById(R.id.homeButton);

            button.setOnClickListener(view -> {
                if(listener != null) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

}

