package com.example.streamingvideoprivateapp4;

import android.app.Activity;
import android.content.Context;
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

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MyViewHolder> {

    private List<DataSeries> seriesList;

    public SeriesAdapter(List<DataSeries> seriesList) {
        this.seriesList = seriesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(seriesList.get(position).getStitle());
        Glide.with(holder.imageView.getContext()).load(seriesList.get(position).getSthumb()).into(holder.imageView);

        //Code tương tự cho series

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Xử lý sự kiện khi click movie sẽ hiện ra movie details
                Intent sendDataToDetailsActivity = new Intent(holder.imageView.getContext(),DetailsActivity.class);
                sendDataToDetailsActivity.putExtra("title",seriesList.get(position).getStitle());
                sendDataToDetailsActivity.putExtra("link",seriesList.get(position).getSlink());
                sendDataToDetailsActivity.putExtra("cover",seriesList.get(position).getScover());
                sendDataToDetailsActivity.putExtra("thumb",seriesList.get(position).getSthumb());
                sendDataToDetailsActivity.putExtra("desc",seriesList.get(position).getSdesc());
                sendDataToDetailsActivity.putExtra("cast",seriesList.get(position).getScast());
                sendDataToDetailsActivity.putExtra("t_link",seriesList.get(position).getTlink());

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
        return seriesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movie_title);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
