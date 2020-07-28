package edu.njucm.book.frame.dao;

import edu.njucm.book.frame.domain.chat.UserTopMap;
import org.apache.ibatis.annotations.Param;

/**
 * @author lvrongwang
 * @since 2020/5/19 1:55
 */
public interface IUserTopMapDao {

    int add(UserTopMap map);

    int delete(@Param("userId") Long userId, @Param("chatId") Long chatId);

    int getTopCountByUserIdAndChatId(@Param("userId") Long userId, @Param("chatId") Long chatId);
}
