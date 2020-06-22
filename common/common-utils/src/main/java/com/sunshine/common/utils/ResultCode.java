package com.sunshine.common.utils;

/**
 * @description: 定义返回码
 * @author: sunshine
 * @date: 2020/5/7 21:50
 * @Version: 1.0
 **/
public enum ResultCode {
    SUCCESS(20000,"操作成功"),
    ERROR(20001,"网络繁忙"),
    SAVE_DATA_ERROR(20002,"保存数据失败"),
    EXIST_ALREADY_DATA(20003,"数据已经存在"),
    ERROR_UPLOAD_FILE(21001,"文件上传失败"),
    ERROR_INPUT_FILE(21002,"导入文件异常"),
    SUCCESS_UPLOAD_FILE(21000,"导入课程成功"),
    ERROR_UPLOAD_PART_DATA(21003,"部分数据添加失败");

    private int code;
    private String msg;
    private ResultCode(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public int getCode(){
        return code;
    }
    public String getMsg(){
        return msg;
    }

}
