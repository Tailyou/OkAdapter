package com.hengda.zwf.commonadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collections;
import java.util.List;

/**
 * ListView 通用 Adapter
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/1/24 9:32
 */
public abstract class LCommonAdapter<T> extends BaseAdapter {

    protected Context mContext;
    protected List<T> mDatas = Collections.emptyList();
    protected LayoutInflater mInflater;
    protected int layoutId;

    public LCommonAdapter(Context context, int layoutId, List<T> mDatas) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T t);

}
