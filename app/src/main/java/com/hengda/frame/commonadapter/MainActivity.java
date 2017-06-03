package com.hengda.frame.commonadapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.gvAndVp, R.id.sLv, R.id.mLv, R.id.sRv, R.id.mRv, R.id.sectionRv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gvAndVp:
                startActivity(new Intent(this, GvAndVpActivity.class));
                break;
            case R.id.sLv:
                startActivity(new Intent(this, LvActivity.class));
                break;
            case R.id.mLv:
                startActivity(new Intent(this, MultiItemLvActivity.class));
                break;
            case R.id.sRv:
                startActivity(new Intent(this, RvActivity.class));
                break;
            case R.id.mRv:
                startActivity(new Intent(this, MultiItemRvActivity.class));
                break;
            case R.id.sectionRv:
                startActivity(new Intent(this, SectionRvActivity.class));
                break;
        }
    }

}
