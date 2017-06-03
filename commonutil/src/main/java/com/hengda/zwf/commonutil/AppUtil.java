package com.hengda.zwf.commonutil;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

public class AppUtil {

    private AppUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 获取App Name
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:04
     */
    public static String getAppName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            return context.getResources().getString(labelRes);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Version Name
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:04
     */
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取Version Code
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:04
     */
    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                    context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 根据apk路径安装apk
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 13:55
     */
    public static void installApk(Context context, String apkPath) {
        Intent installIntent = new Intent(Intent.ACTION_VIEW);
        installIntent.setDataAndType(
                Uri.parse("file://" + apkPath),
                "application/vnd.android.package-archive");
        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(installIntent);
    }

    /**
     * 根据包名检查是否安装
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 13:55
     */
    public static boolean checkInstallByPkgName(Context context, String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    /**
     * 根据包名启动App
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 13:54
     */
    public static void startAppWithPkgName(Context context, String packageName) {
        PackageInfo packageinfo = null;
        try {
            packageinfo = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return;
        }
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);
        List<ResolveInfo> resolveInfoList = context.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);
        ResolveInfo resolveinfo = resolveInfoList.iterator().next();
        if (resolveinfo != null) {
            String pcgName = resolveinfo.activityInfo.packageName;
            String className = resolveinfo.activityInfo.name;
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            ComponentName cn = new ComponentName(pcgName, className);
            intent.setComponent(cn);
            context.startActivity(intent);
        }
    }

}
