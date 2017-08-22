package com.gmonetix.truthdare.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.gmonetix.truthdare.R;
import com.gmonetix.truthdare.model.Friend;
import com.gmonetix.truthdare.utils.Helper;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import java.util.List;

/**
 * @author Gmonetix
 */

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder>{

    private Context context;
    private List<Friend> friendList;

    public FriendAdapter(Context context, List<Friend> friendList) {
        this.friendList = friendList;
        this.context = context;
    }

    @Override
    public FriendAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View AdapterView = LayoutInflater.from(parent.getContext()).inflate(R.layout.friend_list_row, parent, false);
        return new FriendAdapter.ViewHolder(AdapterView);
    }

    @Override
    public void onBindViewHolder(final FriendAdapter.ViewHolder holder, int position) {
        Friend friend = friendList.get(position);
        holder.name.setText(friend.getName());

        ImageLoader.getInstance().displayImage(Helper.getFbImageLink(friend.getUserId(), "100"), holder.imageView, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                holder.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                holder.progressBar.setVisibility(View.GONE);
                holder.imageView.setImageResource(R.drawable.user);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                holder.progressBar.setVisibility(View.GONE);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;
        protected ImageView imageView;
        private ProgressBar progressBar;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.friend_cardview_name);
            imageView = (ImageView) v.findViewById(R.id.friend_cardview_image);
            progressBar = (ProgressBar) v.findViewById(R.id.progressBar_friend_cardview);
        }
    }

}
