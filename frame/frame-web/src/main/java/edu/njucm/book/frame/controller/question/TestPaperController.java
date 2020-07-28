package edu.njucm.book.frame.controller.question;

import static edu.njucm.book.frame.constant.QuestionTypeEnum.BLANK;
import static edu.njucm.book.frame.constant.QuestionTypeEnum.SAQ;
import static edu.njucm.book.frame.constant.QuestionTypeEnum.SELECT;
import static edu.njucm.book.frame.vo.question.QuestionShowVO.tran2QuestionShowVOList;
import static java.util.Objects.nonNull;

import edu.njucm.book.common.constant.IPageConstant;
import edu.njucm.book.frame.domain.BookInfo;
import edu.njucm.book.frame.domain.ChapterInfo;
import edu.njucm.book.frame.domain.QuestionInfo;
import edu.njucm.book.frame.service.IBookInfoService;
import edu.njucm.book.frame.service.IChapterInfoService;
import edu.njucm.book.frame.service.IQuestionInfoService;
import edu.njucm.book.frame.util.FileUtils;
import edu.njucm.book.frame.vo.question.QuestionTypeParamVO;

import java.util.Collections;
import java.util.List;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.context.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lvrongwang
 * @since 2020/5/18 11:56:01
 */
@Controller
@RequestMapping("/question/test")
public class TestPaperController implements IPageConstant {

    private static final String TEST_FILE_PATH = "d://test/";
    private static final String TEST_FILE = "test.doc";
    private static final String TEST_MODEL_PATH = TestPaperController.class.getResource("/model").getPath();
    private static final String TEST_MODEL_FILE = "testModel.vm";

    @Autowired
    private IChapterInfoService chapterInfoService;
    @Autowired
    private IBookInfoService bookInfoService;
    @Autowired
    private IQuestionInfoService questionInfoService;

    @RequestMapping(value = "/selectNum", method = RequestMethod.GET)
    public String selectQuestionTypesNum(Long chapterId, Model model) {
        List<QuestionInfo> questionInfos = questionInfoService.getQuestionInfoList(chapterId, null);
        int selectNum = 0, blankNum = 0, saqNum = 0;
        for (QuestionInfo questionInfo : questionInfos) {
            if (SELECT.getType().equals(questionInfo.getQuestionType())) {
                selectNum++;
            }
            else if (BLANK.getType().equals(questionInfo.getQuestionType())) {
                blankNum++;
            }
            else if (SAQ.getType().equals(questionInfo.getQuestionType())) {
                saqNum++;
            }
        }
        ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(chapterId);
        if (nonNull(chapterInfo)) {
            model.addAttribute("chapterInfo", chapterInfo);
            model.addAttribute("bookInfo", bookInfoService.getBookInfoByBookId(chapterInfo.getBookId()));
        }
        model.addAttribute("num", new QuestionTypeParamVO(chapterId, selectNum, blankNum, saqNum));
        return "question/test";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void buildTestPaper(boolean isOnline, QuestionTypeParamVO vo, HttpServletResponse response)
            throws Exception {
        List<QuestionInfo> questionInfos = questionInfoService.getQuestionInfoList(vo.getChapterId(), null),
                selects = Lists.newArrayList(), blanks = Lists.newArrayList(), saqs = Lists.newArrayList();
        Collections.shuffle(questionInfos);
        for (QuestionInfo questionInfo : questionInfos) {
            if (SELECT.getType().equals(questionInfo.getQuestionType()) && selects.size() < vo.getSelectAmount()) {
                selects.add(questionInfo);
            }
            else if (BLANK.getType().equals(questionInfo.getQuestionType()) && blanks.size() < vo.getBlankAmount()) {
                blanks.add(questionInfo);
            }
            else if (SAQ.getType().equals(questionInfo.getQuestionType()) && saqs.size() < vo.getSaqAmount()) {
                saqs.add(questionInfo);
            }
        }
        ChapterInfo chapterInfo = chapterInfoService.getChapterInfoByChapterId(vo.getChapterId());
        if (nonNull(chapterInfo)) {
            BookInfo bookInfo = bookInfoService.getBookInfoByBookId(chapterInfo.getBookId());
            // 导出模板
            Context context = new VelocityContext();
            context.put("book", bookInfo);
            context.put("chapter", chapterInfo);
            context.put("selects", tran2QuestionShowVOList(selects));
            context.put("blanks", tran2QuestionShowVOList(blanks));
            context.put("saqs", tran2QuestionShowVOList(saqs));
            String testDoc = "第" + chapterInfo.getChapterNo() + "章" + chapterInfo.getChapterName() + "单元测试.doc";
            if (FileUtils.downLoad(response, TEST_FILE_PATH, testDoc, TEST_MODEL_PATH, TEST_MODEL_FILE, context)) {
                response.sendRedirect("/question/success?id=" + vo.getChapterId());
            }
        }
        response.sendRedirect(ERROR_403);
    }
}
