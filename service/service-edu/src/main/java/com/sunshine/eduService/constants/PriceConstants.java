package com.sunshine.eduService.constants;

import java.math.BigDecimal;

/**
 * @description:
 * @author: sunshinehubery
 * @date: 2020/7/5 21:21
 * @Version: 1.0
 **/
public class PriceConstants {
    public static final int STORE_SCALE = 4; //存储精度
    public static final int CAL_SCALE = 8; //运算精度
    public static final int DISPLAY_SCALE = 2; //显示精度
    public static final BigDecimal ZERO = new BigDecimal("0.0000"); //系统级别的0
}
