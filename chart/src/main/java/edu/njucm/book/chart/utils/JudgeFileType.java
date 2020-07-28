package edu.njucm.book.chart.utils;

import java.util.Arrays;
import java.util.List;

public class JudgeFileType {
    private static final List<String> PICTURE_TYPE= Arrays.asList("image/tiff","application/postscript",
            "application/x-bmp","application/x-cdr","application/x-dxf","application/x-emf","application/x-ps",
            "application/postscript","image/gif","image/x-icon","application/x-ico","image/jpeg","application/x-pcx",
            "image/png","application/x-png","text/xml","application/x-tga","image/tiff","application/x-tif",
            "application/x-wmf");
    private static final List<String> TABLE_TYPE= Arrays.asList("application/vnd.ms-excel",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.template",
            "application/vnd.ms-excel.sheet.macroEnabled.12","application/vnd.ms-excel.template.macroEnabled.12",
            "application/vnd.ms-excel.addin.macroEnabled.12","application/vnd.ms-excel.sheet.binary.macroEnabled.12");

    public static Short judge(String contentType){
        if(PICTURE_TYPE.contains(contentType))
            return 1;
        if(TABLE_TYPE.contains(contentType))
            return 2;
        return null;
    }

}
