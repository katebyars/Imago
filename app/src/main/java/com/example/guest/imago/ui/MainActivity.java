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
//
//import static com.example.guest.imago.R.id.appNameTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findImagesButton) Button mFindImagesButton;
//    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    @Bind(R.id.searchEditText) EditText mSearchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface latoItalic = Typeface.createFromAsset(getAssets(), "fonts/Lato-Italic.ttf");
        mSearchEditText.setTypeface(latoItalic);

//        Typeface latoLight = Typeface.createFromAsset(getAssets(), "fonts/Lato-Light.ttf");
//        mAppNameTextView.setTypeface(latoLight);

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