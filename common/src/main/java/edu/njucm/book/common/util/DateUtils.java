package edu.njucm.book.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lvrongwang
 * @since 2020/4/16 16:20
 */
public class DateUtils {

    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);

    private static String DATE_FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    private static final ThreadLocal<SimpleDateFormat> DATE_TIME_DATE_FORMAT = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat(DATE_FORMAT_DATETIME);
        }
    };

    /**
     * 解析yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static Date parseTime(String time) {
        try {
            return DATE_TIME_DATE_FORMAT.get().parse(time);
        } catch (ParseException e) {
            logger.error("Fail to parseTime {}", time, e);
        }
        return null;
    }

    /**
     * 解析yyyy-MM-dd HH:mm:ss
     *
     * @param time
     * @return
     */
    public static String formatTime(Date time) {
        try {
            return DATE_TIME_DATE_FORMAT.get().format(time);
        } catch (Exception e) {
            logger.error("Fail to formatTime {}", time, e);
        }
        return null;
    }
}
