package com.sunshine.eduService.service;

import com.sunshine.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunshine.eduService.entity.EduCourseDto;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author sunshine
 * @since 2020-06-24
 */
public interface EduCourseService extends IService<EduCourse> {

    void saveCourseInfo(EduCourseDto eduCourseDto);
}
