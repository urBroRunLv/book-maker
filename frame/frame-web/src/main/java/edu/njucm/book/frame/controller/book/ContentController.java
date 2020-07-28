package edu.njucm.book.frame.controller.book;

import static edu.njucm.book.common.constant.Constants.STATUS_NORMAL;
import static edu.njucm.book.frame.domain.ajax.BaseAjaxResult.failResult;
import static edu.njucm.book.frame.domain.ajax.BaseAjaxResult.successResult;
import static edu.njucm.book.frame.vo.book.ContentVO.tran2ContentVO;
import static edu.njucm.book.frame.vo.chat.ChatVO.tran2ChatVOs;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import edu.njucm.book.chart.service.PicContentService;
import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.common.util.TxtUtils;
import edu.njucm.book.frame.constant.ContentTypeEnum;
import edu.njucm.book.frame.domain.ChapterInfo;
import edu.njucm.book.frame.domain.ContentInfo;
import edu.njucm.book.frame.domain.ajax.BaseAjaxResult;
import edu.njucm.book.frame.service.*;
import edu.njucm.book.frame.util.ValidateUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import edu.njucm.book.frame.vo.book.ContentVO;
import edu.njucm.book.user.controller.BaseController;
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

import com.google.common.collect.Lists;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lvrongwang
 * @since 2020/3/21 11:20
 */
@Controller
@RequestMapping("content")
public class ContentController extends BaseController implements IPageConstant {

    private static final Logger logger = LoggerFactory.getLogger(ContentController.class);
    private static final String filePath = "D://test/upload/";

    @Autowired
    private IContentInfoService contentInfoService;
    @Autowired
    private IChapterInfoService chapterInfoService;
    @Autowired
    private IBookInfoService bookInfoService;
    @Autowired
    private PicContentService picContentService;
    @Autowired
    private ITextInfoService textInfoService;
    @Autowired
    private IChatInfoService chatInfoService;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserBookMapService userBookMapService;

    /**
     * 内容列表
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String listContents(Long chapterId, Model model, HttpServletRequest request) {
        if (isNull(chapterId)) {
            return redirect(ERROR_403);
        }
        List<ContentInfo> contentInfoList = contentInfoService.listContentInfoByChapterId(chapterId);
        List<ContentVO> voList = Lists.newArrayList();
        for (ContentInfo chapterInfo : contentInfoList) {
            voList.add(tran2ContentVO(chapterInfo));
        }
        ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(chapterId);
        if (nonNull(chapterInfo)) {
            model.addAttribute("book", bookInfoService.getBookInfoByBookId(chapterInfo.getBookId()));
            model.addAttribute("chapterInfo", chapterInfo);
            model.addAttribute("contents", voList);
            // 构建评论区
            User user = userService.getByUserPhone(LoginUtils.getLoginUserToken(request).getUserPhone());
            if (nonNull(user)) {
                model.addAttribute("userId", user.getUserId());
                model.addAttribute("canEdit", userBookMapService.isUserCanEditChapter(user.getUserId(), chapterId));
                model.addAttribute("chatVOs",
                        tran2ChatVOs(user.getUserId(), chatInfoService.listByChapterId(chapterId)));
                return "book/content/content";
            }
        }
        return redirect(ERROR_403);
    }

    /**
     * 添加内容页面
     *
     * @param chapterId
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addContentPage(Long chapterId, Model model) {
        if (isNull(chapterId)) {
            return redirect(ERROR_403);
        }
        ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(chapterId);
        if (nonNull(chapterInfo)) {
            model.addAttribute("book", bookInfoService.getBookInfoByBookId(chapterInfo.getBookId()));
            model.addAttribute("chapterId", chapterId);
            return "book/content/add";
        }
        return redirect(ERROR_403);
    }

    /**
     * 新增内容信息表单提交
     *
     * @param contentVO
     * @param model
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addContentInfoSubmit(ContentVO contentVO, Model model) {
        if (!ValidateUtils.isContentVOValidated(contentVO)) {
            ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(contentVO.getChapterId());
            if (nonNull(chapterInfo)) {
                model.addAttribute("book", bookInfoService.getBookInfoByBookId(chapterInfo.getBookId()));
                model.addAttribute(VO, contentVO);
                return "book/content/add";
            }
            return redirect(ERROR_403);
        }
        ContentInfo contentInfo = new ContentInfo();
        contentInfo.setContentName(contentVO.getContentName());
        contentInfo.setContentType(contentVO.getContentType());
        contentInfo.setChapterId(contentVO.getChapterId());
        contentInfo.setTextId(textInfoService.saveTextInfo(contentVO.getText()));
        contentInfo.setPicId(picContentService.insertPicture(contentVO.getBase64Pic()));
        contentInfo.setContentStatus(STATUS_NORMAL);
        if (contentInfoService.saveContentInfo(contentInfo)) {
            return redirect(CONTENT_SUCCESS + "?id=" + contentVO.getChapterId());
        }
        return redirect(ERROR_403);
    }

    /**
     * 更新内容信息页面
     *
     * @param contentId
     * @param model
     * @return
     */
    @RequestMapping(value = "update/{contentId}", method = RequestMethod.GET)
    public String updateContentInfoPage(@PathVariable Long contentId, Model model) {
        if (isNull(contentId)) {
            return redirect(ERROR_403);
        }
        ContentInfo contentInfo = contentInfoService.getContentInfoByContentId(contentId);
        if (nonNull(contentInfo)) {
            ContentVO contentVO = tran2ContentVO(contentInfo);
            model.addAttribute("VO", contentVO);
            ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(contentInfo.getChapterId());
            if (nonNull(chapterInfo)) {
                model.addAttribute("book", bookInfoService.getBookInfoByBookId(chapterInfo.getBookId()));
                return "book/content/update";
            }
        }
        return redirect(ERROR_403);
    }

    /**
     * 更新内容信息表单提交
     *
     * @param vo
     * @param model
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateContentInfoSubmit(ContentVO vo, Model model) {
        if (!ValidateUtils.isContentVOValidated(vo)) {
            ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(vo.getChapterId());
            if (nonNull(chapterInfo)) {
                model.addAttribute("book", bookInfoService.getBookInfoByBookId(chapterInfo.getBookId()));
                model.addAttribute(VO, vo);
                return "book/content/update";
            }
            return redirect(ERROR_403);
        }
        ContentInfo contentInfo = new ContentInfo();
        contentInfo.setContentId(vo.getContentId());
        contentInfo.setContentName(vo.getContentName());
        contentInfo.setContentType(vo.getContentType());
        contentInfo.setChapterId(vo.getChapterId());
        contentInfo.setTextId(textInfoService.saveTextInfo(vo.getText()));
        contentInfo.setPicId(picContentService.insertPicture(vo.getBase64Pic()));
        contentInfo.setContentStatus(vo.getContentStatus());
        if (contentInfoService.updateContentInfo(contentInfo)) {
            return redirect(CONTENT_SUCCESS + "?id=" + vo.getChapterId());
        }
        return redirect(ERROR_403);
    }

    /**
     * 异步删除内容
     *
     * @param contentId
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxResult deleteContent(String contentId) {
        if (Objects.isNull(contentId)) {
            return failResult(ERROR_403);
        }
        if (StringUtils.isNumeric(contentId)) {
            if (contentInfoService.deleteContentInfoByContentId(Long.valueOf(contentId))) {
                // 弹窗显示删除成功
                return successResult();
            }
        }
        return failResult();
    }

    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String success(Long id, Model model) {
        model.addAttribute("id", id);
        return "book/content/success";
    }

    /**
     * 批量导入内容页
     * 
     * @param chapterId
     * @param model
     * @return
     */
    @RequestMapping(value = "addFromTxt", method = RequestMethod.GET)
    public String addFromTxtPage(Long chapterId, Model model) {
        if (isNull(chapterId)) {
            return redirect(ERROR_403);
        }
        ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(chapterId);
        if (nonNull(chapterInfo)) {
            model.addAttribute("book", bookInfoService.getBookInfoByBookId(chapterInfo.getBookId()));
            model.addAttribute("chapterId", chapterId);
            return "book/content/add_from_txt";
        }
        return redirect(ERROR_403);
    }

    /**
     * 批量导入内容
     * 
     * @param files
     * @param chapterId
     * @param request
     * @return
     */
    @RequestMapping(value = "addFromTxt", method = RequestMethod.POST)
    public String addFromTxt(@RequestParam("files") MultipartFile[] files, Long chapterId, HttpServletRequest request)
            throws IOException {
        if (isNull(files)) {
            return redirect(ERROR_403);
        }
        File fileDir = new File(filePath);
        if (!fileDir.exists()) {
            fileDir.mkdir();
        }
        for (MultipartFile multipartFile : files) {
            File file = new File(filePath + multipartFile.getOriginalFilename());
            multipartFile.transferTo(file);
            String text = TxtUtils.tranTxt2String(file);
            ContentInfo contentInfo = new ContentInfo();
            contentInfo.setContentType(ContentTypeEnum.TEXT.getType());
            contentInfo.setChapterId(chapterId);
            contentInfo.setTextId(textInfoService.saveTextInfo(text));
            contentInfo.setContentStatus(STATUS_NORMAL);
            contentInfoService.saveContentInfo(contentInfo);
        }
        return redirect(CONTENT_SUCCESS + "?id=" + chapterId);
    }
}
