package com.hengda.zwf.commonadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

public abstract class RCommonAdapter<T> extends RecyclerView.Adapter<ViewHolder>{

    protected Context mContext;
    protected List<T> mDatas = Collections.emptyList();
    protected int mLayoutId;
    protected LayoutInflater mInflater;

    public RCommonAdapter(Context context, int layoutId, List<T> mDatas) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mLayoutId = layoutId;
        this.mDatas = mDatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.get(mContext, null, parent, mLayoutId, -1);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.updatePosition(position);
        convert(holder, mDatas.get(position));
    }

    protected int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }

    public abstract void convert(ViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addItem(T message, int position) {
        mDatas.add(position, message);
        notifyItemInserted(position);
    }

    public T getItemAtPosition(int position) {
        return mDatas.get(position);
    }

}

