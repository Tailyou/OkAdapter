package com.hengda.frame.commonadapter;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.hengda.frame.commonadapter.utils.DataUtil;
import com.hengda.zwf.commonadapter.GCommonAdapter;
import com.hengda.zwf.commonadapter.VCommonAdapter;
import com.hengda.zwf.commonadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * GridView 结合 ViewPage 使用
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/2/3 14:02
 */
public class GvAndVpActivity extends AppCompatActivity {

    @Bind(R.id.vPager)
    ViewPager vPager;
    @Bind(R.id.ll_dot)
    LinearLayout llDot;

    Context mContext;
    LayoutInflater layoutInflater;
    List<View> viewList = new ArrayList<>();

    int pageCount;
    int pageSize = 8;
    int curIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gv_and_vp);
        ButterKnife.bind(this);
        mContext = this;
        renderData();
    }

    private void renderData() {
        List<DataUtil.DataItem> dataItems = DataUtil.getDataItems();
        pageCount = (int) Math.ceil(dataItems.size() * 1.0 / pageSize);
        for (int i = 0; i < pageCount; i++) {
            layoutInflater = getLayoutInflater();
            GridView gridView = new GridView(mContext);
            gridView.setNumColumns(4);
            gridView.setAdapter(new GCommonAdapter<DataUtil.DataItem>(mContext, R.layout.item_gridview,
                    dataItems, i, pageSize) {
                @Override
                public void convert(ViewHolder holder, DataUtil.DataItem item) {
                    String packageName = getBaseContext().getPackageName();
                    Resources resources = getBaseContext().getResources();
                    int resId = resources.getIdentifier("res_" + item.getIndex(), "mipmap", packageName);
                    holder.setImageResource(R.id.imageView, resId);
                    holder.setText(R.id.textView, item.getName());
                }
            });
            viewList.add(gridView);
        }
        vPager.setAdapter(new VCommonAdapter(viewList));
        setOvalLayout(layoutInflater);
    }

    public void setOvalLayout(LayoutInflater inflater) {
        for (int i = 0; i < pageCount; i++) {
            llDot.addView(inflater.inflate(R.layout.dot, null));
        }
        llDot.getChildAt(0).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);
        vPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageSelected(int position) {
                llDot.getChildAt(curIndex)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_unselected);
                llDot.getChildAt(position)
                        .findViewById(R.id.v_dot)
                        .setBackgroundResource(R.drawable.dot_selected);
                curIndex = position;
            }

            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

}
