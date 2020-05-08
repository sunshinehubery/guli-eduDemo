package com.sunshine.eduService.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel(value = "teacher的查询对象", description = "讲师查询条件的封装")
public class TeacherQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师名称|模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "yyyy-MM-dd HH:mm:ss")
    private String begin; //注意，这里使用的是String类型，前端传过来的数据无需进行类型转换

    @ApiModelProperty(value = "查询结束时间", example = "yyyy-MM-dd HH:mm:ss")
    private String end;
}
