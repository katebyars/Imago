package com.example.guest.imago.ui;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guest.imago.Constants;
import com.example.guest.imago.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findImagesButton) Button mFindImagesButton;
    @Bind(R.id.searchEditText) EditText mSearchEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface blackTinBox = Typeface.createFromAsset(getAssets(), "fonts/BlackTinBox.ttf");
        mAppNameTextView.setTypeface(blackTinBox);

        mFindImagesButton.setOnClickListener(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

    }

    @Override
    public void onClick(View v) {
        if(v == mFindImagesButton) {
            String query = mSearchEditText.getText().toString();
            if (query != "") {
                addToSharedPreferences(query);
                Intent intent = new Intent(MainActivity.this, ImageListActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
            }
            if(query == "") {
                Toast.makeText(MainActivity.this, "Enter some data!", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }

}