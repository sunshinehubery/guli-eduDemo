package com.sunshine.eduService.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunshine.common.utils.R;
import com.sunshine.eduService.entity.EduCourse;
import com.sunshine.eduService.entity.EduCourseDto;
import com.sunshine.eduService.query.CourseQuery;
import com.sunshine.eduService.service.EduCourseService;
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
 * @since 2020-06-24
 */
@Api(description = "课程科目详情管理")
@CrossOrigin
@RestController
@RequestMapping("/eduService/course")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation(value = "新增课程")
    @PostMapping("save-course-info")
    public R saveCourseInfo(@ApiParam(name = "EduCourseDto", value = "课程基本信息", required = true)
                                @RequestBody EduCourseDto eduCourseDto){
        String courseId = eduCourseService.saveCourseInfo(eduCourseDto);
        return R.ok(courseId);
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("courseInfo/{id}")
    public R getCourseInfoById(@ApiParam(name="id", value = "课程id", required = true)
                               @PathVariable String id){
        EduCourseDto eduCourseDto = eduCourseService.getCourseInfoById(id);
        return R.ok(eduCourseDto);
    }

    @ApiOperation(value = "分页课程列表")
    @GetMapping("{page}/{limit}")
    public R pageList(@ApiParam(name = "page", value = "当前页", required = true)
                      @PathVariable Long page,
                      @ApiParam(name = "limit", value = "每页记录数", required = true)
                      @PathVariable Long limit,
                      @ApiParam(name = "courseQuery", value = "查询对象")
                      CourseQuery courseQuery){
        Page<EduCourse> coursePage = new Page<>(page, limit);
        eduCourseService.pageQuery(coursePage, courseQuery);
        return R.ok(coursePage);
    }

    @ApiOperation(value = "根据ID删除课程")
    @RequestMapping("{id}")
    public R removeById(@ApiParam(name = "id",value = "课程id", required = true) @PathVariable String id){
        // todo 首先删除video记录，然后删除chapter记录，最后删除Course记录
        return R.ok();
    }
}

