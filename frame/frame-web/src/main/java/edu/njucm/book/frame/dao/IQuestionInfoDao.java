package edu.njucm.book.frame.dao;

import edu.njucm.book.frame.domain.QuestionInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface IQuestionInfoDao {

    int save(QuestionInfo questionInfo);

    int update(QuestionInfo questionInfo);

    int delete(@Param("questionId") Long questionId);

    // 以questionId查询单个习题
    QuestionInfo getQuestionInfoByQuestionId(@Param("questionId") Long questionId);

    List<QuestionInfo> getQuestionInfoList(@Param("contentIds") List<Long> contentIds,
            @Param("questionType") Short questionType);
}
