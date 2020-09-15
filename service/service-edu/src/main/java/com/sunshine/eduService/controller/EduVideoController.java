package com.sunshine.eduService.controller;


import com.alibaba.fastjson.JSON;
import com.sunshine.common.utils.R;
import com.sunshine.common.utils.ResultCode;
import com.sunshine.eduService.entity.VideoInfoForm;
import com.sunshine.eduService.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.spring.web.json.Json;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author sunshine
 * @since 2020-09-07
 */
@Api(description = "课时管理")
@RestController
@CrossOrigin
@RequestMapping("/eduService/video")
public class EduVideoController {
    private static Logger logger = LoggerFactory.getLogger(EduVideoController.class);
    @Autowired
    private EduVideoService eduVideoService;

    @ApiOperation(value = "新增课时")
    @PostMapping("saveVideo")
    public R saveVideo(@ApiParam(name = "VideoInfoForm",value = "课时对象",required = true) @RequestBody VideoInfoForm videoInfoForm){
        try{
            eduVideoService.saveVideo(videoInfoForm);
            return R.ok();
        }catch (Exception e){
            logger.error("********EduVideoController.saveVideo error:{}",e.getMessage());
            return R.error(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id查询课时")
    @GetMapping("{id}")
    public R getVideoInfoFormById(@ApiParam(name = "id",value = "课时id",required = true) @PathVariable String id){
        try{
            logger.info("********EduVideoController.getVideoInfoFormById id:{}",id);
            if(StringUtils.isEmpty(id)){
                return R.error(ResultCode.ERROR);
            }
            VideoInfoForm videoInfoForm = eduVideoService.getVideoInfoFormById(id);
            return R.ok(videoInfoForm);
        }catch (Exception e){
            logger.error("********EduVideoController.getVideoInfoFormById error:{}",e.getMessage());
            return R.error(e.getMessage());
        }
    }

    @ApiOperation(value = "通过id更新课时")
    @PutMapping("{id}")
    public R updateVideoInfoById(@ApiParam(name = "videoInfoForm", value = "课时基本信息", required = true) @RequestBody VideoInfoForm videoInfoForm,
                                 @ApiParam(name = "id", value = "课时id", required = true) @PathVariable String id){
        try {
            logger.info("*************EduVideoController.updateVideoInfoById info:{},id:{}",JSON.toJSONString(videoInfoForm),id);
            eduVideoService.updateVideoInfoById(videoInfoForm);
            return R.ok();
        }catch (Exception e){
            logger.error("*************EduVideoController.updateVideoInfoById error:{}",e.getMessage());
            return R.error(e.getMessage());
        }
    }

    @ApiOperation(value = "根据id删除课时信息")
    @DeleteMapping("{id}")
    public R removeVideoById(@ApiParam(name = "id",value = "课时id", required = true) @PathVariable String id){
        try{
            boolean result = eduVideoService.removeVideoById(id);
            if(result){
                return R.ok("删除成功！");
            }else {
                return R.error("删除失败");
            }
        }catch (Exception e){
            logger.error("*************EduVideoController.removeVideoById error:{}",e.getMessage());
            return R.error(e.getMessage());
        }
    }
}

