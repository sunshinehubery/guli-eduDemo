package com.sunshine.eduService.service;

import com.sunshine.eduService.entity.ChapterVo;
import com.sunshine.eduService.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author sunshine
 * @since 2020-09-07
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> nestedList(String courseId);

    boolean removeChapterById(String id);

    boolean removeChapterByCourseId(String courseId);
}
