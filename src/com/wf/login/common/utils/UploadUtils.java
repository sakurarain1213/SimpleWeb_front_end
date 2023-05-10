package com.wf.login.common.utils;


import java.io.File;
import java.util.UUID;

public class UploadUtils {
    //使用UUID生成4唯一的标识码，拼接上图片的名字
    public static String NewFileName(String filename) {
        return UUID.randomUUID().toString().replaceAll("-", "") + "_" + filename;
    }
    //生成二级、三级目录
    public static String NewFilePath(String basepath, String filename) {
        int hashcode = filename.hashCode();
        int path1 = hashcode & 15;
        int path2 = (hashcode >> 4) & 15;
        String newpath = basepath + "\\" + path1 + "\\" + path2;
        File file = new File(newpath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return newpath;
    }
}
