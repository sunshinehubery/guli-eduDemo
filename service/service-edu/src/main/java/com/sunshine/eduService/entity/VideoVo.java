package com.sunshine.eduService.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2020/9/7 20:51
 * @Version: 1.0
 **/
@ApiModel(value = "章节信息")
@Data
public class VideoVo {
    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Boolean isFree;
}
