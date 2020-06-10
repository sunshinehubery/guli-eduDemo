package com.sunshine.eduService.controller;


import com.sunshine.common.utils.R;
import com.sunshine.common.utils.ResultCode;
import com.sunshine.eduService.entity.SubjectNestedVo;
import com.sunshine.eduService.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author sunshine
 * @since 2020-05-26
 */
@Api(description = "课程管理")
@CrossOrigin
@RestController
@RequestMapping("/eduService/subject")
public class EduSubjectController {

    private final EduSubjectService subjectService;

    @Autowired
    public EduSubjectController(EduSubjectService subjectService){
        this.subjectService = subjectService;
    }

    @ApiOperation(value = "通过导入excel添加课程")
    @PostMapping("importSubject")
    public R importSubject(@ApiParam(name = "file", value = "excel表格", required = true)
                           @RequestParam("file")MultipartFile file){
        final List<String> msg = subjectService.importSubject(file);
        if(msg.size() == 0){
            return R.ok(ResultCode.SUCCESS_UPLOAD_FILE);
        } else {
            return R.error(ResultCode.ERROR_UPLOAD_PART_DATA, msg);
        }
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("list")
    public R nestedList(){
        List<SubjectNestedVo> voList = subjectService.nestedList();
        return R.ok(voList);
    }

    @ApiOperation(value = "根据主键id删除课程")
    @DeleteMapping("{id}")
    public R removeById(@ApiParam(name = "id", value = "课程id", required = true)
                        @PathVariable String id){
        subjectService.removeById(id);
        return R.ok();
    }
}

