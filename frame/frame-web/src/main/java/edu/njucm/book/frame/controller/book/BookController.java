package edu.njucm.book.frame.controller.book;

import edu.njucm.book.chart.service.PicContentService;

import com.google.common.collect.Lists;

import edu.njucm.book.common.constant.Constants;
import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.frame.domain.BookInfo;
import edu.njucm.book.frame.domain.ajax.BaseAjaxResult;
import edu.njucm.book.frame.service.IBookInfoService;
import edu.njucm.book.frame.util.ValidateUtils;

import edu.njucm.book.frame.vo.book.BookVO;
import edu.njucm.book.user.controller.BaseController;
import edu.njucm.book.user.domain.Token;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.IUserBookMapService;
import edu.njucm.book.user.service.IUserService;
import edu.njucm.book.user.util.LoginUtils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Objects;

import static edu.njucm.book.frame.domain.ajax.BaseAjaxResult.failResult;
import static edu.njucm.book.frame.domain.ajax.BaseAjaxResult.successResult;
import static edu.njucm.book.frame.vo.book.BookVO.tran2BookVO;
import static edu.njucm.book.user.util.LoginUtils.getLoginUser;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @author lvrongwang
 * @since 2020/3/21 11:20
 */
@Controller
@RequestMapping("book")
public class BookController extends BaseController implements IPageConstant {

    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private IBookInfoService bookInfoService;
    @Autowired
    private PicContentService picContentService;
    @Autowired
    private IUserService userService;

    /**
     * 获取书本列表
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listBooks(Model model, HttpServletRequest request) {
        User user = getLoginUser(request);
        if (nonNull(user)) {
            model.addAttribute("userType", user.getUserType());
            List<BookInfo> bookInfoList = bookInfoService.listBookInfoByUserId(user.getUserId());
            model.addAttribute("books", bookInfoList);
            return "book/book/book";
        }
        return redirect(ERROR_403);
    }

    /**
     * 书本详情页
     */
    @RequestMapping(value = "detail/{bookId}", method = RequestMethod.GET)
    public String bookDetail(@PathVariable("bookId") Long bookId, Model model, HttpServletRequest request) {
        if (isNull(bookId)) {
            return redirect(ERROR_403);
        }
        User user = getLoginUser(request);
        if (nonNull(user)) {
            model.addAttribute("userType", user.getUserType());
        }
        BookInfo bookInfo = bookInfoService.getBookInfoByBookId(bookId);
        model.addAttribute(VO, tran2BookVO(bookInfo));
        return "book/book/detail";
    }

    /**
     * 添加书本页面
     *
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addBookPage() {
        return "book/book/add";
    }

    /**
     * 新增书本信息表单提交
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addBookInfoSubmit(BookVO bookVO, Model model, HttpServletRequest request) {
        if (!ValidateUtils.isBookVOValidated(bookVO)) {
            model.addAttribute(VO, bookVO);
            return "book/book/add";
        }
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookName(bookVO.getBookName());
        bookInfo.setBookPic(picContentService.insertPicture(bookVO.getBookPic()));
        Long userId = LoginUtils.getLoginUser(request).getUserId();
        if (bookInfoService.addBook(bookInfo, userId)) {
            return redirect(BOOK_SUCCESS);
        }
        return redirect(ERROR_403);
    }

    /**
     * 更新书本信息页面
     *
     * @param bookId
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{bookId}", method = RequestMethod.GET)
    public String updateBookInfoPage(@PathVariable Long bookId, Model model) {
        if (isNull(bookId)) {
            return redirect(ERROR_403);
        }
        BookInfo bookInfo = bookInfoService.getBookInfoByBookId(bookId);
        BookVO bookVO = BookVO.tran2BookVO(bookInfo);
        model.addAttribute("VO", bookVO);
        return "book/book/update";
    }

    /**
     * 更新书本信息表单提交
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateBookInfoSubmit(BookVO bookVO, Model model, HttpServletRequest request) {
        if (!ValidateUtils.isBookVOValidated(bookVO)) {
            model.addAttribute(VO, bookVO);
            return "book/book/update";
        }
        BookInfo bookInfo = new BookInfo();
        Token token = LoginUtils.getLoginUserToken(request);
        if (nonNull(token)) {
            bookInfo.setBookId(bookVO.getBookId());
            bookInfo.setBookPic(picContentService.insertPicture(bookVO.getBookPic()));
            bookInfo.setBookName(bookVO.getBookName());
            bookInfo.setBookStatus(Constants.STATUS_NORMAL);
            if (bookInfoService.updateBookInfo(bookInfo)) {
                return redirect(BOOK_SUCCESS);
            }
        }
        return redirect(ERROR_403);
    }

    /**
     * 异步删除书本
     *
     * @param bookId
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxResult deleteBook(String bookId) {
        if (Objects.isNull(bookId)) {
            return failResult(ERROR_403);
        }
        if (StringUtils.isNumeric(bookId)) {
            if (bookInfoService.deleteBook(Long.valueOf(bookId))) {
                // 弹窗显示删除成功
                return successResult();
            }
        }
        return failResult();
    }

    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String success() {
        return "book/book/success";
    }

    private List<BookInfo> listBookBySearchParam(BookInfo bookInfo) {
        try {
            return bookInfoService.listBookInfoBySearchParam(bookInfo);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("BookController::list", e);
        }
        return Lists.newArrayList();
    }
}
