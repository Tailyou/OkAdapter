package com.hengda.zwf.commonadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public abstract class RSectionAdapter<T> extends RMultiItemCommonAdapter<T> {

    private RSectionSupport mRSectionSupport;
    private static final int TYPE_SECTION = 0;
    private LinkedHashMap<String, Integer> mSections;

    private RMultiItemTypeSupport<T> headerItemTypeSupport;

    @Override
    public int getItemViewType(int position) {
        return mRMultiItemTypeSupport.getItemViewType(position, null);
    }

    final RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            findSections();
        }
    };

    public RSectionAdapter(Context context, int layoutId, List<T> datas, RSectionSupport
            RSectionSupport) {
        this(context, layoutId, null, datas, RSectionSupport);
    }

    public RSectionAdapter(Context context, RMultiItemTypeSupport RMultiItemTypeSupport, List<T>
            datas, RSectionSupport RSectionSupport) {
        this(context, -1, RMultiItemTypeSupport, datas, RSectionSupport);
    }

    public RSectionAdapter(Context context, int layoutId, RMultiItemTypeSupport
            RMultiItemTypeSupport, List<T> datas, RSectionSupport RSectionSupport) {
        super(context, datas, null);
        mLayoutId = layoutId;
        initMulitiItemTypeSupport(layoutId, RMultiItemTypeSupport);
        mRMultiItemTypeSupport = headerItemTypeSupport;
        mRSectionSupport = RSectionSupport;
        mSections = new LinkedHashMap<>();
        findSections();
        registerAdapterDataObserver(observer);
    }

    private void initMulitiItemTypeSupport(int layoutId, final RMultiItemTypeSupport
            RMultiItemTypeSupport) {
        if (layoutId != -1) {
            headerItemTypeSupport = new RMultiItemTypeSupport<T>() {
                @Override
                public int getLayoutId(int itemType) {
                    if (itemType == TYPE_SECTION)
                        return mRSectionSupport.sectionHeaderLayoutId();
                    else
                        return mLayoutId;
                }

                @Override
                public int getItemViewType(int position, T o) {
                    int positionVal = getIndexForPosition(position);
                    return mSections.values().contains(position) ?
                            TYPE_SECTION :
                            1;
                }
            };
        } else if (RMultiItemTypeSupport != null) {
            headerItemTypeSupport = new RMultiItemTypeSupport<T>() {
                @Override
                public int getLayoutId(int itemType) {
                    if (itemType == TYPE_SECTION)
                        return mRSectionSupport.sectionHeaderLayoutId();
                    else
                        return RMultiItemTypeSupport.getLayoutId(itemType);
                }

                @Override
                public int getItemViewType(int position, T o) {
                    int positionVal = getIndexForPosition(position);
                    return mSections.values().contains(position) ?
                            TYPE_SECTION :
                            RMultiItemTypeSupport.getItemViewType(positionVal, o);
                }
            };
        } else {
            throw new RuntimeException("layoutId or MultiItemTypeSupport must set one.");
        }

    }

    @Override
    protected boolean isEnabled(int viewType) {
        if (viewType == TYPE_SECTION)
            return false;
        return super.isEnabled(viewType);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        unregisterAdapterDataObserver(observer);
    }

    public void findSections() {
        int n = mDatas.size();
        int nSections = 0;
        mSections.clear();

        for (int i = 0; i < n; i++) {
            String sectionName = mRSectionSupport.getTitle(mDatas.get(i));

            if (!mSections.containsKey(sectionName)) {
                mSections.put(sectionName, i + nSections);
                nSections++;
            }
        }

    }

    @Override
    public int getItemCount() {
        return super.getItemCount() + mSections.size();
    }

    public int getIndexForPosition(int position) {
        int nSections = 0;

        Set<Map.Entry<String, Integer>> entrySet = mSections.entrySet();
        for (Map.Entry<String, Integer> entry : entrySet) {
            if (entry.getValue() < position) {
                nSections++;
            }
        }
        return position - nSections;
    }

    @Override
    protected int getPosition(RecyclerView.ViewHolder viewHolder) {
        return getIndexForPosition(viewHolder.getAdapterPosition());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        position = getIndexForPosition(position);
        if (holder.getItemViewType() == TYPE_SECTION) {
            holder.setText(mRSectionSupport.sectionTitleTextViewId(), mRSectionSupport.getTitle
                    (mDatas.get(position)));
            return;
        }
        super.onBindViewHolder(holder, position);
    }

}
