package com.sunshine.eduService.controller;


import com.sunshine.common.utils.R;
import com.sunshine.common.utils.ResultCode;
import com.sunshine.eduService.entity.ChapterVo;
import com.sunshine.eduService.entity.EduChapter;
import com.sunshine.eduService.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author sunshine
 * @since 2020-09-07
 */
@Api(description = "课程章节管理")
@RestController
@CrossOrigin
@RequestMapping("/eduService/chapter")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "嵌套章节数据列表")
    @RequestMapping(value = "/nestedList/{courseId}",method = RequestMethod.GET)
    public R nestedListByCourseId(@ApiParam(name = "courseId",value = "课程id",required = true) @PathVariable String courseId){
        List<ChapterVo> chapterVoList = eduChapterService.nestedList(courseId);
        return R.ok(chapterVoList);
    }

    @ApiOperation(value = "新增章节")
    @PostMapping
    public R saveChapter(@ApiParam(name = "eduChapter",value = "章节对象",required = true) @RequestBody EduChapter eduChapter){
        eduChapterService.save(eduChapter);
        return R.ok();
    }

    @ApiOperation(value = "根据id查询章节信息")
    @GetMapping("/{id}")
    public R getChapterById(@ApiParam(name = "id",value = "章节id",required = true) @PathVariable String id){
        EduChapter eduChapter = eduChapterService.getById(id);
        return R.ok(eduChapter);
    }

    @ApiOperation(value = "根据id修改章节")
    @PutMapping("/{id}")
    public R updateChapterById(@ApiParam(name = "id",value = "章节id",required = true) @PathVariable String id,
                               @ApiParam(name = "eduChapter", value = "章节对象",required = true) @RequestBody EduChapter eduChapter){
        eduChapter.setId(id);
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

    @ApiOperation(value = "根据id删除章节")
    @DeleteMapping("/{id}")
    public R removeChapterById(@ApiParam(name = "id",value = "id",required = true) @PathVariable String id){
        boolean result = eduChapterService.removeChapterById(id);
        if(result){
            return R.ok();
        }else{
            return R.error(ResultCode.CHAPTER_EXIST_VIDEO);
        }
    }
}

