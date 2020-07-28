package edu.njucm.book.user.service.impl;

import com.google.common.collect.Lists;
import edu.njucm.book.common.util.EncryptUtils;
import edu.njucm.book.user.dao.IUserDao;
import edu.njucm.book.user.dao.IUserLockDao;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.IUserService;
import edu.njucm.book.user.vo.RegisterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static edu.njucm.book.user.constant.UserTypeEnum.EDITOR;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author lvrongwang
 * @since 2020/4/12 16:14
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;
    @Autowired
    private IUserLockDao userLockDao;

    @Override
    public boolean add(User user) {
        if (isNull(user)) {
            return false;
        }
        return userDao.add(user) > 0;
    }

    @Override
    public boolean update(User user) {
        if (isNull(user)) {
            return false;
        }
        return userDao.update(user) > 0;
    }

    @Override
    public boolean delete(Long userId) {
        if (isNull(userId)) {
            return false;
        }
        return userDao.delete(userId) > 0;
    }

    @Override
    public Long userRegister(RegisterVO registerVO) {
        if (isNull(registerVO)) {
            return 0L;
        }
        User user = tranRegisterVO2User(registerVO);
        if (add(user)) {
            return user.getUserId();
        }
        return 0L;
    }

    @Override
    public User getByUserId(Long userId) {
        if (isNull(userId)) {
            return null;
        }
        return userDao.getByUserId(userId);
    }

    @Override
    public User getByUserPhone(String userPhone) {
        if (isBlank(userPhone)) {
            return null;
        }
        return userDao.getByUserPhone(userPhone);
    }

    @Override
    public List<User> listChildUserByUserId(Long userId) {
        if (isNull(userId)) {
            return Lists.newArrayList();
        }
        List<User> users = userDao.listChildUserByUserId(userId);
        User my = getByUserId(userId);
        users.add(my);
        return users;
    }

    @Override
    public List<User> listUserByUserType(Short userType) {
        if (isNull(userType)) {
            return Lists.newArrayList();
        }
        return userDao.listUserByUserType(userType);
    }

    @Override
    public boolean doAfterLoginFail(Long userId) {
        if (isNull(userId)) {
            return false;
        }
        return userLockDao.add(userId) > 0;
    }

    @Override
    public boolean isLockUser(Long userId) {
        if (isNull(userId)) {
            return false;
        }
        return userLockDao.countByUserId(userId) >= 3;
    }

    @Override
    public boolean unlockUser(Long userId) {
        if (isNull(userId)) {
            return false;
        }
        return userLockDao.delete(userId) > 0;
    }

    @Override
    public Date getLockTime(Long userId) {
        if (isNull(userId)) {
            return null;
        }
        return userLockDao.getLockTime(userId);
    }

    private User tranRegisterVO2User(RegisterVO registerVO) {
        User user = new User();
        if (nonNull(registerVO)) {
            user.setUserName(registerVO.getUserName());
            user.setUserPhone(registerVO.getUserPhone());
            // 默认父用户id
            user.setParentUserId(0L);
            user.setUserPassword(EncryptUtils.encrypt(registerVO.getUserPassword()));
            // 初始身份为编委
            user.setUserType(EDITOR.getType());
        }
        return user;
    }
}
