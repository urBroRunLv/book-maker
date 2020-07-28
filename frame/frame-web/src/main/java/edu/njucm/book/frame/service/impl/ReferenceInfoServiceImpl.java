package edu.njucm.book.frame.service.impl;

import com.google.common.collect.Lists;
import edu.njucm.book.frame.dao.IReferenceInfoDao;
import edu.njucm.book.frame.domain.ReferenceInfo;
import edu.njucm.book.frame.service.IReferenceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

/**
 * @author lvrongwang
 * @since 2020/2/29 21:37
 */
@Service
public class ReferenceInfoServiceImpl implements IReferenceInfoService {

    @Autowired
    private IReferenceInfoDao referenceInfoDao;

    @Override
    public boolean saveReferenceInfo(ReferenceInfo referenceInfo) {
        if (isNull(referenceInfo)) {
            return false;
        }
        return referenceInfoDao.save(referenceInfo) > 0;
    }

    @Override
    public boolean updateReferenceInfo(ReferenceInfo referenceInfo) {
        if (isNull(referenceInfo)) {
            return false;
        }
        return referenceInfoDao.update(referenceInfo) > 0;
    }

    @Override
    public boolean deleteReferenceInfoByReferenceId(Long referenceId) {
        if (isNull(referenceId)) {
            return false;
        }
        return referenceInfoDao.delete(referenceId) > 0;
    }

    @Override
    public ReferenceInfo getByReferenceId(Long referenceId) {
        if (isNull(referenceId)) {
            return null;
        }
        return referenceInfoDao.getReferenceInfoByReferenceId(referenceId);
    }

    @Override
    public List<ReferenceInfo> listReferenceInfoByBookId(Long bookId) {
        if (isNull(bookId)){
            return Lists.newArrayList();
        }
        return referenceInfoDao.listReferenceInfoByBookId(bookId);
    }
}
