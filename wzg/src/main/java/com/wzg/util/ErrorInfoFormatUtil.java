package com.wzg.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 错误信息转换为字符串工具
 */
public class ErrorInfoFormatUtil {


    /**
     * @description: 完整错误信息转字符串
     * @param: Exception e
     * @return: Stirng
     * @author wangzg
     * @date: 2020/12/25 16:32
     */
    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String exception =  "\r\n" + sw.toString() + "\r\n";
            sw.close();
            pw.close();
            return exception;
        } catch (Exception e2) {
            return "ErrorInfoFromException";
        }
    }
}
