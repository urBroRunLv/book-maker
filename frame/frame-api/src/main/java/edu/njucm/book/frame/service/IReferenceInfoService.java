package edu.njucm.book.frame.service;

import edu.njucm.book.frame.domain.ReferenceInfo;

import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/2/29 21:14
 */
public interface IReferenceInfoService {

    /**
     * 存储参考书目信息
     *
     * @param referenceInfo
     * @return
     */
    boolean saveReferenceInfo(ReferenceInfo referenceInfo);

    /**
     * 更新参考书目信息
     *
     * @param referenceInfo
     * @return
     */
    boolean updateReferenceInfo(ReferenceInfo referenceInfo);

    /**
     * 删除参考书目信息
     *
     * @param referenceId
     * @return
     */
    boolean deleteReferenceInfoByReferenceId(Long referenceId);

    /**
     * 根据id获取参考书目信息
     *
     * @param referenceId
     * @return
     */
    ReferenceInfo getByReferenceId(Long referenceId);

    /**
     * 根据书本id获取参考书目列表
     *
     * @param bookId
     * @return
     */
    List<ReferenceInfo> listReferenceInfoByBookId(Long bookId);
}
