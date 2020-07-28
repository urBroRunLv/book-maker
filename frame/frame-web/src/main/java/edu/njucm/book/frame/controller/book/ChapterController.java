package edu.njucm.book.frame.controller.book;

import static edu.njucm.book.frame.domain.ajax.BaseAjaxResult.failResult;
import static edu.njucm.book.frame.domain.ajax.BaseAjaxResult.successResult;
import static edu.njucm.book.frame.vo.book.ChapterVO.tran2ChapterVO;
import static edu.njucm.book.frame.vo.book.ChapterVO.tran2ChapterVOList;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.frame.domain.BookInfo;
import edu.njucm.book.frame.domain.ChapterInfo;
import edu.njucm.book.frame.domain.ajax.BaseAjaxResult;
import edu.njucm.book.frame.service.IBookInfoService;
import edu.njucm.book.frame.service.IChapterInfoService;
import edu.njucm.book.frame.util.ValidateUtils;
import edu.njucm.book.frame.vo.book.ChapterVO;
import edu.njucm.book.user.controller.BaseController;

import java.util.List;
import java.util.Objects;

import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.IUserService;
import edu.njucm.book.user.util.LoginUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lvrongwang
 * @since 2020/3/27 15:01
 */
@Controller
@RequestMapping("chapter")
public class ChapterController extends BaseController implements IPageConstant {

    private static final Logger logger = LoggerFactory.getLogger(ChapterController.class);

    @Autowired
    private IChapterInfoService chapterInfoService;
    @Autowired
    private IBookInfoService bookInfoService;

    /**
     * 章节列表页
     *
     * @param bookId
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listChapters(Long bookId, Model model, HttpServletRequest request) {
        if (Objects.isNull(bookId)) {
            return redirect(ERROR_403);
        }
        model.addAttribute("bookId", bookId);
        List<ChapterInfo> chapterInfoList = chapterInfoService.listChapterInfoByBookId(bookId);
        BookInfo bookInfo = bookInfoService.getBookInfoByBookId(bookId);
        if (nonNull(bookInfo)) {
            model.addAttribute("bookName", bookInfo.getBookName());
            User user = LoginUtils.getLoginUser(request);
            if (nonNull(user)) {
                model.addAttribute("user", user);
                model.addAttribute("chapters", tran2ChapterVOList(user.getUserId(), chapterInfoList));
            }
            return "book/chapter/chapter";
        }
        return redirect(ERROR_403);
    }

    /**
     * 添加章节页面
     *
     * @param bookId
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addChapterPage(Long bookId, Model model) {
        if (isNull(bookId)) {
            return redirect(ERROR_403);
        }
        model.addAttribute("bookId", bookId);
        return "book/chapter/add";
    }

    /**
     * 新增章节信息表单提交
     *
     * @param bookId
     * @param chapterVO
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addChapterInfoSubmit(Long bookId, ChapterVO chapterVO, Model model, HttpServletRequest request) {
        if (isNull(bookId)) {
            return redirect(ERROR_403);
        }
        if (!ValidateUtils.isChapterVOValidated(chapterVO)) {
            model.addAttribute(VO, chapterVO);
            return "book/chapter/add";
        }
        ChapterInfo chapterInfo = new ChapterInfo();
        chapterInfo.setChapterName(chapterVO.getChapterName());
        chapterInfo.setChapterNo(chapterVO.getChapterNo());
        chapterInfo.setChatFlag(chapterVO.getChatFlag());
        chapterInfo.setBookId(bookId);
        User user = LoginUtils.getLoginUser(request);
        if (nonNull(user)) {
            if (chapterInfoService.saveChapterInfo(chapterInfo, user.getUserId())) {
                return redirect(CHAPTER_SUCCESS + "?id=" + bookId);
            }
        }
        return redirect(ERROR_403);
    }

    /**
     * 更新章节信息页面
     *
     * @param chapterId
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{chapterId}", method = RequestMethod.GET)
    public String updateChapterInfoPage(@PathVariable Long chapterId, Model model) {
        if (isNull(chapterId)) {
            return redirect(ERROR_403);
        }
        ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(chapterId);
        ChapterVO chapterVO = tran2ChapterVO(null, chapterInfo);
        model.addAttribute("VO", chapterVO);
        return "book/chapter/update";
    }

    /**
     * 更新章节信息表单提交
     *
     * @param vo
     * @param model
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateChapterInfoSubmit(ChapterVO vo, Model model) {
        if (!ValidateUtils.isChapterVOValidated(vo)) {
            model.addAttribute(VO, vo);
            return "book/chapter/update";
        }
        ChapterInfo chapterInfo = new ChapterInfo();
        chapterInfo.setChapterId(vo.getChapterId());
        chapterInfo.setChapterNo(vo.getChapterNo());
        chapterInfo.setChatFlag(vo.getChatFlag());
        chapterInfo.setChapterName(vo.getChapterName());
        if (chapterInfoService.updateChapterInfo(chapterInfo)) {
            return redirect(CHAPTER_SUCCESS + "?id=" + vo.getBookId());
        }
        return redirect(ERROR_403);
    }

    /**
     * 异步删除章节
     *
     * @param chapterId
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxResult deleteChapter(String chapterId) {
        if (Objects.isNull(chapterId)) {
            return failResult(ERROR_403);
        }
        if (StringUtils.isNumeric(chapterId)) {
            if (chapterInfoService.deleteChapterInfoByChapterId(Long.valueOf(chapterId))) {
                // 弹窗显示删除成功
                return successResult();
            }
        }
        return failResult();
    }

    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String success(Long id, Model model) {
        model.addAttribute("id", id);
        return "book/chapter/success";
    }
}
