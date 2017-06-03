package com.hengda.frame.commonadapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.hengda.frame.commonadapter.utils.DataUtil;
import com.hengda.zwf.commonadapter.LCommonAdapter;
import com.hengda.zwf.commonadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 单布局 ListView
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/2/3 14:02
 */
public class LvActivity extends AppCompatActivity {

    @Bind(R.id.sLv)
    ListView sLv;
    Context mContext;
    LCommonAdapter<DataUtil.DataItem> adapter;
    List<DataUtil.DataItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lv);
        ButterKnife.bind(this);
        mContext = this;

        sLv.setAdapter(adapter = new LCommonAdapter<DataUtil.DataItem>(mContext, R.layout.item_listview, dataItems) {
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
