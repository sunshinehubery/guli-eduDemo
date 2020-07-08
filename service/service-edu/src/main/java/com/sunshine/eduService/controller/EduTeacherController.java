package com.sunshine.eduService.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sunshine.common.utils.R;
import com.sunshine.eduService.entity.EduTeacher;
import com.sunshine.eduService.query.TeacherQuery;
import com.sunshine.eduService.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author sunshine
 * @since 2020-05-07
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduService/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private RedisTemplate<String, Object> template;

    @GetMapping("/test")
    public R test(){
        ValueOperations<String, Object> ops = template.opsForValue();
        ops.set("strKey1", "hello spring boot redis",30, TimeUnit.SECONDS);
        String value = (String) ops.get("strKey1");
        return R.ok(value);
    }
    private final EduTeacherService teacherService;

    //java变量初始化：静态变量或静态语句块 =》实例变量或初始化语句块 =》构造方法 =》@Autowired
    @Autowired
    public EduTeacherController(EduTeacherService eduTeacherService){
        this.teacherService = eduTeacherService;
    }

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R list(){
        List<EduTeacher> list = teacherService.list(null);
        return R.ok(list);
    }

    @ApiOperation(value = "删除讲师(逻辑删除)")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "讲师ID", required = true)
                                  @PathVariable String id){
        boolean result = teacherService.removeByTeacherId(id);
        if(result) {
            return R.ok();
        }else {
            return R.error("删除失败");
        }
    }

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(@ApiParam(name = "page", value = "当前页", required = true)
                      @PathVariable Long page,
                      @ApiParam(name = "limit", value = "每页记录数", required = true)
                      @PathVariable Long limit,
                      @ApiParam(name = "teacherQuery", value = "查询对象")
                      TeacherQuery teacherQuery){
        Page<EduTeacher> teacherPage = new Page<>(page,limit);
        teacherService.pageQuery(teacherPage,teacherQuery);
        return R.ok(teacherPage);
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
        return R.ok(teacher);
    }

    @ApiOperation(value = "根据讲师ID修改信息")
    @PutMapping("/edit")
    public R UpdateById(@ApiParam(name = "teacher", value = "讲师对象", required = true)
                        @RequestBody EduTeacher teacher){
        teacherService.updateById(teacher);
        return R.ok();
    }

}

