package com.hengda.zwf.commonadapter;

public interface LMultiItemTypeSupport<T> {

    int getLayoutId(int position, T t);

    int getViewTypeCount();

    int getItemViewType(int position, T t);

}