package com.sunshine.eduService.service.impl;

import com.sunshine.baseService.exception.GuliException;
import com.sunshine.common.utils.ResultCode;
import com.sunshine.eduService.constants.CourseConstants;
import com.sunshine.eduService.entity.EduCourse;
import com.sunshine.eduService.entity.EduCourseDescription;
import com.sunshine.eduService.entity.EduCourseDto;
import com.sunshine.eduService.mapper.EduCourseMapper;
import com.sunshine.eduService.service.EduCourseDescriptionService;
import com.sunshine.eduService.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author sunshine
 * @since 2020-06-24
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public void saveCourseInfo(EduCourseDto eduCourseDto) {
        // 保存课程基本信息
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus(CourseConstants.COURSE_DRAFT);
        BeanUtils.copyProperties(eduCourseDto,eduCourse);
        boolean resultCourse = this.save(eduCourse);
        if(!resultCourse){
            throw new GuliException(ResultCode.SAVE_COURSE_ERROR);
        }
        // 保存课程详情信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(eduCourseDto.getDescription());
        boolean resultDescription = eduCourseDescriptionService.save(eduCourseDescription);
        if(!resultDescription){
            throw new GuliException(ResultCode.SAVE_COURSE_DESCRIPTION_ERROR);
        }
    }
}
