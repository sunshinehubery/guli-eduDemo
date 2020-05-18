package com.sunshine.ossService.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2020/5/17 20:49
 * @Version: 1.0
 **/
public interface OssService {
    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}
