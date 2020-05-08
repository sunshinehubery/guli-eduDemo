package com.sunshine.eduService.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunshine.baseService.exception.GuliException;
import com.sunshine.common.utils.R;
import com.sunshine.eduService.entity.EduTeacher;
import com.sunshine.eduService.query.TeacherQuery;
import com.sunshine.eduService.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author sunshine
 * @since 2020-05-07
 */
@Api(description = "讲师管理")
@CrossOrigin
@RestController
@RequestMapping("/eduService/teacher")
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R list(){
        try {
            int i = 10/0;
        }catch (Exception e){
            throw new GuliException(2101,"自定义异常");
        }
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("item",list);
    }

    @ApiOperation(value = "删除讲师(逻辑删除)")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true)
                                  @PathVariable String id){
        teacherService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(@ApiParam(name = "page", value = "当前页", required = true)
                      @PathVariable Long page,
                      @ApiParam(name = "limit", value = "每页记录数", required = true)
                      @PathVariable Long limit,
                      @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                      @PathVariable TeacherQuery teacherQuery){
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        teacherService.pageQuery(teacherPage,teacherQuery);
        List<EduTeacher> teachers = teacherPage.getRecords();
        Long total = teacherPage.getTotal();
        return R.ok().data("total", total).data("rows", teachers);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(@ApiParam(name = "teacher", value = "讲师对象", required = true)
                  @RequestBody EduTeacher teacher){
        teacherService.save(teacher);
        return R.ok();
    }

    @ApiOperation(value = "根据讲师ID查询")
    @GetMapping("{id}")
    public R getById(@ApiParam(name = "id", value = "讲师ID", required = true)
                     @PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据讲师ID修改信息")
    @PutMapping("{id}")
    public R UpdateById(@ApiParam(name = "id", value = "讲师ID", required = true)
                        @PathVariable String id,
                        @ApiParam(name = "teacher", value = "讲师对象", required = true)
                        @RequestBody EduTeacher teacher){
        teacher.setId(id);
        teacherService.updateById(teacher);
        return R.ok();
    }

}

