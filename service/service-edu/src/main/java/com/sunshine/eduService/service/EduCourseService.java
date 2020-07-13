package com.sunshine.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunshine.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunshine.eduService.entity.EduCourseDto;
import com.sunshine.eduService.query.CourseQuery;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author sunshine
 * @since 2020-06-24
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(EduCourseDto eduCourseDto);

    EduCourseDto getCourseInfoById(String id);

    void pageQuery(Page<EduCourse> coursePage, CourseQuery query);
}
