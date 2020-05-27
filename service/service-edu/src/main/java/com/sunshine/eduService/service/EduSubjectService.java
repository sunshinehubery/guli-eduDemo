package com.sunshine.eduService.service;

import com.sunshine.eduService.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author sunshine
 * @since 2020-05-26
 */
public interface EduSubjectService extends IService<EduSubject> {

    List<String> importSubject(MultipartFile file);
}
