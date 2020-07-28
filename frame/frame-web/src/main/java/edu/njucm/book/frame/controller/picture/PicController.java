package edu.njucm.book.frame.controller.picture;

import edu.njucm.book.chart.entity.PicContent;
import edu.njucm.book.chart.service.PicContentService;
import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.user.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

import static edu.njucm.book.common.util.PicUtils.getPicBase64Str;
import static java.util.Objects.nonNull;

/**
 * @author lvrongwang
 * @since 2020/4/18 17:23
 */
@RestController
@RequestMapping()
public class PicController extends BaseController implements IPageConstant {

    private static Logger logger = LoggerFactory.getLogger(PicController.class);

    @Autowired
    private PicContentService picContentService;

    /**
     * 设置图片返回头
     */
    private void responseHeader(HttpServletResponse response) {
        response.setDateHeader("expries", -1);
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
    }

    @RequestMapping(value = "/getPicByPicId/{picId}", method = RequestMethod.GET)
    @ResponseBody
    public void picShow(@PathVariable("picId") Long picId, Model model, HttpServletRequest request, HttpServletResponse response) {
        if (nonNull(picId)) {
            try {
                PicContent pic = picContentService.findById(picId);
                if (nonNull(pic)) {
                    byte[] bytePic = pic.getContent();
                    responseHeader(response);
                    OutputStream out = response.getOutputStream();
                    out.write(bytePic);
                    out.flush();
                    out.close();
                }
            } catch (IOException e) {
                logger.error("getPic error!" + e);
            }
        }
    }
}
