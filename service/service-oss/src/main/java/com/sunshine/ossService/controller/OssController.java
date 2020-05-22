package com.sunshine.ossService.controller;

import com.sunshine.common.utils.R;
import com.sunshine.common.utils.ResultCode;
import com.sunshine.ossService.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2020/5/17 21:03
 * @Version: 1.0
 **/
@Api(description="阿里云文件管理")
@RestController
@CrossOrigin
@RequestMapping("/oss/file")
public class OssController {
    @Autowired
    private OssService ossService;

    /**
     * 文件上传
     *
     * @param file
     */
    @ApiOperation(value = "文件上传")
    @PostMapping("upload")
    public R upload(@ApiParam(name = "file", value = "文件", required = true)
                    @RequestParam("file") MultipartFile file) {
        String uploadUrl = ossService.upload(file);
        //返回r对象
        return R.ok(ResultCode.SUCCESS.getCode(),"文件上传成功", uploadUrl);

    }
}
