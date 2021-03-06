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
    SAVE_COURSE_ERROR(20004, "课程信息保存失败"),
    SAVE_COURSE_DESCRIPTION_ERROR(20005, "课程详情信息保存失败"),
    ERROR_DATA_NULL(20006, "数据不存在"),
    UPDATE_COURSE_ERROR(20007, "课程信息更新失败"),
    UPDATE_COURSE_DESCRIPTION_ERROR(20008, "课程详情信息更新失败"),
    CHAPTER_EXIST_VIDEO(20009, "该章节下存在视频教程，请先删除视频教程"),
    SAVE_VIDEO_ERROR(20010, "课时信息保存失败"),
    UPDATE_VIDEO_ERROR(20011,"课时信息更新失败"),
    DELETE_VIDEO_ERROR(20012,"删除视频失败"),
    DELETE_CHAPTER_ERROR(20013,"删除章节信息失败"),
    DELETE_COURSE_DESCRIPTION_ERROR(20014,"删除课程详情信息失败"),
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
