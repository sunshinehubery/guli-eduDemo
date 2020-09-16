package com.sunshine.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunshine.eduService.entity.EduCourseDescription;
import com.sunshine.eduService.mapper.EduCourseDescriptionMapper;
import com.sunshine.eduService.service.EduCourseDescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author sunshine
 * @since 2020-06-24
 */
@Service
public class EduCourseDescriptionServiceImpl extends ServiceImpl<EduCourseDescriptionMapper, EduCourseDescription> implements EduCourseDescriptionService {

    @Override
    public boolean removeCourseDescriptionByCourseId(String courseId) {
        QueryWrapper<EduCourseDescription> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        int result = baseMapper.delete(wrapper);
        return result > 0;
    }
}
