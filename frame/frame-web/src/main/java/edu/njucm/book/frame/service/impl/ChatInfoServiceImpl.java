package edu.njucm.book.frame.service.impl;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import edu.njucm.book.common.constant.Constants;
import edu.njucm.book.frame.dao.IChatInfoDao;
import edu.njucm.book.frame.domain.chat.ChatInfo;
import edu.njucm.book.frame.service.IChatInfoService;
import edu.njucm.book.frame.vo.chat.ChatParam;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

/**
 * @author lvrongwang
 * @since 2020/5/12 17:18
 */
@Service
public class ChatInfoServiceImpl implements IChatInfoService {

    @Autowired
    private IChatInfoDao chatInfoDao;

    @Override
    public boolean addChat(ChatParam param, Long userId) {
        if (isNull(param) || isNull(userId)) {
            return false;
        }
        ChatInfo info = new ChatInfo();
        info.setUserId(userId);
        info.setChapterId(param.getChapterId());
        info.setLinkChatId(param.getLinkChatId());
        info.setContent(param.getText());
        info.setTopNum(Constants.LONG_ZERO);
        return chatInfoDao.add(info) > 0;
    }

    @Override
    public boolean top(Long chatId, Long num) {
        if (isNull(chatId)) {
            return false;
        }
        ChatInfo chat = getByChatId(chatId);
        if (nonNull(chat)) {
            return chatInfoDao.top(chatId, num) > 0;
        }
        return false;
    }

    @Override
    public boolean deleteChat(Long chatId) {
        if (isNull(chatId)) {
            return false;
        }
        return chatInfoDao.delete(chatId) > 0;
    }

    @Override
    public List<ChatInfo> listByChapterId(Long chapterId) {
        if (isNull(chapterId)) {
            return Lists.newArrayList();
        }
        return chatInfoDao.listByChapterId(chapterId);
    }

    @Override
    public ChatInfo getByChatId(Long chatId) {
        if (isNull(chatId)) {
            return null;
        }
        return chatInfoDao.getByChatId(chatId);
    }
}
