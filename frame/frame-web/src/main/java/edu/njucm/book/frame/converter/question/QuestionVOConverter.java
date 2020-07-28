package edu.njucm.book.frame.converter.question;

import edu.njucm.book.frame.converter.question.type.BlankConverter;
import edu.njucm.book.frame.converter.question.type.OtherConverter;
import edu.njucm.book.frame.converter.question.type.SaqConverter;
import edu.njucm.book.frame.converter.question.type.SelectConverter;
import edu.njucm.book.frame.vo.question.QuestionVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lvrongwang
 * @since 2020/5/12 15:15
 */
@Component
public class QuestionVOConverter {

    @Autowired
    private SelectConverter selectConverter;
    @Autowired
    private BlankConverter blankConverter;
    @Autowired
    private SaqConverter saqConverter;
    @Autowired
    private OtherConverter otherConverter;

    /**
     * 策略模式构建富文本转化器，便于后期新增题型
     *
     * @param vo
     * @return
     */
    public String tran2Text(QuestionVO vo) {
        return getConverter(vo).questionTextConvert(vo);
    }

    private AbstractQuestionVOConverter getConverter(QuestionVO vo) {
        if (vo.isSelect()) {
            return selectConverter;
        }
        if (vo.isBlank()) {
            return blankConverter;
        }
        if (vo.isSaq()) {
            return saqConverter;
        } else {
            return otherConverter;
        }
    }
}
