package edu.njucm.book.common.util;

import static edu.njucm.book.common.constant.Constants.EMPTY;
import static edu.njucm.book.common.util.EncryptUtils.base64Decrypt;
import static edu.njucm.book.common.util.EncryptUtils.base64Encrypt;
import static java.util.Objects.isNull;

/**
 * @author lvrongwang
 * @since 2020/4/19 0:46
 */
public class PicUtils {

    /**
     * 获取jpg图片字节码
     *
     * @param base64
     * @return
     */
    public static byte[] getPicByteByBase64(String base64) {
        String str = base64.substring(base64.indexOf(",") + 1);
        return base64Decrypt(str);
    }

    /**
     * 获取jpg图片base64码
     *
     * @param bytes
     * @return
     */
    public static String getPicBase64Str(byte[] bytes) {
        if (isNull(bytes)) {
            return EMPTY;
        }
        String str = base64Encrypt(bytes);
        return "data:image/jpeg;base64," + str;
    }
}
