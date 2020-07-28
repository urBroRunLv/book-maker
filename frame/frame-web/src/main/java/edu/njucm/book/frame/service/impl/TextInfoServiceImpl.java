package edu.njucm.book.frame.service.impl;

import edu.njucm.book.frame.dao.ITextInfoDao;
import edu.njucm.book.frame.domain.TextInfo;
import edu.njucm.book.frame.service.ITextInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author lvrongwang
 * @since 2020/2/29 18:21
 */
@Service
public class TextInfoServiceImpl implements ITextInfoService {

    @Autowired
    private ITextInfoDao textInfoDao;

    @Override
    public Long saveTextInfo(String text) {
        if (Objects.isNull(text)) {
            return null;
        }
        if (textInfoDao.save(new TextInfo().withTextDetail(text)) > 0) {
            return textInfoDao.getId();
        }
        return null;
    }

    @Override
    public boolean updateTextInfo(TextInfo textInfo) {
        if (Objects.isNull(textInfo)) {
            return false;
        }
        return textInfoDao.update(textInfo) > 0;
    }

    @Override
    public boolean deleteTextInfoByTextId(Long textId) {
        if (Objects.isNull(textId)) {
            return false;
        }
        return textInfoDao.delete(textId) > 0;
    }

    @Override
    public String getTextDetailByTextId(Long textId) {
        if (Objects.isNull(textId)) {
            return null;
        }
        TextInfo textInfo = textInfoDao.getTextInfoByTextId(textId);
        if (Objects.nonNull(textInfo)) {
            return textInfo.getTextDetail();
        }
        return null;
    }
}
