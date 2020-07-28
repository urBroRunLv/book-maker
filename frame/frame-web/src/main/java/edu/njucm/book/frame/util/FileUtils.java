package edu.njucm.book.frame.util;

import edu.njucm.book.common.constant.IPageConstant;
import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * 文件操作工具类
 * 
 * @author lvrongwang
 * @since 2020/5/25 18:44
 */
public class FileUtils implements IPageConstant {

    /**
     * 下载文件
     * 
     * @param response
     * @param filePath
     * @param modelPath
     * @param vmFile
     * @param context
     * @return
     */
    public static boolean downLoad(HttpServletResponse response, String filePath, String docFile, String modelPath,
            String vmFile, Context context) {
        try {
            VelocityEngine velocityEngine = new VelocityEngine();
            Properties p = new Properties();
            p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
            p.setProperty(velocityEngine.FILE_RESOURCE_LOADER_PATH, modelPath);
            velocityEngine.init(p);
            Template template = velocityEngine.getTemplate(vmFile);
            File fileDir = new File(filePath);
            if (!fileDir.exists()) {
                fileDir.mkdir();
            }
            File file = new File(filePath + docFile);
            Writer writer = new FileWriter(file);
            template.merge(context, writer);
            writer.flush();
            writer.close();
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            byte[] bytes = new byte[1024];
            int len = 0;
            response.reset();
            response.setCharacterEncoding("GB2312");
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(docFile, "UTF-8"));
            OutputStream out = response.getOutputStream();
            while ((len = inputStream.read(bytes)) > 0) {
                out.write(bytes, 0, len);
            }
            inputStream.close();
            out.close();
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
