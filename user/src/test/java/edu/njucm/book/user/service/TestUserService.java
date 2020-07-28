package edu.njucm.book.user.service;

import edu.njucm.book.common.util.EncryptUtils;
import edu.njucm.book.user.BaseDaoTest;
import edu.njucm.book.user.dao.IUserDao;
import edu.njucm.book.user.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author lvrongwang
 * @since 2020/4/18 14:43
 */
public class TestUserService extends BaseDaoTest {

    @Autowired
    IUserService userService;
    @Autowired
    IUserDao userDao;

    @Test
    public void getUserByUserPhone() {
        String userPhone = "15751868653";
        System.out.println(userService.getByUserPhone(userPhone).toString());
    }

    @Test
    public void listChildUserByUserId() {
        Long userId = 1L;
        System.out.println(userDao.listChildUserByUserId(userId).toString());
        System.out.println(userService.listChildUserByUserId(userId).toString());
    }

    @Test
    public void add() {
        User user = new User();
        user.setUserName("吕融望");
        user.setUserPassword(EncryptUtils.encrypt("1234qwer"));
        user.setParentUserId(0L);
        user.setUserType((short) 2);
        user.setUserPhone("13025641231");
        System.out.println(userDao.add(user));
    }
}
