package com.example.guest.imago.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.guest.imago.Constants;
import com.example.guest.imago.R;
import com.example.guest.imago.adapters.FirebaseImageListAdapter;
import com.example.guest.imago.adapters.FirebaseImageViewHolder;
import com.example.guest.imago.models.Image;
import com.example.guest.imago.util.ItemTouchHelperAdapter;
import com.example.guest.imago.util.OnStartDragListener;
import com.example.guest.imago.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedImageListFragment extends Fragment implements OnStartDragListener {
    private DatabaseReference mImageReference;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private FirebaseImageListAdapter mFirebaseAdapter;
    protected ItemTouchHelper mItemTouchHelper;

    public SavedImageListFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_image_list, container, false);
        ButterKnife.bind(this, view);
        setUpFirebaseAdapter();
        return view;
    }

    private void setUpFirebaseAdapter() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_SAVED_IMAGE)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);
        mFirebaseAdapter = new FirebaseImageListAdapter(Image.class,
                R.layout.image_list_item_drag, FirebaseImageViewHolder.class,
                query, this, getActivity());

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        mFirebaseAdapter = new FirebaseImageListAdapter(Image.class,
                R.layout.image_list_item_drag, FirebaseImageViewHolder.class,
                query, this, getActivity());

        mFirebaseAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                mFirebaseAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
