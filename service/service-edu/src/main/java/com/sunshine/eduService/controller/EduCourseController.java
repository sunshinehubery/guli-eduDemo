package com.sunshine.eduService.controller;


import com.sunshine.common.utils.R;
import com.sunshine.eduService.entity.EduCourseDto;
import com.sunshine.eduService.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        eduCourseService.saveCourseInfo(eduCourseDto);
        return R.ok();
    }
}

