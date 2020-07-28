package edu.njucm.book.frame.controller.chat;

import static java.util.Objects.nonNull;

import edu.njucm.book.frame.domain.ajax.BaseAjaxResult;
import edu.njucm.book.frame.service.IChatInfoService;
import edu.njucm.book.frame.service.IUserTopMapService;
import edu.njucm.book.frame.vo.chat.ChatParam;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.IUserService;
import edu.njucm.book.user.util.LoginUtils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lvrongwang
 * @since 2020/5/12 16:07
 */
@Controller
@RequestMapping("chat")
public class ChatController {

    @Autowired
    private IChatInfoService chatInfoService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserTopMapService userTopMapService;

    /**
     * 异步添加评论或回复
     *
     * @param param
     * @param request
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxResult addComment(ChatParam param, HttpServletRequest request) {
        User user = userService.getByUserPhone(LoginUtils.getLoginUserToken(request).getUserPhone());
        if (nonNull(user)) {
            if (chatInfoService.addChat(param, user.getUserId())) {
                return BaseAjaxResult.successResult();
            }
        }
        return BaseAjaxResult.failResult();
    }

    /**
     * 异步删除评论
     *
     * @param chatId
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxResult deleteComment(Long chatId) {
        if (nonNull(chatId)) {
            if (chatInfoService.deleteChat(chatId)) {
                return BaseAjaxResult.successResult();
            }
        }
        return BaseAjaxResult.failResult();
    }

    /**
     * 异步点赞取消赞入库
     *
     * @param chatId
     * @param num
     * @return
     */
    @RequestMapping(value = "top", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxResult top(Long chatId, Long num, HttpServletRequest request) {
        if (nonNull(chatId)) {
            User user = userService.getByUserPhone(LoginUtils.getLoginUserToken(request).getUserPhone());
            if (nonNull(user)) {
                Long userId = user.getUserId();
                if (userTopMapService.hasTop(userId, chatId) ?
                        userTopMapService.delete(userId, chatId) : userTopMapService.add(userId, chatId)) {
                    if (chatInfoService.top(chatId, num)) {
                        return BaseAjaxResult.successResult();
                    }
                }
            }
        }
        return BaseAjaxResult.failResult();
    }
}
