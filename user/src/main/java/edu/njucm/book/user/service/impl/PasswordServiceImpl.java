package edu.njucm.book.user.service.impl;

import edu.njucm.book.common.util.EncryptUtils;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.IPasswordService;
import edu.njucm.book.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author lvrongwang
 * @since 2020/4/13 21:14
 */
@Service
public class PasswordServiceImpl implements IPasswordService {

    @Autowired
    private IUserService userService;

    @Override
    public boolean checkLogonPassword(String logonPassword, String encryptedPassword) {
        if (isBlank(logonPassword) || isBlank(encryptedPassword)) {
            return false;
        }
        return logonPassword.equals(EncryptUtils.decrypt(encryptedPassword));
    }

    @Override
    public boolean checkUserLogonPassword(String logonPassword, String userPhone) {
        if (isBlank(logonPassword) || isBlank(userPhone)) {
            return false;
        }
        User user = userService.getByUserPhone(userPhone);
        if (isNull(user)) {
            return false;
        }
        String encryptedPassword = user.getUserPassword();
        return checkLogonPassword(logonPassword, encryptedPassword);
    }
}
