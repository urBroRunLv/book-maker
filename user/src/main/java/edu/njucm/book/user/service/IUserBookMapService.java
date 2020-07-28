package edu.njucm.book.user.service;

import edu.njucm.book.user.domain.UserBookMap;
import edu.njucm.book.user.domain.UserChapterMap;

import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/5/3 23:48
 */
public interface IUserBookMapService {

    List<UserBookMap> listByUserId(Long userId);

    List<UserBookMap> listByBookId(Long bookId);

    List<String> listAuthorByBookId(Long bookId);

    boolean add(UserBookMap map);

    boolean addChapterRec(UserChapterMap map);

    boolean delete(Long recId);

    boolean deleteByBookId(Long bookId);

    boolean isUserAccess(Long userId, Long bookId);

    boolean isUserCanEditChapter(Long userId, Long chapterId);
}
