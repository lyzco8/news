package com.lsf;

import java.io.File;

/**
 * @author 刘愿
 * @date 2020/12/8 8:29
 * @see [相关类/方法]
 * @since V1.00
 */
public class Test {
    public static void main(String[] args) {
        try {
            String filePath = "out/artifacts/news_war_exploded/imgs/detail/11.jpg";
            File file = new File(filePath);
            if (file.exists()) {
                file.delete();
            } else {
                System.out.println("wu");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}