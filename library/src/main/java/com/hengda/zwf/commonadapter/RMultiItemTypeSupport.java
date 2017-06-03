package com.hengda.zwf.commonadapter;

public interface RMultiItemTypeSupport<T> {

    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);

}