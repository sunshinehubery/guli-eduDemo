package com.sunshine.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunshine.eduService.entity.EduTeacher;
import com.sunshine.eduService.mapper.EduTeacherMapper;
import com.sunshine.eduService.query.TeacherQuery;
import com.sunshine.eduService.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author sunshine
 * @since 2020-05-07
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> teacherPage, TeacherQuery query) {
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("sort");
        if(query == null){
            baseMapper.selectPage(teacherPage,wrapper);
            return;
        }
        String name = query.getName();
        Integer level = query.getLevel();
        String begin = query.getBegin();
        String end = query.getEnd();
        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        baseMapper.selectPage(teacherPage,wrapper);
    }

    @Override
    public boolean removeByTeacherId(String id) {
        Integer result = baseMapper.deleteById(id);
        return result > 0;
    }
}
