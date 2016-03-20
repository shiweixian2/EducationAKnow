package com.bestcode95.educationaknow.utils.view;

import android.os.Environment;
import android.util.Log;

import java.io.File;

public class PathUtil {

    /**
     * 创建文件夹
     * <p/>
     * 并 获取基本应用的基本文件夹的路径
     *
     * @return
     */
    public String getBasePath() {
        String basePath = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + "/ALittleLove/";
        File baseDir = new File(basePath);
        if (!baseDir.exists()) {
            baseDir.mkdirs();
            Log.d("PathUtil", "文件在正在创建");
        } else {
            Log.d("PathUtil", "文件夹存在");
        }
        return basePath;
    }

    /**
     * 创建文件夹
     * <p/>
     * 并 获取我的界面文件夹的路径
     *
     * @return
     */
    public String getMinePath() {
        String path = getBasePath() + "Mine/";
        File mineDir = new File(path);
        if (!mineDir.exists()) {
            mineDir.mkdirs();
        }
        return path;
    }
}
