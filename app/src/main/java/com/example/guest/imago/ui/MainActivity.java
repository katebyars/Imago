package com.example.guest.imago.ui;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.guest.imago.Constants;
import com.example.guest.imago.R;
import com.example.guest.imago.ui.ImageListActivity;
import com.example.guest.imago.ui.LoginActivity;
import com.example.guest.imago.ui.SavedImageActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findImagesButton) Button mFindImagesButton;
    @Bind(R.id.savedImagesButton) Button mSavedImagesButton;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface walk = Typeface.createFromAsset(getAssets(), "fonts/Walkwayrounded.ttf");
        mAppNameTextView.setTypeface(walk);

        mFindImagesButton.setOnClickListener(this);
        mSavedImagesButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if(v == mFindImagesButton) {
            Intent intent = new Intent(MainActivity.this, ImageListActivity.class);
            startActivity(intent);
        }

        if (v == mSavedImagesButton) {
            Intent intent = new Intent(MainActivity.this, SavedImageActivity.class);
            startActivity(intent);
        }

    }

}