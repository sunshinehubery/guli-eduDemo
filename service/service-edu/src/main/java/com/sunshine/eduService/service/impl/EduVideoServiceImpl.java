package com.sunshine.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunshine.baseService.exception.GuliException;
import com.sunshine.common.utils.ResultCode;
import com.sunshine.eduService.entity.EduVideo;
import com.sunshine.eduService.entity.VideoInfoForm;
import com.sunshine.eduService.mapper.EduVideoMapper;
import com.sunshine.eduService.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author sunshine
 * @since 2020-09-07
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Override
    public boolean getCountByChapterId(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        Integer count = baseMapper.selectCount(wrapper);
        return count != null && count > 0;
    }

    @Override
    public void saveVideo(VideoInfoForm videoInfoForm) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm,eduVideo);
        boolean result = this.save(eduVideo);
        if(!result){
            throw new GuliException(ResultCode.SAVE_VIDEO_ERROR);
        }
    }

    @Override
    public VideoInfoForm getVideoInfoFormById(String id) {
        EduVideo eduVideo = this.getById(id);
        if(eduVideo == null){
            throw new GuliException(ResultCode.ERROR_DATA_NULL);
        }
        VideoInfoForm videoInfoForm = new VideoInfoForm();
        BeanUtils.copyProperties(eduVideo,videoInfoForm);
        return videoInfoForm;
    }

    @Override
    public void updateVideoInfoById(VideoInfoForm videoInfoForm) {
        EduVideo eduVideo = new EduVideo();
        BeanUtils.copyProperties(videoInfoForm,eduVideo);
        boolean result = this.updateById(eduVideo);
        if(!result){
            throw new GuliException(ResultCode.UPDATE_VIDEO_ERROR);
        }
    }

    @Override
    public boolean removeVideoById(String id) {
        Integer result = baseMapper.deleteById(id);
        return result > 0;
    }

    @Override
    public boolean removeVideoByCourseId(String courseId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        int result = baseMapper.delete(wrapper);
        return result > 0;
    }
}
