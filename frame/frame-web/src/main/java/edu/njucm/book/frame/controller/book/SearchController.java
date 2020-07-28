package edu.njucm.book.frame.controller.book;

import edu.njucm.book.frame.domain.BookInfo;
import edu.njucm.book.common.util.EncryptUtils;

import edu.njucm.book.user.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lvrongwang
 * @since 2020/3/27 15:00
 */
@Controller
@RequestMapping("search")
public class SearchController extends BaseController {

    @RequestMapping(value = "book",method = RequestMethod.GET)
    public String searchBook(String bookId, String bookName, String bookStatus) {
        BookInfo bookInfo = new BookInfo();
        if (StringUtils.isNotBlank(bookId)) {
            bookInfo.setBookId(Long.valueOf(bookId));
        }
        if (StringUtils.isNotBlank(bookName)) {
            bookInfo.setBookName(bookName);
        }
        if (StringUtils.isNotBlank(bookStatus)) {
            bookInfo.setBookStatus(Short.valueOf(bookStatus));
        }
        return redirect("/book/list?bookInfo=" + EncryptUtils.encrypt(JSONUtils.toJSONString(bookInfo)));
    }
}
