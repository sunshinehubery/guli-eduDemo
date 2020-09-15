package com.sunshine.eduService.service;

import com.sunshine.eduService.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sunshine.eduService.entity.VideoInfoForm;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author sunshine
 * @since 2020-09-07
 */
public interface EduVideoService extends IService<EduVideo> {
    boolean getCountByChapterId(String chapterId);

    void saveVideo(VideoInfoForm videoInfoForm);

    VideoInfoForm getVideoInfoFormById(String id);

    void updateVideoInfoById(VideoInfoForm videoInfoForm);

    boolean removeVideoById(String id);
}
