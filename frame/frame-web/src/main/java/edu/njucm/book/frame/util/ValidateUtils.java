package edu.njucm.book.frame.util;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isAnyBlank;
import static org.apache.commons.lang3.StringUtils.isBlank;

import edu.njucm.book.frame.domain.ReferenceInfo;
import edu.njucm.book.frame.vo.book.BookVO;
import edu.njucm.book.frame.vo.book.ChapterVO;
import edu.njucm.book.frame.vo.book.ContentVO;

/**
 * @author lvrongwang
 * @since 2020/3/21 22:01
 */
public class ValidateUtils {

    private static final String INSERT_EMPTY = "输入信息不可为空，请重填~";

    public static boolean isBookVOValidated(BookVO vo) {
        if (isNull(vo)) {
            return false;
        }
        if (isBlank(vo.getBookName()) || isBlank(vo.getBookPic())) {
            vo.setErrorMsg(INSERT_EMPTY);
            return false;
        }
        return true;
    }

    public static boolean isBookVOUpdate(BookVO bookVO) {
        if (isNull(bookVO)) {
            return false;
        }
        return nonNull(bookVO.getBookId());
    }

    public static boolean isChapterVOValidated(ChapterVO vo) {
        if (isNull(vo)) {
            return false;
        }
        if (isBlank(vo.getChapterName()) || isNull(vo.getChapterNo())) {
            vo.setErrorMsg(INSERT_EMPTY);
            return false;
        }
        return true;
    }

    public static boolean isContentVOValidated(ContentVO vo) {
        if (isNull(vo)) {
            return false;
        }
        if (isNull(vo.getContentType())) {
            vo.setErrorMsg(INSERT_EMPTY);
            return false;
        }
        return true;
    }

    public static boolean isReferenceInfoValidate(ReferenceInfo info) {
        if (isNull(info)) {
            return false;
        }
        return !isAnyBlank(info.getAuthorName(), info.getPublishName(), info.getPublishPlace(), info.getPublishYear(),
                info.getReferenceName(), info.getReferenceType()) && !isNull(info.getBookId());
    }
}
