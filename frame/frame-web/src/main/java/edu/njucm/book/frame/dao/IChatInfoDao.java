package edu.njucm.book.frame.dao;

import edu.njucm.book.frame.domain.chat.ChatInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/**
 * @author lvrongwang
 * @since 2020/5/5 14:53
 */
public interface IChatInfoDao {

    int add(ChatInfo chatInfo);

    int top(@Param("chatId") Long chatId, @Param("topNum") Long topNum);

    int delete(@Param("chatId") Long chatId);

    List<ChatInfo> listByChapterId(@Param("chapterId") Long chapterId);

    ChatInfo getByChatId(@Param("chatId") Long chatId);

    List<ChatInfo> listByLinkChatId(@Param("linkChatId") Long linkChatId);
}
