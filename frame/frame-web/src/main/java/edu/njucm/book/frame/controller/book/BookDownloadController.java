package edu.njucm.book.frame.controller.book;

import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.frame.service.IBookInfoService;

import java.io.IOException;

import edu.njucm.book.frame.util.FileUtils;
import edu.njucm.book.frame.vo.AllVO;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.util.LoginUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.Objects.nonNull;

/**
 * 书本导出
 * 
 * @author lvrongwang
 * @since 2020/5/19 11:44:51
 */
@Controller
@RequestMapping("book")
public class BookDownloadController implements IPageConstant {

    private static final String BOOK_FILE_PATH = "d://test/";
    private static final String BOOK_MODEL_PATH = BookDownloadController.class.getResource("/model").getPath();
    private static final String BOOK_MODEL_FILE = "bookModel.vm";

    @Autowired
    private IBookInfoService bookInfoService;

    @RequestMapping(value = "download/{bookId}", method = RequestMethod.GET)
    public void downloadBook(@PathVariable Long bookId, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        User user = LoginUtils.getLoginUser(request);
        AllVO allVO = nonNull(user) ? bookInfoService.getAllVOByBookId(user.getUserId(), bookId) : null;
        if (nonNull(allVO)) {
            try {
                Context context = new VelocityContext();
                context.put("VO", allVO);
                String bookDoc = allVO.getBookVO().getBookName() + ".doc";
                if (FileUtils.downLoad(response, BOOK_FILE_PATH, bookDoc, BOOK_MODEL_PATH, BOOK_MODEL_FILE, context)) {
                    response.sendRedirect("/book/success?id=" + bookId);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect(ERROR_403);
    }
}
