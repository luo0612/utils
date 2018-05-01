package luo.com.utils.android;

import android.os.Build;
import android.provider.Settings;

import luo.com.utils.Utils;

/**
 * Created by admin on 2016/8/31.
 * 手机工具类
 * 1.可以判断当前是否处于飞行模式
 */
public abstract class TelephoneUtils {

    /**
     * 判断手机当前是否处于飞行模式
     *
     * @return
     */
    public static boolean isAirolaneMode() {
        int isAirplaneMode = -1;
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            isAirplaneMode = Settings.System.getInt(Utils.getContext().getContentResolver(), Settings.Global.AIRPLANE_MODE_ON, 0);
        } else {
            isAirplaneMode = Settings.System.getInt(Utils.getContext().getContentResolver(), Settings.System.AIRPLANE_MODE_ON, 0);
        }
        return isAirplaneMode == 1;
    }

    /**
     * 获取Android设备唯一标识
     *
     * @return
     */
    public static String getAndroidId() {
        String androidId = Settings.Secure.getString(Utils.getContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return androidId;
    }
}
