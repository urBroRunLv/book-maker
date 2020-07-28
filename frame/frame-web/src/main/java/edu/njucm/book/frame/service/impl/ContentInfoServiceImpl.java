package edu.njucm.book.frame.service.impl;

import com.google.common.collect.Lists;

import edu.njucm.book.frame.dao.IContentInfoDao;
import edu.njucm.book.frame.domain.ContentInfo;
import edu.njucm.book.frame.service.IContentInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author lvrongwang
 * @since 2020/2/29 20:53
 */
@Service
public class ContentInfoServiceImpl implements IContentInfoService {

    @Autowired
    private IContentInfoDao contentInfoDao;

    @Override
    public boolean saveContentInfo(ContentInfo contentInfo) {
        if (Objects.isNull(contentInfo)) {
            return false;
        }
        return contentInfoDao.save(contentInfo) > 0;
    }

    @Override
    public boolean updateContentInfo(ContentInfo contentInfo) {
        if (Objects.isNull(contentInfo)) {
            return false;
        }
        return contentInfoDao.update(contentInfo) > 0;
    }

    @Override
    public boolean deleteContentInfoByContentId(Long contentId) {
        if (Objects.isNull(contentId)) {
            return false;
        }
        ContentInfo contentInfo = getContentInfoByContentId(contentId);
        if (Objects.nonNull(contentInfo)) {
            return contentInfoDao.update(contentInfo.withStatusDeleted()) > 0;
        }
        return false;
    }

    @Override
    public ContentInfo getContentInfoByContentId(Long contentId) {
        if (Objects.isNull(contentId)) {
            return null;
        }
        return contentInfoDao.getContentInfoByContentId(contentId);
    }

    @Override
    public List<ContentInfo> listContentInfoByChapterId(Long chapterId) {
        if (Objects.isNull(chapterId)) {
            return Lists.newArrayList();
        }
        return contentInfoDao.listContentInfoByChapterId(chapterId);
    }

    @Override
    public List<Long> listContentIdByChapterId(Long chapterId) {
        if (Objects.isNull(chapterId)) {
            return Lists.newArrayList();
        }
        return contentInfoDao.listContentIdByChapterId(chapterId);
    }
}
