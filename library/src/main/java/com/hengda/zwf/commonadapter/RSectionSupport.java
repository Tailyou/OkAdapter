package com.hengda.zwf.commonadapter;

public interface RSectionSupport<T> {

    int sectionHeaderLayoutId();

    int sectionTitleTextViewId();

    String getTitle(T t);

}
