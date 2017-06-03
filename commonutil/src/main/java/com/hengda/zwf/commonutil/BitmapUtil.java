package com.hengda.zwf.commonutil;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BitmapUtil {

    /**
     * 根据指定宽高从resource加载图片
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:15
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }

    /**
     * 根据指定宽高从file加载图片
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:15
     */
    public static Bitmap decodeSampledBitmapFromFile(String pathName, int reqWidth, int reqHeight) {
        // First decode with inJustDecodeBounds=true to check dimensions
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(pathName, options);
        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(pathName, options);
    }

    /**
     * 计算缩放因子
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:16
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    /**
     * Bitmap缩放
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:07
     */
    public static Bitmap scaleBitmap(Bitmap bm, float scale) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap newBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newBitmap;
    }

    /**
     * Bitmap转圆角
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:07
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        Bitmap rcBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(rcBitmap);
        int color = 0xff424242;
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        float roundPx = pixels;
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return rcBitmap;
    }

    /**
     * 根据指定宽度缩放图片显示
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:11
     */
    public static void picFitSpecifyWidth(ImageView imageView, Bitmap bitmap, int specifyWidth) {
        int bWidth = bitmap.getWidth();
        int bHeight = bitmap.getHeight();
        int width = specifyWidth;
        int height = width * bHeight / bWidth;
        ViewGroup.LayoutParams para = imageView.getLayoutParams();
        para.width = width;
        para.height = height;
        imageView.setLayoutParams(para);
    }

    /**
     * 根据指定高度缩放图片显示
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:10
     */
    public static void picFitSpecifyHeight(ImageView imageView, Bitmap bitmap, int specifyHeight) {
        int bWidth = bitmap.getWidth();
        int bHeight = bitmap.getHeight();
        int height = specifyHeight;
        int width = height * bWidth / bHeight;
        ViewGroup.LayoutParams para = imageView.getLayoutParams();
        para.width = width;
        para.height = height;
        imageView.setLayoutParams(para);
    }

}
