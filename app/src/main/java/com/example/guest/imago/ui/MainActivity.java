package com.example.guest.imago.ui;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.imago.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findImagesButton) Button mFindImagesButton;
    @Bind(R.id.searchEditText) EditText mSearchEditText;
//    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
//        mAppNameTextView.setTypeface(ostrichFont);

        mFindImagesButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mFindImagesButton) {
            String query = mSearchEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, ImageListActivity.class);
            intent.putExtra("query", query);
            startActivity(intent);
        }
    }
}