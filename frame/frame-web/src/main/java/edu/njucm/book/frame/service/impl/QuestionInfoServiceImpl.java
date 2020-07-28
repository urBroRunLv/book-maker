package edu.njucm.book.frame.service.impl;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

import edu.njucm.book.chart.service.PicContentService;
import edu.njucm.book.common.constant.Constants;
import edu.njucm.book.frame.converter.question.QuestionVOConverter;
import edu.njucm.book.frame.dao.IContentInfoDao;
import edu.njucm.book.frame.dao.IQuestionInfoDao;
import edu.njucm.book.frame.domain.QuestionInfo;
import edu.njucm.book.frame.service.IContentInfoService;
import edu.njucm.book.frame.service.IQuestionInfoService;
import edu.njucm.book.frame.service.ITextInfoService;
import edu.njucm.book.frame.vo.question.QuestionVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.google.common.collect.Lists;

@Service
public class QuestionInfoServiceImpl implements IQuestionInfoService {

    @Autowired
    private IQuestionInfoDao questionInfoDao;
    @Autowired
    private PicContentService picContentService;
    @Autowired
    private ITextInfoService textInfoService;
    @Autowired
    private QuestionVOConverter questionVOConverter;
    @Autowired
    private IContentInfoService contentInfoService;

    @Override
    public boolean save(QuestionInfo questionInfo) {
        if (isNull(questionInfo)) {
            return false;
        }
        return questionInfoDao.save(questionInfo) > 0;
    }

    @Transactional
    @Override
    public boolean saveQuestionInfo(QuestionVO vo) {
        if (isNull(vo)) {
            return false;
        }
        QuestionInfo ques = new QuestionInfo();
        ques.setQuestionType(vo.getType());
        ques.setContentId(vo.getContentId());
        ques.setQuestionTextId(textInfoService.saveTextInfo(questionVOConverter.tran2Text(vo)));
        ques.setAnswerTextId(textInfoService.saveTextInfo(vo.getAnswer()));
        if (isNotBlank(vo.getBase64Pic())) {
            ques.setPicId(picContentService.insertPicture(vo.getBase64Pic()));
        }
        ques.setQuestionStatus(Constants.STATUS_NORMAL);
        return questionInfoDao.save(ques) > 0;
    }

    @Override
    public boolean updateQuestionInfo(QuestionInfo questionInfo) {
        if (isNull(questionInfo)) {
            return false;
        }
        return questionInfoDao.update(questionInfo) > 0;
    }

    @Override
    public boolean deleteQuestionInfoByQuestionId(Long questionId) {
        if (isNull(questionId)) {
            return false;
        }
        return questionInfoDao.delete(questionId) > 0;
    }

    @Override
    public QuestionInfo getQuestionInfoQuestionId(Long questionId) {
        if (isNull(questionId)) {
            return null;
        }
        return questionInfoDao.getQuestionInfoByQuestionId(questionId);
    }

    @Override
    public List<QuestionInfo> getQuestionInfoList(Long chapterId, Short questionType) {
        if (isNull(chapterId)) {
            return Lists.newArrayList();
        }
        List<Long> contentIds = contentInfoService.listContentIdByChapterId(chapterId);
        if (isNull(contentIds) || contentIds.isEmpty()) {
            return Lists.newArrayList();
        }
        return questionInfoDao.getQuestionInfoList(contentIds, questionType);
    }
}
