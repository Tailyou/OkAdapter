package com.hengda.zwf.commonadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * 多布局ListView通用Adapter
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/1/24 9:34
 */
public abstract class LMultiItemCommonAdapter<T> extends LCommonAdapter<T> {

    protected LMultiItemTypeSupport<T> mLMultiItemTypeSupport;

    public LMultiItemCommonAdapter(Context context, List<T> datas, LMultiItemTypeSupport<T>
            LMultiItemTypeSupport) {
        super(context, -1, datas);
        mLMultiItemTypeSupport = LMultiItemTypeSupport;
        if (mLMultiItemTypeSupport == null)
            throw new IllegalArgumentException("the mMultiItemTypeSupport can not be null.");
    }

    @Override
    public int getViewTypeCount() {
        if (mLMultiItemTypeSupport != null)
            return mLMultiItemTypeSupport.getViewTypeCount();
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (mLMultiItemTypeSupport != null)
            return mLMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
        return super.getItemViewType(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mLMultiItemTypeSupport == null)
            return super.getView(position, convertView, parent);
        int layoutId = mLMultiItemTypeSupport.getLayoutId(position, getItem(position));
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent, layoutId, position);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

}
