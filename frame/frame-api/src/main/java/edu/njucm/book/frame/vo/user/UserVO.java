package edu.njucm.book.frame.vo.user;

import edu.njucm.book.common.util.ContextUtils;
import edu.njucm.book.user.constant.UserTypeEnum;
import edu.njucm.book.user.domain.User;
import edu.njucm.book.user.service.IUserService;

import static java.util.Objects.isNull;

/**
 * @author lvrongwang
 * @since 2020/5/11 18:46
 */
public class UserVO {

    private Long id;
    private String name;
    private String phone;
    private Short userType;
    private String userTypeName;
    private String managerName;
    private String errorMsg;

    public static UserVO tran2UserVO(User user) {
        UserVO vo = new UserVO();
        vo.setId(user.getUserId());
        vo.setName(user.getUserName());
        vo.setPhone(user.getUserPhone());
        vo.setUserType(user.getUserType());
        vo.setUserTypeName(UserTypeEnum.tran2Name(user.getUserType()));
        User manager = ContextUtils.getContext().getBean(IUserService.class).getByUserId(user.getParentUserId());
        vo.setManagerName(isNull(manager) ? "未绑定主编" : manager.getUserName());
        return vo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Short getUserType() {
        return userType;
    }

    public void setUserType(Short userType) {
        this.userType = userType;
    }

    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", userType=" + userType +
                ", userTypeName='" + userTypeName + '\'' +
                ", managerName='" + managerName + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
