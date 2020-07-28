package edu.njucm.book.frame.converter.question.type;

import edu.njucm.book.frame.converter.question.AbstractQuestionVOConverter;
import edu.njucm.book.frame.vo.question.QuestionVO;
import edu.njucm.book.frame.vo.question.SelectVO;

import org.springframework.stereotype.Component;

/**
 * @author lvrongwang
 * @since 2020/5/12 15:18
 */
@Component
public class SelectConverter extends AbstractQuestionVOConverter {

    @Override
    protected String convert(QuestionVO questionVO) {
        SelectVO vo = (SelectVO) questionVO;
        StringBuilder str = new StringBuilder();
        str.append("<div>")
                .append("<div>").append(vo.getQuestion()).append("</div>")
                .append("<div>A: ").append(vo.getOptionA()).append("</div>")
                .append("<div>B: ").append(vo.getOptionB()).append("</div>")
                .append("<div>C: ").append(vo.getOptionC()).append("</div>")
                .append("<div>D: ").append(vo.getOptionD()).append("</div>")
                .append("</div>");
        return String.valueOf(str);
    }
}
