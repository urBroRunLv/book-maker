package edu.njucm.book.frame.controller.user.manage;

import com.google.common.collect.Lists;
import edu.njucm.book.common.constant.Constants;
import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.frame.domain.BookInfo;
import edu.njucm.book.frame.domain.ChapterInfo;
import edu.njucm.book.frame.service.IBookInfoService;
import edu.njucm.book.frame.service.IChapterInfoService;
import edu.njucm.book.frame.vo.AllVO;
import edu.njucm.book.user.controller.BaseController;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.domain.UserBookMap;
import edu.njucm.book.user.domain.UserChapterMap;
import edu.njucm.book.user.service.IUserBookMapService;
import edu.njucm.book.user.service.IUserService;
import edu.njucm.book.user.util.LoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.http.util.TextUtils.isBlank;

/**
 * @author lvrongwang
 * @since 2020/5/24 17:39
 */
@Controller
@RequestMapping("/manage")
public class BookManagerController extends BaseController implements IPageConstant {

    @Autowired
    private IBookInfoService bookInfoService;
    @Autowired
    private IUserBookMapService userBookMapService;
    @Autowired
    private IChapterInfoService chapterInfoService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public String distributeBooks(Model model, HttpServletRequest request) {
        User user = LoginUtils.getLoginUser(request);
        assert user != null;
        model.addAttribute("books", bookInfoService.listBookInfoByUserId(user.getUserId()));
        return "user/manage_book";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String distributeBooksSubmit(Long bookId, String userIds) {
        if (isNull(bookId) || isBlank(userIds)) {
            return redirect(ERROR_403);
        }
        String[] userIdArray = userIds.split(Constants.SEP_COMMA);
        for (String userId : userIdArray) {
            if (!userBookMapService.add(new UserBookMap(bookId, Long.valueOf(userId)))) {
                return redirect(ERROR_403);
            }
        }
        return "user/success";
    }

    @RequestMapping(value = "/chapter", method = RequestMethod.GET)
    public String distributeChapters(Model model, HttpServletRequest request) {
        User user = LoginUtils.getLoginUser(request);
        assert user != null;
        List<AllVO> books = Lists.newArrayList();
        for (BookInfo book : bookInfoService.listBookInfoByUserId(user.getUserId())) {
            books.add(bookInfoService.getAllVOByBookId(user.getUserId(), book.getBookId()));
        }
        model.addAttribute("books", books);
        return "user/manage_chapter";
    }

    @RequestMapping(value = "/chapter", method = RequestMethod.POST)
    public String distributeChaptersSubmit(Long chapterId, String userIds) {
        if (isNull(chapterId) || isBlank(userIds)) {
            return redirect(ERROR_403);
        }
        String[] userIdArray = userIds.split(Constants.SEP_COMMA);
        for (String userId : userIdArray) {
            if (userBookMapService.addChapterRec(new UserChapterMap(chapterId, Long.valueOf(userId)))) {
                ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(chapterId);
                if (nonNull(chapterInfo)) {
                    if (userBookMapService.isUserAccess(Long.valueOf(userId), chapterInfo.getBookId())) {
                        return "user/success";
                    }
                    else {
                        if (userBookMapService.add(new UserBookMap(chapterInfo.getBookId(), Long.valueOf(userId)))) {
                            return "user/success";
                        }
                    }
                }
            }
        }
        return redirect(ERROR_403);
    }
}
