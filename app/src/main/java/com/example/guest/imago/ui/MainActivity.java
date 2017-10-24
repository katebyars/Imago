package com.example.guest.imago.ui;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.guest.imago.R;
import com.example.guest.imago.fragments.ProfileFragment;
import com.example.guest.imago.fragments.SavedFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
//
//import static com.example.guest.imago.R.id.appNameTextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item){
            switch (item.getItemId()) {
                case R.id.profile:
                    setTitle("Profile");
                    ProfileFragment profileFragment = new ProfileFragment();
                    FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction1.replace(R.id.FragmentFrame, profileFragment, "FragmentName");
                    fragmentTransaction1.commit();

                    return true;

                case R.id.saved:
                    setTitle("Saved");
                    SavedFragment savedFragment = new SavedFragment();
                    FragmentTransaction fragmentTransaction2 = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction2.replace(R.id.FragmentFrame, savedFragment, "FragmentName");
                    fragmentTransaction2.commit();
                    return true;

//                case R.id.favorites:
//                    setTitle("Favorites");
//                    FavoritesFragment favoritesFragment = new FavoritesFragment();
//                    FragmentTransaction fragmentTransaction3 = getSupportFragmentManager().beginTransaction();
//                    fragmentTransaction3.replace(R.id.FragmentFrame, favoritesFragment, "FragmentName");
//                    fragmentTransaction3.commit();
//                    return true;
            }
            return false;
        }
    };

    @Bind(R.id.findImagesButton) Button mFindImagesButton;
    @Bind(R.id.searchEditText) EditText mSearchEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface latoItalic = Typeface.createFromAsset(getAssets(), "fonts/Lato-Italic.ttf");
        mSearchEditText.setTypeface(latoItalic);

        mFindImagesButton.setOnClickListener(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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