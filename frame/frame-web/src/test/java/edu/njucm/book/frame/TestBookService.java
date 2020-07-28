package edu.njucm.book.frame;

import edu.njucm.book.BaseDaoTest;
import edu.njucm.book.frame.domain.BookInfo;
import edu.njucm.book.frame.service.IBookInfoService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lvrongwang
 * @since 2020/4/18 22:20
 */
public class TestBookService extends BaseDaoTest {

    @Autowired
    private IBookInfoService bookInfoService;

    @Test
    public void add() {
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookStatus((short) 0);
        bookInfo.setBookName("算法导论");
        bookInfo.setBookPic(8L);
        System.out.println(bookInfoService.saveBookInfo(bookInfo));
    }

    @Test
    public void bookList(){
        Long userId = 8L;
        System.out.println(bookInfoService.listBookInfoByUserId(userId));
    }
}
