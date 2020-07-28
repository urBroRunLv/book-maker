package edu.njucm.book.frame.service;

import edu.njucm.book.frame.domain.QuestionInfo;
import edu.njucm.book.frame.vo.question.QuestionVO;

import java.util.List;

public interface IQuestionInfoService {

    boolean save(QuestionInfo questionInfo);

    /**
     * 存储习题信息
     *
     * @param vo
     * @return
     */
    boolean saveQuestionInfo(QuestionVO vo);

    /**
     * 更新习题信息
     *
     * @param questionInfo
     * @return
     */
    boolean updateQuestionInfo(QuestionInfo questionInfo);

    /**
     * 删除习题信息
     *
     * @param questionId
     * @return
     */
    boolean deleteQuestionInfoByQuestionId(Long questionId);

    /**
     * 根据id获取习题信息
     *
     * @param questionId
     * @return
     */
    QuestionInfo getQuestionInfoQuestionId(Long questionId);

    /**
     * 根据章节、知识点id、题型可选地查询习题列表
     *
     * @param chapterId
     * @param questionType
     * @return
     */
    List<QuestionInfo> getQuestionInfoList(Long chapterId, Short questionType);
}
