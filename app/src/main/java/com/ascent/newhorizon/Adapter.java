package com.ascent.newhorizon;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    List<String> title;
    List<Integer> image;
    List<String> maker;
    LayoutInflater inflater;

    public Adapter(Context context, List<String> title, List<String> maker, List<Integer> image){
        this.title = title;
        this.image = image;
        this.maker = maker;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(title.get(position));
        holder.maker.setText(maker.get(position));
        holder.gridIcon.setImageResource(image.get(position));
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView maker;
        ImageView gridIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView2);
            maker = itemView.findViewById(R.id.text_description);
            gridIcon = itemView.findViewById(R.id.imageView2);

        }
    }
}
