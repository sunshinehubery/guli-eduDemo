package com.sunshine.eduService.controller;


import com.sunshine.common.utils.R;
import com.sunshine.eduService.service.EduSubjectService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author sunshine
 * @since 2020-05-26
 */
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
        subjectService.importSubject(file);
        return R.ok();
    }
}

