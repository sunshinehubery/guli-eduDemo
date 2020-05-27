package com.sunshine.eduService.service.impl;

import com.sunshine.eduService.entity.EduSubject;
import com.sunshine.eduService.mapper.EduSubjectMapper;
import com.sunshine.eduService.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author sunshine
 * @since 2020-05-26
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void importSubject(MultipartFile file) {
        
    }
}
