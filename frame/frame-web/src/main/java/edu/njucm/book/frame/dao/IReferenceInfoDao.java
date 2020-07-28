package edu.njucm.book.frame.dao;

import edu.njucm.book.frame.domain.ReferenceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/2/29 18:20
 */
public interface IReferenceInfoDao {

    int save(ReferenceInfo referenceInfo);

    int update(ReferenceInfo referenceInfo);

    int delete(@Param("referenceId") Long referenceId);

    ReferenceInfo getReferenceInfoByReferenceId(@Param("referenceId") Long referenceId);

    List<ReferenceInfo> listReferenceInfoByBookId(@Param("bookId") Long bookId);
}
