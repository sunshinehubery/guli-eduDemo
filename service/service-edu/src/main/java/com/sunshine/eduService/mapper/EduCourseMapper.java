package com.sunshine.eduService.mapper;

import com.sunshine.eduService.entity.CoursePublishVo;
import com.sunshine.eduService.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author sunshine
 * @since 2020-06-24
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    CoursePublishVo selectCoursePublishVoById(String id);
}
