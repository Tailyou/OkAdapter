package com.hengda.zwf.commonadapter;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

public abstract class RMultiItemCommonAdapter<T> extends RCommonAdapter<T> {

    protected RMultiItemTypeSupport<T> mRMultiItemTypeSupport;

    public RMultiItemCommonAdapter(Context context,
                                   List<T> datas,
                                   RMultiItemTypeSupport<T> RMultiItemTypeSupport) {
        super(context, -1, datas);
        mRMultiItemTypeSupport = RMultiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        if (mRMultiItemTypeSupport != null)
            return mRMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mRMultiItemTypeSupport == null) return super.onCreateViewHolder(parent, viewType);
        int layoutId = mRMultiItemTypeSupport.getLayoutId(viewType);
        ViewHolder holder = ViewHolder.get(mContext, null, parent, layoutId, -1);
        return holder;
    }

}
