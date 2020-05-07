package com.sunshine.eduService.controller;


import com.sunshine.common.utils.R;
import com.sunshine.eduService.entity.EduTeacher;
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
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("item",list);
    }

    @ApiOperation(value = "删除讲师(逻辑删除)")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true)
                                  @PathVariable String id){
        boolean flag = teacherService.removeById(id);
        if(flag) {
            return R.ok();
        }else{
            return R.error();
        }
    }

}

