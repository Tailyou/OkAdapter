package com.hengda.zwf.commonutil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.File;

public class ShareUtil {

    /**
     * 分享文字
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 15:08
     */
    public static void shareText(Context context, String activityTitle,
                                 String msgTitle, String msgText) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
        intent.putExtra(Intent.EXTRA_TEXT, msgText);
        context.startActivity(Intent.createChooser(intent, activityTitle));
    }

    /**
     * 分享图片
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 15:09
     */
    public static void sharePic(Context context, String activityTitle, File file) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        if (file != null && file.exists() && file.isFile()) {
            intent.setType("image/*");
            Uri u = Uri.fromFile(file);
            intent.putExtra(Intent.EXTRA_STREAM, u);
        }
        context.startActivity(Intent.createChooser(intent, activityTitle));
    }

    /**
     * 分享图片，重载
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 15:09
     */
    public static void sharePic(Context context, String activityTitle,
                                String imgPath) {
        File f = new File(imgPath);
        sharePic(context, activityTitle, f);
    }

}
