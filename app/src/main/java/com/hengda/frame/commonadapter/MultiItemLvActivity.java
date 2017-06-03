package com.hengda.frame.commonadapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.hengda.frame.commonadapter.utils.DataUtil;
import com.hengda.zwf.commonadapter.LCommonAdapter;
import com.hengda.zwf.commonadapter.LMultiItemCommonAdapter;
import com.hengda.zwf.commonadapter.LMultiItemTypeSupport;
import com.hengda.zwf.commonadapter.RSectionSupport;
import com.hengda.zwf.commonadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 多布局 ListView
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/2/3 14:02
 */
public class MultiItemLvActivity extends AppCompatActivity {

    @Bind(R.id.sLv)
    ListView sLv;

    Context mContext;
    LMultiItemTypeSupport<DataUtil.DataItem> itemTypeSupport;
    LMultiItemCommonAdapter<DataUtil.DataItem> adapter;
    List<DataUtil.DataItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv);
        ButterKnife.bind(this);
        mContext = this;

        itemTypeSupport = new LMultiItemTypeSupport<DataUtil.DataItem>() {
            @Override
            public int getLayoutId(int position, DataUtil.DataItem dataItem) {
                return dataItem.getIndex() % 2 == 0 ? R.layout.item_multi_item_left : R.layout.item_multi_item_right;
            }

            @Override
            public int getViewTypeCount() {
                return 2;
            }

            @Override
            public int getItemViewType(int position, DataUtil.DataItem dataItem) {
                return dataItem.getIndex() % 2;
            }
        };

        sLv.setAdapter(adapter = new LMultiItemCommonAdapter<DataUtil.DataItem>(mContext, dataItems, itemTypeSupport) {
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
