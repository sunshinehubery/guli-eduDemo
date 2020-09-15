package com.sunshine.eduService.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunshine.baseService.exception.GuliException;
import com.sunshine.common.utils.ResultCode;
import com.sunshine.eduService.entity.ChapterVo;
import com.sunshine.eduService.entity.EduChapter;
import com.sunshine.eduService.entity.EduVideo;
import com.sunshine.eduService.entity.VideoVo;
import com.sunshine.eduService.mapper.EduChapterMapper;
import com.sunshine.eduService.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sunshine.eduService.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author sunshine
 * @since 2020-09-07
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;

    @Override
    public List<ChapterVo> nestedList(String courseId) {
        // 获取章节信息(携带视频)
        List<ChapterVo> chapterVoList = new ArrayList<>();
        //获取章节信息
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        wrapper.orderByDesc("sort","id");
        List<EduChapter> chapters = baseMapper.selectList(wrapper);
        //获取视频信息
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        wrapperVideo.orderByDesc("sort","id");
        List<EduVideo> videos = videoService.list(wrapperVideo);
        //填充章节vo数据
        for(int i = 0;i < chapters.size();i++){
            ChapterVo vo = new ChapterVo();
            EduChapter chapter = chapters.get(i);
            BeanUtils.copyProperties(chapter,vo);
            //填充视频vo数据
            List<VideoVo> videoVos = new ArrayList<>();
            for(int j = 0;j < videos.size();j++){
                EduVideo video = videos.get(j);
                if(chapter.getId().equals(video.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video,videoVo);
                    videoVos.add(videoVo);
                }
                vo.setChildren(videoVos);
            }
            chapterVoList.add(vo);
        }
        return chapterVoList;
    }

    @Override
    public boolean removeChapterById(String id) {
        if(videoService.getCountByChapterId(id)){
            throw new GuliException(ResultCode.CHAPTER_EXIST_VIDEO);
        }
        Integer result = baseMapper.deleteById(id);
        return result > 0;
    }
}
