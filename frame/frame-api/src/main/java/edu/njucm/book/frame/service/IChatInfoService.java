package edu.njucm.book.frame.service;

import edu.njucm.book.frame.domain.chat.ChatInfo;
import edu.njucm.book.frame.vo.chat.ChatParam;

import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/5/12 16:49
 */
public interface IChatInfoService {

    boolean addChat(ChatParam param, Long userId);

    boolean top(Long chatId, Long num);

    boolean deleteChat(Long chatId);

    List<ChatInfo> listByChapterId(Long chapterId);

    ChatInfo getByChatId(Long chatId);
}
