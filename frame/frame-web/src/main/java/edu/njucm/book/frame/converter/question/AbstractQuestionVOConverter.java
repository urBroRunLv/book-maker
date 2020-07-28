package edu.njucm.book.frame.converter.question;

import static edu.njucm.book.common.constant.Constants.EMPTY;

import edu.njucm.book.frame.vo.question.QuestionVO;

import org.springframework.stereotype.Component;

/**
 * @author lvrongwang
 * @since 2020/5/12 15:13
 */
@Component
public abstract class AbstractQuestionVOConverter {

    public String questionTextConvert(QuestionVO vo){
        return convert(vo);
    }

    protected String convert(QuestionVO vo){
        return EMPTY;
    }
}
