package edu.njucm.book.common.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * @author lvrongwang
 * @since 2020/5/26 23:47
 */
public class TxtUtils {

    /**
     * 转换txt文件为字符串
     * 
     * @param file
     * @return
     */
    public static String tranTxt2String(File file) {
        StringBuilder result = new StringBuilder();
        try {
            InputStreamReader inputStreamReader =
                    new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String s;
            while ((s = br.readLine()) != null) {
                result.append(System.lineSeparator()).append(s);
            }
            br.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
