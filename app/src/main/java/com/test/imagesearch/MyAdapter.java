package com.test.imagesearch;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private ArrayList<Data> dataArrayList = new ArrayList<>();

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        private ANImageView imageView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.title);
            imageView = (ANImageView)v.findViewById(R.id.imageView);
            imageView.setDefaultImageResId(R.drawable.defaultimage);
            imageView.setErrorImageResId(R.drawable.defaultimage);
        }
    }

    MyAdapter() {}

    public void setDataForAdapter(ArrayList<Data> dataArrayList) {
        this.dataArrayList.clear();
        this.dataArrayList.addAll(dataArrayList);
    }

    public void clearData() {
        this.dataArrayList.clear();
        notifyDataSetChanged();
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter, null, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data data = dataArrayList.get(position);
        holder.mTextView.setText(data.getTitle());
        if(data.getThumbnail()!=null) {
            holder.imageView.setImageUrl(data.getThumbnail().getSource());
        }
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }
}
