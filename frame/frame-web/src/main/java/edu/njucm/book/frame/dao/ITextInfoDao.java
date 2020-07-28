package edu.njucm.book.frame.dao;

import edu.njucm.book.frame.domain.TextInfo;
import org.apache.ibatis.annotations.Param;

/**
 * @author lvrongwang
 * @since 2020/2/29 18:20
 */
public interface ITextInfoDao {

    int save(TextInfo textInfo);

    Long getId();

    int update(TextInfo textInfo);

    int delete(@Param("textId") Long textId);

    TextInfo getTextInfoByTextId(@Param("textId") Long textId);
}
