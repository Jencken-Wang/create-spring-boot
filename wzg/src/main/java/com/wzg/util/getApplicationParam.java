package com.wzg.util;

import java.util.ResourceBundle;

public class getApplicationParam {


    //获取配置文件参数
    public static String getString(String filePath, String key) {
        ResourceBundle basicConfig = ResourceBundle.getBundle(filePath);
        return basicConfig.getString(key);
    }
}
