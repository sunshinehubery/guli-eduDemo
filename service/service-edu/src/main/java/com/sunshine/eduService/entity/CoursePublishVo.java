package com.sunshine.eduService.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2020/9/15 23:54
 * @Version: 1.0
 **/
@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo {
    private static final long serialVersionUID = 1L;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;//只用于显示
}
