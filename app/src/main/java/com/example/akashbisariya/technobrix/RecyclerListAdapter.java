package com.example.akashbisariya.technobrix;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

/**
 * Created by akash bisariya on 21-01-2018.
 */

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ModelData.Data> eventList= new ArrayList<>();
    public RecyclerListAdapter(Context context,ArrayList<ModelData.Data> arrayList) {
        mContext=context;
        eventList.addAll(arrayList);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.row_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvEventAddress.setText(eventList.get(position).getVenue());
        holder.tvEventName.setText(eventList.get(position).getEventName());
        holder.tvEventType.setText(eventList.get(position).getCategoryName());
        holder.tvEventLike.setText("Likes "+eventList.get(position).getEventNumLikes());
        holder.tvEventDate.setText(eventList.get(position).getEventTime());
        holder.pbImage.setVisibility(View.VISIBLE);



        Glide.with(mContext).load(eventList.get(position).getImageUrl().get(0).getUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.pbImage.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.pbImage.setVisibility(View.GONE);
                        return false;
                    }
                })
        .into(holder.ivEvent);
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvEventName;
        TextView tvEventAddress;
        TextView tvEventDate;
        TextView tvEventType;
        TextView tvEventLike;
        ImageView ivEvent;
        ProgressBar  pbImage;
        public ViewHolder(View itemView) {
            super(itemView);
            tvEventName =itemView.findViewById(R.id.tv_event_name);
            tvEventAddress=itemView.findViewById(R.id.tv_event_address);
            tvEventDate=itemView.findViewById(R.id.tv_event_date);
            tvEventLike=itemView.findViewById(R.id.tv_event_like);
            tvEventType=itemView.findViewById(R.id.tv_event_type);
            ivEvent=itemView.findViewById(R.id.iv_image);
            pbImage=itemView.findViewById(R.id.pb_image);
        }
    }
}
