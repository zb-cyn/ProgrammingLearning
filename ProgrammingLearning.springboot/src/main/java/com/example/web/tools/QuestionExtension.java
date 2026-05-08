package com.example.web.tools;

import java.util.Arrays;
import java.util.List;

public class QuestionExtension {
    /**
     * 把字符串转换成列表,分割的
     *
     * @param value
     * @return
     */
    public static List<String> StringToList(String value) {
        String[] strArray = value.split(",");
        Arrays.sort(strArray);
        return Arrays.asList(strArray);
    }

    /**
     * 把字符串进行排序
     *
     * @param value
     * @return
     */
    public static String StringSort(String value) {
        String[] strArray = value.split(",");
        Arrays.sort(strArray);
        return String.join(",", strArray);
    }
}
