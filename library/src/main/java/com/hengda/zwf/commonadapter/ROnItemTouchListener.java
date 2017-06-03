package com.hengda.zwf.commonadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public abstract class ROnItemTouchListener implements RecyclerView.OnItemTouchListener {

    GestureDetector mGestureDetector;

    public ROnItemTouchListener(Context context, final RecyclerView recyclerView) {
        mGestureDetector = new GestureDetector(context, new GestureDetector
                .SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if (childView != null) {
                    onItemLongClick(childView, recyclerView.getChildLayoutPosition(childView));
                }
            }
        });
    }

    public abstract void onItemClick(View view, int position);

    public abstract void onItemLongClick(View view, int position);

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if (childView != null && mGestureDetector.onTouchEvent(e)) {
            onItemClick(childView, rv.getChildLayoutPosition(childView));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

}
