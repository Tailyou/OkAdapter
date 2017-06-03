package com.hengda.zwf.commonutil;

import android.os.Environment;

import java.io.File;

public class SDCardUtil {

    private SDCardUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SD卡是否可用
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:46
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取外部存储根目录，sd卡
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:46
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取系统根目录
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 15:00
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

}
