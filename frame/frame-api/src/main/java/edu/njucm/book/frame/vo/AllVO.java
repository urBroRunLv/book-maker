package edu.njucm.book.frame.vo;

import edu.njucm.book.common.constant.Constants;
import edu.njucm.book.frame.domain.ReferenceInfo;
import edu.njucm.book.frame.vo.book.BookVO;
import edu.njucm.book.frame.vo.book.ChapterVO;
import edu.njucm.book.frame.vo.chat.ChatVO;
import edu.njucm.book.frame.vo.user.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/5/19 11:55:20
 */
public class AllVO {

    public AllVO(List<String> userNames, BookVO bookVO, List<ChapterVO> chapterVOs,
            List<ReferenceInfo> referenceInfos) {
        this.userNames = userNames;
        this.bookVO = bookVO;
        this.chapterVOs = chapterVOs;
        this.referenceInfos = referenceInfos;
    }

    public AllVO() {
    }

    private List<String> userNames;
    private BookVO bookVO;
    private List<ChapterVO> chapterVOs;
    private List<ReferenceInfo> referenceInfos;

    public String getAuthor() {
        String[] authorArray = this.userNames.toArray(new String[this.userNames.size()]);
        return StringUtils.join(authorArray, Constants.SEP_BLANK);
    }

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public BookVO getBookVO() {
        return bookVO;
    }

    public void setBookVO(BookVO bookVO) {
        this.bookVO = bookVO;
    }

    public List<ChapterVO> getChapterVOs() {
        return chapterVOs;
    }

    public void setChapterVOs(List<ChapterVO> chapterVOs) {
        this.chapterVOs = chapterVOs;
    }

    public List<ReferenceInfo> getReferenceInfos() {
        return referenceInfos;
    }

    public void setReferenceInfos(List<ReferenceInfo> referenceInfos) {
        this.referenceInfos = referenceInfos;
    }

    @Override
    public String toString() {
        return "AllVO{" + "userNames='" + userNames + '\'' + ", bookVO=" + bookVO + ", chapterVOs=" + chapterVOs
                + ", referenceInfos=" + referenceInfos + '}';
    }
}
