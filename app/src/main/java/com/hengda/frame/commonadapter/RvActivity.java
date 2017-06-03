package com.hengda.frame.commonadapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hengda.frame.commonadapter.utils.DataUtil;
import com.hengda.zwf.commonadapter.LCommonAdapter;
import com.hengda.zwf.commonadapter.RCommonAdapter;
import com.hengda.zwf.commonadapter.ROnItemTouchListener;
import com.hengda.zwf.commonadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.hengda.frame.commonadapter.R.id.sLv;
import static com.hengda.frame.commonadapter.R.id.tabMode;

/**
 * 单布局 RecyclerView
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/2/3 14:03
 */
public class RvActivity extends AppCompatActivity {

    @Bind(R.id.sRv)
    RecyclerView sRv;

    Context mContext;
    RCommonAdapter<DataUtil.DataItem> adapter;
    List<DataUtil.DataItem> dataItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv);
        ButterKnife.bind(this);
        mContext = this;

        sRv.setLayoutManager(new LinearLayoutManager(mContext));
        sRv.setAdapter(adapter = new RCommonAdapter<DataUtil.DataItem>(mContext, R.layout.item_recyclerview, dataItems) {
            @Override
            public void convert(ViewHolder holder, DataUtil.DataItem item) {
                String packageName = getBaseContext().getPackageName();
                Resources resources = getBaseContext().getResources();
                int resId = resources.getIdentifier("res_" + item.getIndex(), "mipmap", packageName);
                holder.setImageResource(R.id.imageView, resId);
                holder.setText(R.id.textView, item.getName());
            }
        });
        sRv.addOnItemTouchListener(new ROnItemTouchListener(mContext, sRv) {
            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        dataItems.addAll(DataUtil.getDataItems());
        adapter.notifyDataSetChanged();
    }

}
