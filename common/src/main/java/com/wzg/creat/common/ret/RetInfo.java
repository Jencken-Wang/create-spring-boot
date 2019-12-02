package com.wzg.creat.common.ret;

/**
 * @Description: 返回信息
 * @author wangzg_csd
 * @version 1.0
 * @createDate 2019/11/27 13:47
 * @Return
 */

public class RetInfo<T> {

    public static String SUCCESS = "SUCCESS";
    public static String FAIL = "FAIL";

    public String retMsg = "success";
    public String retCode = "SUCCESS";
    public T retData;

    public RetInfo(){};
    public RetInfo(String retCode, String retMsg){
        this.retCode = retCode;
        this.retMsg = retMsg;
    }
    public RetInfo(T retData){
        this.retData = retData;
    }

    public void setRetMsg(String msg){ this.retMsg = msg;}
    public void setRetData(T data){ this.retData = data;}
    public void setRetCode(String code){this.retCode = code;}

    public String getRetMsg(){ return this.retMsg; }
    public String getRetCode(){ return this.retCode; }
    public T getRetData(){ return this.retData; }
}
