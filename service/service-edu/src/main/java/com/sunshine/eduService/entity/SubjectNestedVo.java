package com.sunshine.eduService.entity;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: sunshine
 * @date: 2020/6/10 21:15
 * @Version: 1.0
 **/
@Data
public class SubjectNestedVo {
    private String id;

    private String title;

    private List<SubjectVo> subjectVoList;
}
