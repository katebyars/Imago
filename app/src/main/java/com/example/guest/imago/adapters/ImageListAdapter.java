//package com.example.guest.imago.adapters;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.example.guest.imago.R;
//import com.example.guest.imago.models.Image;
//import com.example.guest.imago.ui.ImageDetailActivity;
//import com.example.guest.imago.ui.ImageDetailFragment;
//
//import java.util.ArrayList;
//
//public class ImageListAdapter { private static final int MAX_WIDTH = 200;
//    private static final int MAX_HEIGHT = 200;
//
//    private ArrayList<Image> mImages = new ArrayList<>();
//    private Context mContext;
//
//    public ImageListAdapter(Context context, ArrayList<Image> restaurants) {
//        mContext = context;
//        mImages = restaurants;
//    }
//
//    @Override
//    public ImageListAdapter.ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_list_item, parent, false);
//        ImageViewHolder viewHolder = new ImageViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(ImageListAdapter.ImageViewHolder holder, int position) {
//        holder.bindImage(mImages.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return mImages.size();
//    }
//
//
//    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        @Bind(R.id.imageImageView)
//        ImageView mImageImageView;
//        @Bind(R.id.imageNameTextview)
//        TextView mImageNameTextView;
//        private Context mContext;
//
//        public ImageViewHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//
//            mContext = itemView.getContext();
//            itemView.setOnClickListener(this);
//        }
//
//        public void bindRestaurant(Image image) {
//            Picasso.with(mContext)
//                    .load(image.getImageUrl())
//                    .resize(MAX_WIDTH, MAX_HEIGHT)
//                    .centerCrop()
//                    .into(mImageImageView);
//
//            mImageNameTextView.setText(image.getImageName());
//           //matches the image view on the recycler view ?
//        }
//
//        @Override
//        public void onClick(View v) {
//            int itemPosition = getLayoutPosition();
//            Intent intent = new Intent(mContext, ImageDetailActivity.class);
//            intent.putExtra("position", itemPosition + "");
//            intent.putExtra("restaurants", Parcels.wrap(mImages));
//            mContext.startActivity(intent);
//        }
//    }
//}