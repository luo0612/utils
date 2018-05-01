package luo.com.utils.java;

/**
 * Created by admin on 2016/8/31.
 * 时间工具类
 */
public abstract class TimeUtils {
    /**
     * 持续时间格式化
     *
     * @param time 需要格式化的时间长度,如:100000毫秒
     * @param join 连接符号 如:"-",":"
     * @return 格式化后的事件 如:"hh-mm-ss","hh:mm:ss","mm-ss","mm:ss"
     */
    public static String getFormatHHMMSS(long time, String join) {
        time = time / 1000;
        long hour = time / 3600;
        String hourStr = getHourString(hour);
        long minute = (time - hour * 3600) / 60;
        String minuteStr = getMinuteString(minute);
        long second = time % 60;
        String secondStr = getSecondString(second);
        return hourStr + join + minuteStr + join + secondStr;
    }


    public static String getSecondString(long second) {
        StringBuilder builder = new StringBuilder();
        if (second < 10) {
            builder.append("0").append(second);
        } else {
            builder.append(second);
        }
        return builder.toString();
    }

    public static String getMinuteString(long minute) {
        StringBuilder builder = new StringBuilder();
        if (minute < 10) {
            builder.append("0").append(minute);
        } else {
            builder.append(minute);
        }
        return builder.toString();
    }

    public static String getHourString(long hour) {
        StringBuilder builder = new StringBuilder();
        if (hour == 0) {
            return builder.toString();
        } else if (hour < 10) {
            return builder.append("0").append(hour).toString();
        } else {
            return builder.append(hour).toString();
        }
    }

}
