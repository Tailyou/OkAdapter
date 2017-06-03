package com.hengda.frame.commonadapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hengda.frame.commonadapter.utils.DataUtil;
import com.hengda.zwf.commonadapter.RSectionAdapter;
import com.hengda.zwf.commonadapter.RSectionSupport;
import com.hengda.zwf.commonadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 带章节标题 RecyclerView
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/2/3 14:03
 */
public class SectionRvActivity extends AppCompatActivity {

    @Bind(R.id.sRv)
    RecyclerView sRv;

    Context mContext;
    List<DataUtil.DataItem> dataItems = new ArrayList<>();
    RSectionSupport<DataUtil.DataItem> sectionSupport;
    RSectionAdapter<DataUtil.DataItem> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        ButterKnife.bind(this);
        mContext = this;

        sectionSupport = new RSectionSupport<DataUtil.DataItem>() {
            @Override
            public int sectionHeaderLayoutId() {
                return R.layout.item_section;
            }

            @Override
            public int sectionTitleTextViewId() {
                return R.id.tvUnitName;
            }

            @Override
            public String getTitle(DataUtil.DataItem dataItem) {
                return dataItem.getUnitName();
            }
        };
        sRv.setLayoutManager(new LinearLayoutManager(mContext));
        sRv.setAdapter(adapter = new RSectionAdapter<DataUtil.DataItem>(mContext, R.layout.item_recyclerview,
                dataItems, sectionSupport) {
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
