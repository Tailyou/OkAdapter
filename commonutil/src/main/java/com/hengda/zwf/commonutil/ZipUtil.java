package com.hengda.zwf.commonutil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtil {

    /**
     * 解压-带解压完成回调
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 15:17
     */
    public static void unzip(File zipFile, String unzipToDirPath, IUnzipCallback callback) throws Exception {
        ZipInputStream inZip = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry zipEntry;
        String szName;
        while ((zipEntry = inZip.getNextEntry()) != null) {
            szName = zipEntry.getName();
            if (zipEntry.isDirectory()) {
                szName = szName.substring(0, szName.length() - 1);
                File folder = new File(unzipToDirPath + File.separator + szName);
                folder.mkdirs();
            } else {
                File file = new File(unzipToDirPath + File.separator + szName);
                file.createNewFile();
                FileOutputStream out = new FileOutputStream(file);
                int len;
                byte[] buffer = new byte[1024];
                while ((len = inZip.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                    out.flush();
                }
                out.close();
            }
        }
        callback.completed();
        zipFile.delete();
        inZip.close();
    }

    public interface IUnzipCallback {
        void completed();
    }

}
