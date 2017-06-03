package com.hengda.zwf.commonadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collections;
import java.util.List;

import static android.os.Build.VERSION_CODES.M;

/**
 * GridView 通用 Adapter
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/1/24 9:24
 */
public abstract class GCommonAdapter<T> extends BaseAdapter {

    protected List<T> mDatas = Collections.emptyList();
    protected Context mContext;
    protected LayoutInflater inflater;
    protected int layoutId;

    private int curIndex;//与ViewPager结合使用时，标识当前页
    private int pageSize;//与ViewPager结合使用时，每一页个数

    public GCommonAdapter(Context context, int layoutId, List<T> mDatas,
                          int curIndex, int pageSize) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.layoutId = layoutId;
        this.mDatas = mDatas;
        this.curIndex = curIndex;
        this.pageSize = pageSize;
    }

    public GCommonAdapter(Context context, int layoutId, List<T> mDatas) {
        inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.layoutId = layoutId;
        this.mDatas = mDatas;
        this.curIndex = -1;
        this.pageSize = -1;
    }

    @Override
    public int getCount() {
        if (pageSize == -1 || curIndex == -1) {
            return mDatas.size();
        } else {
            return mDatas.size() > (curIndex + 1) * pageSize ? pageSize : (mDatas.size() - curIndex * pageSize);
        }
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position + curIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + curIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, layoutId, position);
        int pos = position + (pageSize == -1 || curIndex == -1 ? 0 : curIndex * pageSize);
        convert(holder, mDatas.get(pos));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T t);

}