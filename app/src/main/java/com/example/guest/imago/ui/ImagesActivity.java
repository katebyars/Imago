package com.example.guest.imago.ui;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.guest.imago.R;
import com.example.guest.imago.adapters.ImageListAdapter;
import com.example.guest.imago.models.Image;
import com.example.guest.imago.services.UnsplashService;

import java.io.IOException;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ImagesActivity extends AppCompatActivity {
    public static final String TAG = ImagesActivity.class.getSimpleName();
    private ImageListAdapter mAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    public ArrayList<Image> images = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");

        getImages(search);
    }

    private void getImages(String search) {
        final UnsplashService unsplashService = new UnsplashService();
        UnsplashService.findImages(search, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) {
                images = unsplashService.processResults(response);

                ImagesActivity.this.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        mAdapter = new ImageListAdapter(getApplicationContext(), images);
                        mRecyclerView.setAdapter(mAdapter);

                        RecyclerView.LayoutManager layoutManager =
                                new LinearLayoutManager(ImagesActivity.this);

                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
