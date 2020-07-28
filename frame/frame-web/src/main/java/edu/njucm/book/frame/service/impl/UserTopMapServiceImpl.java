package edu.njucm.book.frame.service.impl;

import edu.njucm.book.frame.dao.IUserTopMapDao;
import edu.njucm.book.frame.domain.chat.UserTopMap;
import edu.njucm.book.frame.service.IUserTopMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

/**
 * @author lvrongwang
 * @since 2020/5/19 2:02
 */
@Service
public class UserTopMapServiceImpl implements IUserTopMapService {

    @Autowired
    private IUserTopMapDao userTopMapDao;

    @Override
    public boolean add(Long userId, Long chatId) {
        if (isNull(userId) || isNull(chatId)) {
            return false;
        }
        return userTopMapDao.add(new UserTopMap(userId, chatId)) > 0;
    }

    @Override
    public boolean delete(Long userId, Long chatId) {
        if (isNull(userId) || isNull(chatId)) {
            return false;
        }
        return userTopMapDao.delete(userId, chatId) > 0;
    }

    @Override
    public boolean hasTop(Long userId, Long chatId) {
        if (isNull(userId) || isNull(chatId)) {
            return false;
        }
        return userTopMapDao.getTopCountByUserIdAndChatId(userId, chatId) > 0;
    }
}
