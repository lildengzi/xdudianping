package com.example.sens.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 常量类
 */
public class CommonConstant {

    public static String UPLOADS_PATH = "D:\\upload\\";
//    public static String UPLOADS_PATH = "D:\\upload\\";
//    云数据库过期了，用本地上传。
    public static List<String> DISH_NOTICE_TYPE = new ArrayList<>();

    static {
        DISH_NOTICE_TYPE.add("特价菜品");
        DISH_NOTICE_TYPE.add("优惠套餐");
    }


    public static final int SUMMARY_LENGTH = 100;

}
