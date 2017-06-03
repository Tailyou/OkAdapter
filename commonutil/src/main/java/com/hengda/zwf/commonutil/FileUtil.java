package com.hengda.zwf.commonutil;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具
 *
 * @author 祝文飞（Tailyou）
 * @time 2017/1/23 14:42
 */
public class FileUtil {

    public final static String FILE_EXTENSION_SEPARATOR = ".";

    private FileUtil() {
        throw new AssertionError();
    }

    /**
     * 从指定文件下读取字符串
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:29
     */
    public static StringBuilder readStringFromFile(String filePath, String charset) {
        File file = new File(filePath);
        StringBuilder fileContent = new StringBuilder("");
        if (file == null || !file.isFile()) {
            return null;
        }
        BufferedReader reader = null;
        try {
            InputStreamReader is = new InputStreamReader(new FileInputStream(file), charset);
            reader = new BufferedReader(is);
            String line;
            while ((line = reader.readLine()) != null) {
                if (!fileContent.toString().equals("")) {
                    fileContent.append("\r\n");
                }
                fileContent.append(line);
            }
            return fileContent;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            IOUtil.close(reader);
        }
    }

    /**
     * 将字符串写到指定文件
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:29
     */
    public static boolean writeStringToFile(String filePath, String content, boolean append) {
        if (TextUtils.isEmpty(content)) {
            return false;
        }
        FileWriter fileWriter = null;
        try {
            makeDirs(filePath);
            fileWriter = new FileWriter(filePath, append);
            fileWriter.write(content);
            return true;
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            IOUtil.close(fileWriter);
        }
    }

    /**
     * 将输入流写到指定文件
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:30
     */
    public static boolean writeInputStreamToFile(File file, InputStream stream, boolean append) {
        OutputStream o = null;
        try {
            makeDirs(file.getAbsolutePath());
            o = new FileOutputStream(file, append);
            byte data[] = new byte[1024];
            int length;
            while ((length = stream.read(data)) != -1) {
                o.write(data, 0, length);
            }
            o.flush();
            return true;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            IOUtil.close(o);
            IOUtil.close(stream);
        }
    }

    /**
     * 移动文件
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:31
     */
    public static void moveFile(String sourceFilePath, String destFilePath) {
        if (TextUtils.isEmpty(sourceFilePath) || TextUtils.isEmpty(destFilePath)) {
            throw new RuntimeException("Both sourceFilePath and destFilePath cannot be null.");
        }
        moveFile(new File(sourceFilePath), new File(destFilePath));
    }

    /**
     * 移动文件
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:31
     */
    public static void moveFile(File srcFile, File destFile) {
        boolean rename = srcFile.renameTo(destFile);
        if (!rename) {
            copyFile(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
            deleteFile(srcFile.getAbsolutePath());
        }
    }

    /**
     * 复制文件
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:33
     */
    public static boolean copyFile(String sourceFilePath, String destFilePath) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(sourceFilePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        }
        return writeInputStreamToFile(new File(destFilePath), inputStream, false);
    }

    /**
     * 获取文件名，带文件类型后缀
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:33
     */
    public static String getFileName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }
        int filePos = filePath.lastIndexOf(File.separator);
        return (filePos == -1) ? filePath : filePath.substring(filePos + 1);
    }

    /**
     * 获取文件名，不带文件类型后缀
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:34
     */
    public static String getFileNameWithoutExtension(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }
        int extentPos = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePos = filePath.lastIndexOf(File.separator);
        if (filePos == -1) {
            return (extentPos == -1 ? filePath : filePath.substring(0, extentPos));
        }
        if (extentPos == -1) {
            return filePath.substring(filePos + 1);
        }
        return (filePos < extentPos ? filePath.substring(filePos + 1, extentPos) :
                filePath.substring(filePos + 1));
    }

    /**
     * 获取文件类型后缀
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:36
     */
    public static String getFileExtension(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }
        int extPos = filePath.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        int filePos = filePath.lastIndexOf(File.separator);
        if (extPos == -1) {
            return "";
        }
        return (filePos >= extPos) ? "" : filePath.substring(extPos + 1);
    }

    /**
     * 获取文件夹路径
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:37
     */
    public static String getFolderName(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return filePath;
        }
        int filePos = filePath.lastIndexOf(File.separator);
        return (filePos == -1) ? "" : filePath.substring(0, filePos);
    }

    /**
     * 创建文件目录
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:38
     */
    public static boolean makeDirs(String filePath) {
        String folderName = getFolderName(filePath);
        if (TextUtils.isEmpty(folderName)) {
            return false;
        }
        File folder = new File(folderName);
        return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
    }

    /**
     * 根据路径判断文件是否存在
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:38
     */
    public static boolean isFileExist(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        }
        File file = new File(filePath);
        return (file.exists() && file.isFile());
    }

    /**
     * 判断目录是否存在
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:39
     */
    public static boolean isDirExist(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return false;
        }
        File dire = new File(dirPath);
        return (dire.exists() && dire.isDirectory());
    }

    /**
     * 根据指定路径删除文件
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:40
     */
    public static boolean deleteFile(String path) {
        if (TextUtils.isEmpty(path)) {
            return true;
        }
        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        return file.delete();
    }

    /**
     * 获取指定目录下，指定类型的所有文件
     *
     * @author 祝文飞（Tailyou）
     * @time 2017/1/23 14:41
     */
    private static List<File> getFilesByExtension(String path, String extension) {
        File file = new File(path);
        List<File> fileList = new ArrayList<>();
        File[] array = file.listFiles();
        for (int i = 0; i < array.length; i++) {
            if (array[i].isFile() && array[i].getName().endsWith(extension)) {
                fileList.add(array[i]);
            }
        }
        return fileList;
    }

}