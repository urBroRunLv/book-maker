package edu.njucm.book.frame.converter.question.type;

import edu.njucm.book.frame.converter.question.AbstractQuestionVOConverter;
import edu.njucm.book.frame.vo.question.QuestionVO;
import edu.njucm.book.frame.vo.question.SaqVO;

import org.springframework.stereotype.Component;

/**
 * @author lvrongwang
 * @since 2020/5/12 15:18
 */
@Component
public class SaqConverter extends AbstractQuestionVOConverter {

    @Override
    protected String convert(QuestionVO questionVO) {
        SaqVO vo = (SaqVO) questionVO;
        StringBuilder str = new StringBuilder();
        str.append("<div>").append(vo.getQuestion()).append(vo.getBase64Pic()).append("<div");
        return String.valueOf(str);
    }
}
