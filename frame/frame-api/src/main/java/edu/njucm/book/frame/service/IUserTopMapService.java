package edu.njucm.book.frame.service;

/**
 * @author lvrongwang
 * @since 2020/5/19 1:59
 */
public interface IUserTopMapService {

    boolean add(Long userId, Long chatId);

    boolean delete(Long userId, Long chatId);

    boolean hasTop(Long userId, Long chatId);
}
