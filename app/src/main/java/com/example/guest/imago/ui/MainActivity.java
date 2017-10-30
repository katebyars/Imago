package com.example.guest.imago.ui;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.imago.Constants;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findImagesButton) Button mFindImagesButton;
    @Bind(R.id.searchEditText) EditText mSearchEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.recentSearches) TextView mRecentSearchesTextView;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;
    private ImageListAdapter mAdapter;
    public ArrayList<Image> mImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface blackTinBox = Typeface.createFromAsset(getAssets(), "fonts/Walkwayrounded.ttf");
        mAppNameTextView.setTypeface(blackTinBox);

        mFindImagesButton.setOnClickListener(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);

    }

    @Override
    public void onClick(View v) {
        if(v == mFindImagesButton) {
            String query = mSearchEditText.getText().toString();
            addToSharedPreferences(query);
            Intent intent = new Intent(MainActivity.this, ImageListActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    }

    private void addToSharedPreferences(String query) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, query).apply();
    }

//    @Override
//    public void onClick(View v) {
//        if(v == mFindImagesButton) {
//            String query = mSearchEditText.getText().toString();
//            if (!(query).equals("")) {
//                addToSharedPreferences(query);
//                Intent intent = new Intent(MainActivity.this, ImageListActivity.class);
//            intent.putExtra("query", query);
//            startActivity(intent);
//            }
//            if((query).equals("")) {
//                Toast.makeText(MainActivity.this, "Enter a location!", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//
//    private void addToSharedPreferences(String query) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, query).apply();
//    }

}