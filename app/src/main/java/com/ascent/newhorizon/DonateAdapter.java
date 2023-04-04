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

import org.w3c.dom.Text;

import java.util.List;


public class DonateAdapter extends RecyclerView.Adapter<DonateAdapter.ViewHolder> {
    List<String> donateText;
    List<String> bank;
    List<String> acc;
    List<String> holderacc;
    List<String> ref;
    List<String> reference1;
    List<String> reference2;

    List<String> donateBtn;
    LayoutInflater inflaterDonate;

    private DonateAdapter.OnItemClickListener listener;

    public DonateAdapter(Context dContext){
        this.inflaterDonate = LayoutInflater.from(dContext);
    }

    public DonateAdapter(Context dContext, List<String>donateText, List<String>bank, List<String>acc, List<String>holderacc,
                         List<String>ref,  List<String> reference1,  List<String> reference2, List<String> donateBtn, DonateAdapter.OnItemClickListener listener){
        this.donateText = donateText;
        this.bank = bank;
        this.acc = acc;
        this.holderacc = holderacc;
        this.ref = ref;
        this.reference1 = reference1;
        this.reference2 = reference2;
        this.donateBtn = donateBtn;
        this.listener = listener;
        this.inflaterDonate = LayoutInflater.from(dContext);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup dParent, int viewType) {
        LayoutInflater inflaterDonate = LayoutInflater.from(dParent.getContext());
        View dView = inflaterDonate.inflate(R.layout.donate_layout,dParent,false);
        ViewHolder dViewHolder = new ViewHolder(dView);
        return dViewHolder;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(DonateAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textDonate.setText(donateText.get(position));
        holder.textBank.setText(bank.get(position));
        holder.textAcc.setText(acc.get(position));
        holder.textHolder.setText(holderacc.get(position));
        holder.textRef.setText(ref.get(position));
        holder.textReference1.setText(reference1.get(position));
        holder.textReference2.setText(reference2.get(position));
        holder.donateBtn.setText(donateBtn.get(position));
    }

    @Override
    public int getItemCount() {
        return donateText.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textDonate;
        TextView textBank;
        TextView textAcc;
        TextView textHolder;
        TextView textRef;
        TextView textReference1;
        TextView textReference2;

        Button donateBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textDonate = itemView.findViewById(R.id.donateText1);
            textBank = itemView.findViewById(R.id.donateBank);
            textAcc = itemView.findViewById(R.id.donateAcc);
            textHolder = itemView.findViewById(R.id.donateHolder);
            textRef = itemView.findViewById(R.id.donateRef);
            textReference1 = itemView.findViewById(R.id.donateCode);
            textReference2 = itemView.findViewById(R.id.donateCode2);
            donateBtn = itemView.findViewById(R.id.btnBack);

            donateBtn.setOnClickListener(view -> {
                if(listener != null) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
