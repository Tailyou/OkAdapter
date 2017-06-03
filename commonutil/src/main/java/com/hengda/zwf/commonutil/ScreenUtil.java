package com.hengda.zwf.commonutil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;

public class ScreenUtil {

    private int screenW;
    private int screenH;
    private float screenDensity;

    private volatile static ScreenUtil sInstance;

    private ScreenUtil(Context mContext) {
        DisplayMetrics metric = mContext.getResources()
                .getDisplayMetrics();
        screenW = metric.widthPixels;
        screenH = metric.heightPixels;
        screenDensity = metric.density;
    }

    public static ScreenUtil getInstance(Context mContext) {
        if (sInstance == null) {
            synchronized (ScreenUtil.class) {
                if (sInstance == null) {
                    sInstance = new ScreenUtil(mContext);
                }
            }
        }
        return sInstance;
    }

    /**
     * 获取屏幕宽度
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:44
     */
    public int getScreenW() {
        return screenW;
    }

    /**
     * 获取屏幕高度
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:44
     */
    public int getScreenH() {
        return screenH;
    }

    /**
     * 获取屏幕密度
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:45
     */
    public float getScreenDensity() {
        return screenDensity;
    }

    /**
     * 获取状态栏高度
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:45
     */
    public static int getStatusHeight(Context context) {
        int statusHeight = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 截屏，不包含系统栏
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:45
     */
    public Bitmap snapShotPure(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmapFull = view.getDrawingCache();

        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        Bitmap bitmap = Bitmap.createBitmap(bitmapFull, 0, frame.top, screenW,
                frame.bottom - frame.top);
        view.destroyDrawingCache();
        return bitmap;
    }

    /**
     * 截屏，包含系统栏
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:45
     */
    public Bitmap snapShotFull(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmapFull = view.getDrawingCache();
        return bitmapFull;
    }

}
