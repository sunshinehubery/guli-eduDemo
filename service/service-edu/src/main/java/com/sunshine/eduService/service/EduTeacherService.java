package com.sunshine.eduService.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunshine.eduService.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunshine.eduService.query.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author sunshine
 * @since 2020-05-07
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> teacherPage, TeacherQuery query);
}
