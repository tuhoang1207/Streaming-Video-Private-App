package com.example.streamingvideoprivateapp4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    //Cho Slider
    private List<DataModel> dataModels;
    private SliderAdapter sliderAdapter;

    //Cho Featured
    private List<DataFeatured> dataFeatureds;
    private RecyclerView featuredRecyclerView;
    private FeaturedAdapter featuredAdapter;

    //Cho Series
    private List<DataSeries> dataSeries;
    private RecyclerView seriesRecyclerView;
    private SeriesAdapter seriesAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Streaming Video Private App");

        FirebaseApp.initializeApp(this);

        SliderView sliderView = findViewById(R.id.sliderView);

        sliderAdapter = new SliderAdapter(this);
        sliderView.setSliderAdapter(sliderAdapter);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.setScrollTimeInSec(6);//Tăng hay giảm thời gian slide auto chuyển đều được
        renewItems(sliderView);

        //Load data từ Firebase
        loadFirebaseForSlider();
        loadFeaturedData();
;    }

    /*private void loadData() {
        loadFeaturedData();
        loadMoviesData();
    }*/

    private void loadMoviesData() {

    }

    private void loadFeaturedData() {
        //Load data featured từ Firebase
        DatabaseReference FRef = database.getReference("featured");
        featuredRecyclerView = findViewById(R.id.recyclerView2);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL); //Set layout default là vertical
        //Đảo thứ tự layout để data thêm vào sau xuất hiện trước
        //Cụ thể khi thêm sẽ có thứ tự mặc định 0 1 2 3, đảo ngược lại sẽ xuất hiện 3 2 1 0
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        featuredRecyclerView.setLayoutManager(layoutManager);

        dataFeatureds = new ArrayList<>();
        featuredAdapter = new FeaturedAdapter(dataFeatureds);
        featuredRecyclerView.setAdapter(featuredAdapter);

        FRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot:snapshot.getChildren()){
                    DataFeatured dataFeatured = contentSnapShot.getValue(DataFeatured.class);
                    dataFeatureds.add(dataFeatured);
                }
                featuredAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Load Series adapter sau khi load Featured adapter
        loadSeriesData();
    }

    private void loadSeriesData() {
        DatabaseReference SRef = database.getReference("series");
        seriesRecyclerView = findViewById(R.id.recyclerView3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        seriesRecyclerView.setLayoutManager(layoutManager);

        dataSeries = new ArrayList<>();
        seriesAdapter = new SeriesAdapter(dataSeries);

        seriesRecyclerView.setAdapter(seriesAdapter);
        SRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSnapShot:snapshot.getChildren()){
                    DataSeries newDataSeries = contentSnapShot.getValue(DataSeries.class);
                    dataSeries.add(newDataSeries);
                }
                seriesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //error code
            }
        });
    }

    private void loadFirebaseForSlider() {
        myRef.child("trailer").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot contentSlider: snapshot.getChildren()) {
                    DataModel sliderItem = contentSlider.getValue(DataModel.class);
                    dataModels.add(sliderItem);
                }
                sliderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, ""+error.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    public void renewItems(View view) {
        dataModels = new ArrayList<>();
        DataModel dataItems = new DataModel();
        dataModels.add(dataItems);

        sliderAdapter.renewItems(dataModels);
        sliderAdapter.deleteItems(0);
    }
}