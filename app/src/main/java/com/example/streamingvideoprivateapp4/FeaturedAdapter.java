package com.example.streamingvideoprivateapp4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

//Chia Adapter class cho RecycleView
public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.MyViewHolder> {
    private List<DataFeatured> dataFeatureds;

    public FeaturedAdapter(List<DataFeatured> dataFeatureds) {
        this.dataFeatureds = dataFeatureds;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(dataFeatureds.get(position).getFtitle());
        Glide.with(holder.itemView.getContext()).load(dataFeatureds.get(position).getFcover()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return dataFeatureds.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.movie_title);
        }
    }
}
