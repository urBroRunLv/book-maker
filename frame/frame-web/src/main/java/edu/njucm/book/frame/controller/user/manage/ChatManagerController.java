package edu.njucm.book.frame.controller.user.manage;

import com.google.common.collect.Lists;
import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.frame.domain.BookInfo;
import edu.njucm.book.frame.domain.ChapterInfo;
import edu.njucm.book.frame.domain.ajax.BaseAjaxResult;
import edu.njucm.book.frame.service.IBookInfoService;
import edu.njucm.book.frame.service.IChapterInfoService;
import edu.njucm.book.frame.vo.AllVO;
import edu.njucm.book.user.controller.BaseController;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.IUserService;
import edu.njucm.book.user.util.LoginUtils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static java.util.Objects.nonNull;

/**
 * 讨论后台管理
 *
 * @author lvrongwang
 * @since 2020/5/12 16:06
 */
@Controller
@RequestMapping("/manage/discuss")
public class ChatManagerController extends BaseController implements IPageConstant {

    @Autowired
    private IUserService userService;
    @Autowired
    private IBookInfoService bookInfoService;
    @Autowired
    private IChapterInfoService chapterInfoService;

    /**
     * 管理讨论内容
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String manageDiscussInfo(Model model, HttpServletRequest request) {
        User user = userService.getByUserPhone(LoginUtils.getLoginUserToken(request).getUserPhone());
        if (BooleanUtils.isFalse(user.isManager())) {
            return redirect(ERROR_403);
        }
        // todo
        List<BookInfo> bookInfos = bookInfoService.listBookInfoByUserId(user.getUserId());
        List<AllVO> vos = Lists.newArrayList();
        for (BookInfo bookInfo : bookInfos) {
            vos.add(bookInfoService.getAllVOByBookId(user.getUserId(), bookInfo.getBookId()));
        }
        model.addAttribute("allVOs", vos);
        return "user/manage_discuss";
    }

    /**
     * 讨论开关
     * 
     * @param chapterId
     * @param openFlag
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/openFlag", method = RequestMethod.POST)
    public BaseAjaxResult operateOpenFlag(Long chapterId, Short openFlag) {
        if (nonNull(chapterId) && nonNull(openFlag)) {
            ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(chapterId);
            if (nonNull(chapterInfo)) {
                if (chapterInfoService.updateChapterInfo(chapterInfo.withChatFlag(openFlag))) {
                    return BaseAjaxResult.successResult();
                }
            }
        }
        return BaseAjaxResult.failResult();
    }
}
