package edu.njucm.book.user.web.util;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

/**
 * @author lvrongwang
 * @since 2020/4/16 15:29
 */
public class LoginUrlControl implements ILoginUrlControl {

    private List<String> excludeLoginUrl;

    @Override
    public boolean needLogin(String url) {
        if (isEmpty(excludeLoginUrl)) {
            return true;
        }
        if (isBlank(url)) {
            return true;
        }
        for (String noNeedLoginUrl : excludeLoginUrl) {
            if (url.matches(noNeedLoginUrl) || url.equals("/")) {
                return false;
            }
        }
        return true;
    }

    public List<String> getExcludeLoginUrl() {
        return excludeLoginUrl;
    }

    public void setExcludeLoginUrl(List<String> excludeLoginUrl) {
        this.excludeLoginUrl = excludeLoginUrl;
    }
}
