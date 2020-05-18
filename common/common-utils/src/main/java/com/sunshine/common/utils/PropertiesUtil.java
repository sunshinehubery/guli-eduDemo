package com.sunshine.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


/**
 * @description:
 * @author: sunshine
 * @date: 2020/5/17 12:01
 * @Version: 1.0
 **/
public class PropertiesUtil {
    private  static  Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    private static Properties props;
    ///代码块执行顺序：静态代码块>普通代码块>构造代码块
    //构造代码块每次都执行，但是静态代码块只执行一次
    static {
        String fileName = "configuration.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        } catch (IOException e) {
            logger.error("配置文件读取异常",e);
        }
    }
    //自定义俩个get方法，方便调用工具类读取properties文件的属性
    public static String getProperty(String key){
        String value= props.getProperty(key.trim());
        if (StringUtils.isEmpty(value)){
            return null;
        }
        return value.trim();
    }
    // 先获取properties，若没有使用defaultValue
    public static String getProperty(String key,String defaultValue){
        String value= props.getProperty(key.trim());
        if (StringUtils.isEmpty(value)){
            value = defaultValue;
        }
        return value.trim();
    }
}
