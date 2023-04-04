package com.ascent.newhorizon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterOne extends RecyclerView.Adapter<AdapterOne.ViewHolder> {
    List<String> blogName;
    List<String> blogLoc;
    List<String> blogDesc;

    List<String> blogBtn;
    List<Integer> blogImg;
    LayoutInflater inflater1;

    private AdapterOne.OnItemClickListener listener;

    public AdapterOne(Context ctxt, List<String> blogName, List<String> blogLoc, List<String> blogDesc, List<String> blogBtn, List<Integer> blogImg, AdapterOne.OnItemClickListener listener){
        this.blogName = blogName;
        this.blogLoc = blogLoc;
        this.blogDesc = blogDesc;
        this.blogBtn = blogBtn;
        this.blogImg = blogImg;
        this.inflater1 = LayoutInflater.from(ctxt);
        this.listener = (OnItemClickListener) listener;
    }
    

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent1, int viewType) {
        LayoutInflater layoutInflater1 = LayoutInflater.from(parent1.getContext());
        View view1 = layoutInflater1.inflate(R.layout.list_layout,parent1,false);
        ViewHolder viewHolder = new ViewHolder(view1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textName.setText(blogName.get(position));
        holder.textLocation.setText(blogLoc.get(position));
        holder.textDesc.setText(blogDesc.get(position));
        holder.button.setText(blogBtn.get(position));
        holder.imageIcon.setImageResource(blogImg.get(position));

    }

    @Override
    public int getItemCount() {
        return blogDesc.size();
    }

    public interface OnItemClickListener {
        void OnItemClick(int position);

        void onItemClick(int adapterPosition);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageIcon;
        TextView textName;
        TextView textLocation;
        TextView textDesc;

        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageIcon = itemView.findViewById(R.id.imageBlog);
            textName = itemView.findViewById(R.id.blogName);
            textLocation = itemView.findViewById(R.id.blogLoc);
            textDesc = itemView.findViewById(R.id.blogStory);
            button = itemView.findViewById(R.id.btnDonate);

            button.setOnClickListener(view -> {
                if(listener != null) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}
