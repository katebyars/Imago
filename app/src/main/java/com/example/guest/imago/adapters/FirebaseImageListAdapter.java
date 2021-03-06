package com.example.guest.imago.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.guest.imago.models.Image;
import com.example.guest.imago.ui.ImageDetailActivity;
import com.example.guest.imago.util.ItemTouchHelperAdapter;
import com.example.guest.imago.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

public class FirebaseImageListAdapter extends FirebaseRecyclerAdapter<Image, FirebaseImageViewHolder> implements ItemTouchHelperAdapter {

    private ChildEventListener mChildEventListener;
    private ArrayList<Image> mImages = new ArrayList<>();
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseImageListAdapter(Class<Image> modelClass, int modelLayout,
                                         Class<FirebaseImageViewHolder> viewHolderClass,
                                         Query ref, OnStartDragListener onStartDragListener, Context context) {

        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mImages.add(dataSnapshot.getValue(Image.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void populateViewHolder(final FirebaseImageViewHolder viewHolder, Image model, int position) {
        viewHolder.bindImage(model);

        viewHolder.mDragIcon.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImageDetailActivity.class);
                intent.putExtra("position", viewHolder.getAdapterPosition());
                Log.d("hello", "hello");
                Log.d("getAdapterposition", String.valueOf(viewHolder.getAdapterPosition()));

                intent.putExtra("images", Parcels.wrap(mImages));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mImages, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mImages.remove(position);
        getRef(position).removeValue();
    }

    private void setIndexInFirebase() {
        for (Image image : mImages) {
            int index = mImages.indexOf(image);
            DatabaseReference ref = getRef(index);
            image.setIndex(Integer.toString(index));
            ref.setValue(image);
        }
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }

}