package com.sunshine.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunshine.baseService.exception.GuliException;
import com.sunshine.common.utils.ResultCode;
import com.sunshine.eduService.constants.CourseConstants;
import com.sunshine.eduService.constants.PriceConstants;
import com.sunshine.eduService.entity.*;
import com.sunshine.eduService.mapper.EduCourseMapper;
import com.sunshine.eduService.query.CourseQuery;
import com.sunshine.eduService.service.EduChapterService;
import com.sunshine.eduService.service.EduCourseDescriptionService;
import com.sunshine.eduService.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunshine.eduService.service.EduVideoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;

    @Override
    public String saveCourseInfo(EduCourseDto eduCourseDto) {
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        // 新增课程数据
        if(StringUtils.isEmpty(eduCourseDto.getId())) {
            // 保存课程基本信息
            eduCourse.setStatus(CourseConstants.COURSE_DRAFT);
            BeanUtils.copyProperties(eduCourseDto, eduCourse);
            boolean resultCourse = this.save(eduCourse);
            if (!resultCourse) {
                throw new GuliException(ResultCode.SAVE_COURSE_ERROR);
            }
            // 保存课程详情信息
            eduCourseDescription.setCourseId(eduCourse.getId());
            eduCourseDescription.setDescription(eduCourseDto.getDescription());
            boolean resultDescription = eduCourseDescriptionService.save(eduCourseDescription);
            if (!resultDescription) {
                throw new GuliException(ResultCode.SAVE_COURSE_DESCRIPTION_ERROR);
            }
        }else {
            // 更新课程基本信息
            eduCourse.setStatus(CourseConstants.COURSE_DRAFT);
            BeanUtils.copyProperties(eduCourseDto, eduCourse);
            boolean resultCourse = this.updateById(eduCourse);
            if (!resultCourse) {
                throw new GuliException(ResultCode.UPDATE_COURSE_ERROR);
            }
            // 更新课程详情信息
            eduCourseDescription.setCourseId(eduCourse.getId());
            eduCourseDescription.setDescription(eduCourseDto.getDescription());
            QueryWrapper<EduCourseDescription> wrapper = new QueryWrapper<>();
            wrapper.eq("course_id", eduCourse.getId());
            boolean resultDescription = eduCourseDescriptionService.update(eduCourseDescription, wrapper);
            if (!resultDescription) {
                throw new GuliException(ResultCode.UPDATE_COURSE_DESCRIPTION_ERROR);
            }
        }
        return eduCourse.getId();
    }

    @Override
    public EduCourseDto getCourseInfoById(String id) {
        EduCourse eduCourse = this.getById(id);
        if(eduCourse == null){
            throw new GuliException(ResultCode.ERROR_DATA_NULL);
        }
        EduCourseDto eduCourseDto = new EduCourseDto();
        BeanUtils.copyProperties(eduCourse, eduCourseDto);
        //设置显示精度：舍弃多余的位数
        eduCourseDto.setPrice(eduCourseDto.getPrice().setScale(PriceConstants.DISPLAY_SCALE, BigDecimal.ROUND_FLOOR));
        QueryWrapper<EduCourseDescription> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", eduCourse.getId());
        EduCourseDescription description = eduCourseDescriptionService.getOne(wrapper);
        eduCourseDto.setDescription(description.getDescription());
        return eduCourseDto;
    }

    @Override
    public void pageQuery(Page<EduCourse> coursePage, CourseQuery query) {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("gmt_create");
        if(query == null){
            baseMapper.selectPage(coursePage, wrapper);
            return;
        }
        if (!StringUtils.isEmpty(query.getTitle())){
            wrapper.like("title", query.getTitle());
        }
        if(!StringUtils.isEmpty(query.getTeacherId())){
            wrapper.eq("teacher_id", query.getTeacherId());
        }
        if(!StringUtils.isEmpty(query.getSubjectParentId())){
            wrapper.eq("subject_parent_id", query.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(query.getSubjectId())){
            wrapper.eq("subject_id", query.getSubjectId());
        }
        baseMapper.selectPage(coursePage, wrapper);
    }

    @Override
    public boolean removeById(String courseId) {
        boolean result = false;
        //首先删除video记录，然后删除chapter记录，最后删除Course记录
        result = eduVideoService.removeVideoByCourseId(courseId);
        if(result){
            result = eduChapterService.removeChapterByCourseId(courseId);
            if(result){
                result = eduCourseDescriptionService.removeCourseDescriptionByCourseId(courseId);
                if(result){
                    int count = baseMapper.deleteById(courseId);
                    return count > 0;
                }else {
                    throw new GuliException(ResultCode.DELETE_COURSE_DESCRIPTION_ERROR);
                }
            }else {
                throw new GuliException(ResultCode.DELETE_CHAPTER_ERROR);
            }
        }else {
            throw new GuliException(ResultCode.DELETE_VIDEO_ERROR);
        }
    }

    @Override
    public CoursePublishVo selectCoursePublishVoById(String id) {
        return baseMapper.selectCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(String id) {
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus(CourseConstants.COURSE_NORMAL);
        Integer result = baseMapper.updateById(eduCourse);
        return result > 0;
    }
}
