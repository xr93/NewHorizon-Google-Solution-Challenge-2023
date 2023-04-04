package com.ascent.newhorizon;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ComAdapter extends RecyclerView.Adapter<ComAdapter.ViewHolder> {
    List<String> comName;
    List<String> comBuddy;
    List<String> comRate;
    List<String> comDesc;
    List<String> comBtn;
    List<Integer> comImage;
    LayoutInflater comInflater;

    private ComAdapter.OnItemClickListener listener;

    public ComAdapter() {}

    public ComAdapter(Context comContext, List<String> comName, List<String> comBuddy,
                      List<String> comRate, List<String> comDesc,List<String> comBtn,List<Integer>comImage, ComAdapter.OnItemClickListener listener){
        this.comName = comName;
        this.comBuddy = comBuddy;
        this.comRate = comRate;
        this.comDesc = comDesc;
        this.comImage = comImage;
        this.comBtn = comBtn;
        this.listener = listener;
        this.comInflater = LayoutInflater.from(comContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parentCom, int ComViewType) {
        LayoutInflater comInflater = LayoutInflater.from(parentCom.getContext());
        View viewCom = comInflater.inflate(R.layout.com_list,parentCom,false);
        ViewHolder comViewHolder = new ViewHolder(viewCom);
        return comViewHolder;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.comName.setText(comName.get(position));
        holder.comBuddy.setText(comBuddy.get(position));
        holder.comRate.setText(comRate.get(position));
        holder.comDesc.setText(comDesc.get(position));
        holder.comButton.setText(comBtn.get(position));
        holder.comImage.setImageResource(comImage.get(position));
    }

    @Override
    public int getItemCount() {
        return comName.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView comName;
        TextView comBuddy;
        TextView comRate;
        TextView comDesc;

        Button comButton;
        ImageView comImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            comName = itemView.findViewById(R.id.textComName);
            comBuddy = itemView.findViewById(R.id.textComBuddy);
            comRate = itemView.findViewById(R.id.textRate);
            comDesc = itemView.findViewById(R.id.comDescription);
            comButton = itemView.findViewById(R.id.btnCom);
            comImage = itemView.findViewById(R.id.comImage);

            comButton.setOnClickListener(view -> {
                if(listener != null) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
