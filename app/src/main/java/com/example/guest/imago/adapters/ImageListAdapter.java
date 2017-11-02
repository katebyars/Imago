package com.example.guest.imago.adapters;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.guest.imago.Constants;
import com.example.guest.imago.R;
import com.example.guest.imago.models.Image;
import com.example.guest.imago.ui.ImageDetailActivity;
import com.example.guest.imago.ui.ImageDetailFragment;
import com.example.guest.imago.ui.ImageListActivity;
import com.example.guest.imago.util.OnImageSelectedListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import org.parceler.Parcels;
import java.util.ArrayList;
import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.button;
import static android.support.constraint.R.id.parent;
import static java.security.AccessController.getContext;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> {
    private static final int MAX_WIDTH = 1000;
    private static final int MAX_HEIGHT = 1000;

    private ArrayList<Image> mImages = new ArrayList<>();
    private Context mContext;
    private int mOrientation;
    private OnImageSelectedListener mOnImageSelectedListener;

    @Bind(R.id.imageImageView) ImageView mImageImageView;

    public ImageListAdapter(Context context, ArrayList<Image> images, OnImageSelectedListener imageSelectedListener) {
        mContext = context;
        mImages = images;
        mOnImageSelectedListener = imageSelectedListener;
    }

    @Override
    public ImageListAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, parent, false);
        ImageViewHolder viewHolder = new ImageViewHolder(view, mImages, mOnImageSelectedListener);;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ImageListAdapter.ImageViewHolder holder, int position) {
        holder.bindImage(mImages.get(position));
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.imageImageView) ImageView mImageImageView;

        private Context mContext;
        private int mOrientation;
        private ArrayList<Image> mImages = new ArrayList<>();
        private OnImageSelectedListener mImageSelectedListener;

        public ImageViewHolder(View itemView, ArrayList<Image> images, OnImageSelectedListener imageSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            mOrientation = itemView.getResources().getConfiguration().orientation;
            mImages = images;
            mImageSelectedListener = imageSelectedListener;

            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
                createDetailFragment(0);
            }

            itemView.setOnClickListener(this);

            mOrientation = itemView.getResources().getConfiguration().orientation;
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(0);
            }
        }

        public void bindImage(Image image) {

            Picasso.with(mContext)
                    .load(image.getImageUrl())
                    .resize(MAX_WIDTH, MAX_HEIGHT)
                    .centerCrop()
                    .into(mImageImageView);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            mImageSelectedListener.onImageSelectedListener(itemPosition, mImages);
            if (mOrientation == Configuration.ORIENTATION_LANDSCAPE) {
                createDetailFragment(itemPosition);
            } else {
                Intent intent = new Intent(mContext, ImageDetailActivity.class);
                intent.putExtra(Constants.EXTRA_KEY_POSITION, itemPosition);
                intent.putExtra(Constants.EXTRA_KEY_IMAGES, Parcels.wrap(mImages));
                mContext.startActivity(intent);
            }
        }

        private void createDetailFragment(int position) {
            ImageDetailFragment detailFragment = ImageDetailFragment.newInstance(mImages, position);
            FragmentTransaction ft = ((FragmentActivity) mContext).getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.imageDetailContainer, detailFragment);
            ft.commit();
        }

    }
}
