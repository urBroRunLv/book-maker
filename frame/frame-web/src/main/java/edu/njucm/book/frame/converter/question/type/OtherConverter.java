package edu.njucm.book.frame.converter.question.type;

import edu.njucm.book.frame.converter.question.AbstractQuestionVOConverter;
import edu.njucm.book.frame.vo.question.QuestionVO;

import org.springframework.stereotype.Component;

/**
 * @author lvrongwang
 * @since 2020/5/12 15:26
 */
@Component
public class OtherConverter extends AbstractQuestionVOConverter {

    @Override
    protected String convert(QuestionVO vo) {
        StringBuilder str = new StringBuilder();
        str.append("<div>").append(vo.getQuestion()).append("<div>");
        return String.valueOf(str);
    }
}
