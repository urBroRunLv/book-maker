package edu.njucm.book.frame.controller.question;

import static edu.njucm.book.common.constant.Constants.STATUS_NORMAL;
import static edu.njucm.book.frame.domain.ajax.BaseAjaxResult.failResult;
import static edu.njucm.book.frame.domain.ajax.BaseAjaxResult.successResult;
import static edu.njucm.book.frame.vo.question.QuestionShowVO.tran2QuestionShowVOList;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import edu.njucm.book.chart.service.PicContentService;
import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.common.util.TxtUtils;
import edu.njucm.book.frame.constant.QuestionTypeEnum;
import edu.njucm.book.frame.domain.BookInfo;
import edu.njucm.book.frame.domain.ChapterInfo;
import edu.njucm.book.frame.domain.ContentInfo;
import edu.njucm.book.frame.domain.QuestionInfo;
import edu.njucm.book.frame.domain.ajax.BaseAjaxResult;
import edu.njucm.book.frame.service.*;
import edu.njucm.book.frame.vo.question.*;
import edu.njucm.book.user.controller.BaseController;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/question")
public class QuestionController extends BaseController implements IPageConstant {

    private static final Logger logger = LoggerFactory.getLogger(QuestionController.class);
    private static final String filePath = "D://test/upload/";

    @Autowired
    private IQuestionInfoService questionInfoService;
    @Autowired
    private IContentInfoService contentInfoService;
    @Autowired
    private IChapterInfoService chapterInfoService;
    @Autowired
    private ITextInfoService textInfoService;
    @Autowired
    private PicContentService picContentService;
    @Autowired
    private IBookInfoService bookInfoService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String questionList(Long chapterId, Short type, Model model) {
        model.addAttribute("type", type);
        List<QuestionInfo> questionInfos = questionInfoService.getQuestionInfoList(chapterId, type);
        ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(chapterId);
        if (nonNull(questionInfos) && nonNull(chapterInfo)) {
            model.addAttribute("questions", tran2QuestionShowVOList(questionInfos));
            model.addAttribute("chapterId", chapterId);
            model.addAttribute("book", bookInfoService.getBookInfoByBookId(chapterInfo.getBookId()));
        }
        return "question/question_list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String addPage(Long chapterId, Model model) {
        List<ContentInfo> contentInfos = contentInfoService.listContentInfoByChapterId(chapterId);
        ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(chapterId);
        if (nonNull(contentInfos) && nonNull(chapterInfo)) {
            model.addAttribute("contents", contentInfos);
            model.addAttribute("book", bookInfoService.getBookInfoByBookId(chapterInfo.getBookId()));
        }
        model.addAttribute("chapterId", chapterId);
        return "question/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addSubmit(Short type, SelectVO selectVO, BlankVO blankVO, SaqVO saqVO, Model model) {
        try {
            QuestionVO vo = null;
            if (QuestionTypeEnum.SELECT.getType().equals(type)) {
                vo = selectVO;
            }
            else if (QuestionTypeEnum.BLANK.getType().equals(type)) {
                vo = blankVO;
            }
            else if (QuestionTypeEnum.SAQ.getType().equals(type)) {
                vo = saqVO;
            }
            if (questionInfoService.saveQuestionInfo(vo)) {
                ContentInfo contentInfo = contentInfoService.getContentInfoByContentId(vo.getContentId());
                if (nonNull(contentInfo)) {
                    model.addAttribute("id", contentInfo.getChapterId());
                    return "question/success";
                }
            }
        }
        catch (Exception e) {
            logger.error("add question error!", e);
        }
        return redirect(ERROR_403);
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String updateQuestion(Long questionId, Model model) {
        if (isNull(questionId)) {
            return redirect(ERROR_403);
        }
        model.addAttribute("questionId", questionId);
        QuestionInfo questionInfo = questionInfoService.getQuestionInfoQuestionId(questionId);
        if (nonNull(questionInfo)) {
            model.addAttribute("VO", new QuestionShowVO(questionInfo));
            ContentInfo contentInfo = contentInfoService.getContentInfoByContentId(questionInfo.getContentId());
            if (nonNull(contentInfo)) {
                model.addAttribute("chapterId", contentInfo.getChapterId());
                model.addAttribute("contents",
                        contentInfoService.listContentInfoByChapterId(contentInfo.getChapterId()));
                ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(contentInfo.getChapterId());
                if (nonNull(chapterInfo)) {
                    model.addAttribute("book", bookInfoService.getBookInfoByBookId(chapterInfo.getBookId()));
                    return "question/update";
                }
            }
        }
        return redirect(ERROR_403);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateSubmit(QuestionVO questionVO) {
        if (isNull(questionVO)) {
            return redirect(ERROR_403);
        }
        QuestionInfo questionInfo = questionInfoService.getQuestionInfoQuestionId(questionVO.getQuestionId());
        questionInfo.setContentId(questionVO.getContentId());
        questionInfo.setQuestionTextId(textInfoService.saveTextInfo(questionVO.getQuestion()));
        questionInfo.setAnswerTextId(textInfoService.saveTextInfo(questionVO.getAnswer()));
        questionInfo.setPicId(picContentService.insertPicture(questionVO.getBase64Pic()));
        if (questionInfoService.updateQuestionInfo(questionInfo)) {
            ContentInfo contentInfo = contentInfoService.getContentInfoByContentId(questionVO.getContentId());
            if (nonNull(contentInfo)) {
                return redirect(QUESTION_SUCCESS + "?id=" + contentInfo.getChapterId());
            }
        }
        return redirect(ERROR_403);
    }

    /**
     * 异步删除问题
     *
     * @param questionId
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseAjaxResult deleteQuestion(String questionId) {
        if (Objects.isNull(questionId)) {
            return failResult(ERROR_403);
        }
        if (StringUtils.isNumeric(questionId)) {
            if (questionInfoService.deleteQuestionInfoByQuestionId(Long.valueOf(questionId))) {
                // 弹窗显示删除成功
                return successResult();
            }
        }
        return failResult();
    }

    /**
     * 成功页
     */
    @RequestMapping(value = "success", method = RequestMethod.GET)
    public String success(Long id, Model model) {
        model.addAttribute("id", id);
        return "question/success";
    }

    /**
     * 批量导入习题
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
        model.addAttribute("contents", contentInfoService.listContentInfoByChapterId(chapterId));
        ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(chapterId);
        if (nonNull(chapterInfo)) {
            model.addAttribute("chapter", chapterInfo);
            model.addAttribute("book", bookInfoService.getBookInfoByBookId(chapterInfo.getBookId()));
        }
        return "question/add_from_txt";
    }

    /**
     * @param files
     * @param contentId
     * @param type
     * @param request
     * @return
     */
    @RequestMapping(value = "addFromTxt", method = RequestMethod.POST)
    public String addFromTxt(@RequestParam("files") MultipartFile[] files, Short type, Long contentId,
            HttpServletRequest request) throws IOException {
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
            String[] textArray = text.split("/////");
            for (String item : textArray) {
                QuestionInfo questionInfo = new QuestionInfo();
                questionInfo.setQuestionType(type);
                questionInfo.setContentId(contentId);
                questionInfo.setQuestionTextId(textInfoService.saveTextInfo(item.substring(0, item.indexOf("答案："))));
                questionInfo.setAnswerTextId(textInfoService.saveTextInfo(item.substring(item.indexOf("答案：") + 3)));
                questionInfo.setQuestionStatus(STATUS_NORMAL);
                if (!questionInfoService.save(questionInfo)) {
                    return redirect(ERROR_403);
                }
            }
        }
        ContentInfo contentInfo = contentInfoService.getContentInfoByContentId(contentId);
        if (isNull(contentInfo)) {
            return redirect(ERROR_403);
        }
        return redirect(QUESTION_SUCCESS + "?id=" + contentInfo.getChapterId());
    }
}
