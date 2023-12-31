package com.example.streamingvideoprivateapp4;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
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
        //Load thumbnail
        Glide.with(holder.itemView.getContext()).load(dataFeatureds.get(position).getFthumb()).into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xử lý sự kiện khi click movie sẽ hiện ra movie details
                Intent sendDataToDetailsActivity = new Intent(holder.imageView.getContext(),DetailsActivity.class);
                sendDataToDetailsActivity.putExtra("title",dataFeatureds.get(position).getFtitle());
                sendDataToDetailsActivity.putExtra("link",dataFeatureds.get(position).getFlink());
                sendDataToDetailsActivity.putExtra("cover",dataFeatureds.get(position).getFcover());
                sendDataToDetailsActivity.putExtra("thumb",dataFeatureds.get(position).getFthumb());
                sendDataToDetailsActivity.putExtra("desc",dataFeatureds.get(position).getFdes());
                sendDataToDetailsActivity.putExtra("cast",dataFeatureds.get(position).getFcast());
                sendDataToDetailsActivity.putExtra("t_link",dataFeatureds.get(position).getTlink());

                //Tạo mô hình chuyển đổi trước khi gửi data
                ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)holder.itemView.getContext(),holder.imageView,"imageMain");
                //Share tên phần tử giống như file xml (imageMain)
                holder.itemView.getContext().startActivity(sendDataToDetailsActivity,optionsCompat.toBundle());
                //Tạo adapter và data cho recycle view của episodes và casts

            }
        });
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
