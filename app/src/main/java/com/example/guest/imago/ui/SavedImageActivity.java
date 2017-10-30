package com.example.guest.imago.ui;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.guest.imago.Constants;
import com.example.guest.imago.R;
import com.example.guest.imago.adapters.FirebaseImageViewHolder;
import com.example.guest.imago.models.Image;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedImageActivity extends AppCompatActivity{

    private DatabaseReference mImageReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView) RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_images);
        ButterKnife.bind(this);

        mImageReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_SAVED_IMAGE);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Image, FirebaseImageViewHolder>
                (Image.class, R.layout.image_list_item, FirebaseImageViewHolder.class,
                        mImageReference) {

            @Override
            protected void populateViewHolder(FirebaseImageViewHolder viewHolder,
                                              Image model, int position) {
                viewHolder.bindImage(model);
            }
        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}