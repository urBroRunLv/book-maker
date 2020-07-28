package edu.njucm.book.common.constant;

/**
 * @author lvrongwang
 * @since 2020/3/7 10:43
 */
public interface IPageConstant {

    /**
     * error page
     */
    String ERROR_403 = "/error/403";
    String ERROR_404 = "/error/404";
    String ERROR_500 = "/error/500";
    /**
     * index
     */
    String HOME_PAGE = "http://www.book.com";
    String BOOK_HOME_PAGE = "http://www.book.com/book/list";
    String USER_CENTER_PAGE = "http://www.book.com/user";
    /**
     * success
     */
    String BOOK_SUCCESS = "/book/success";
    String CHAPTER_SUCCESS = "/chapter/success";
    String CONTENT_SUCCESS = "/content/success";
    String QUESTION_SUCCESS = "/question/success";
}
