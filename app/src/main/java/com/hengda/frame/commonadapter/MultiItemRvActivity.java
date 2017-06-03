package com.hengda.frame.commonadapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.hengda.frame.commonadapter.utils.DataUtil;
import com.hengda.zwf.commonadapter.RCommonAdapter;
import com.hengda.zwf.commonadapter.RMultiItemCommonAdapter;
import com.hengda.zwf.commonadapter.RMultiItemTypeSupport;
import com.hengda.zwf.commonadapter.RSectionAdapter;
import com.hengda.zwf.commonadapter.RSectionSupport;
import com.hengda.zwf.commonadapter.ViewHolder;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 多布局 RecyclerView
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/2/3 14:03
 */
public class MultiItemRvActivity extends AppCompatActivity {

    @Bind(R.id.sRv)
    RecyclerView sRv;

    Context mContext;
    RMultiItemCommonAdapter<DataUtil.DataItem> adapter;
    RMultiItemTypeSupport<DataUtil.DataItem> itemTypeSupport;
    List<DataUtil.DataItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        ButterKnife.bind(this);
        mContext = this;

        sRv.setLayoutManager(new LinearLayoutManager(mContext));
        itemTypeSupport = new RMultiItemTypeSupport<DataUtil.DataItem>() {
            @Override
            public int getLayoutId(int itemType) {
                return itemType == 0 ? R.layout.item_multi_item_left : R.layout.item_multi_item_right;
            }

            @Override
            public int getItemViewType(int position, DataUtil.DataItem dataItem) {
                return dataItem.getIndex() % 2;
            }
        };
        sRv.setAdapter(adapter = new RMultiItemCommonAdapter<DataUtil.DataItem>(mContext, dataItems, itemTypeSupport) {
            @Override
            public void convert(ViewHolder holder, DataUtil.DataItem item) {
                String packageName = getBaseContext().getPackageName();
                Resources resources = getBaseContext().getResources();
                int resId = resources.getIdentifier("res_" + item.getIndex(), "mipmap", packageName);
                holder.setImageResource(R.id.imageView, resId);
                holder.setText(R.id.textView, item.getName());
            }
        });
        dataItems.addAll(DataUtil.getDataItems());
        adapter.notifyDataSetChanged();
    }

}
