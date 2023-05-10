package com.wf.login.common.utils;

import java.io.File;
import java.util.HashMap;

public class FileListUtils{
    public static void Filelist(File file, HashMap<String,String> fileMap){
        File[] files = file.listFiles();
        assert files != null;
        for(File f : files){
            if(f.isDirectory()){
                Filelist(f,fileMap);
            }else{
                String uuidname = f.getName();
                //获取文件真实上传的名字
                //由于上传时加上了uuid_filename,找到"_"的下标就可以截取出
                int index = uuidname.indexOf("_");
                String name = uuidname.substring(index+1);
                fileMap.put(uuidname,name);
            }
        }
    }
}
