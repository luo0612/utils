package luo.com.utils.ui;

import android.widget.Toast;

import luo.com.utils.Utils;

/**
 * Created by admin on 2016/8/31.
 * Toast工具类
 */
public abstract class ToastUtils {


    /**
     * 弹出Toast,短时间
     *
     * @param text 弹出的内容
     */
    public static void showShort(String text) {
        Toast toast = Toast.makeText(Utils.getContext(), text + "", Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 弹出Toast,短时间
     *
     * @param resId 弹出内容的资源ID
     */
    public static void showShort(int resId) {
        String toast = ResourceUtils.getString(resId);
        showShort(toast);
    }

    /**
     * Debug状态弹出Toast, 短时间
     *
     * @param text
     */
    public static void showDebugShort(String text) {
        if (Utils.isDebug()) {
            showShort(text);
        }
    }

    /**
     * Debug状态弹出Toast, 短时间
     *
     * @param resId
     */
    public static void showDebugShort(int resId) {
        if (Utils.isDebug()) {
            showShort(resId);
        }
    }

    /**
     * 弹出Toast,长时间
     *
     * @param text
     */
    public static void showLong(String text) {
        Toast toast = Toast.makeText(Utils.getContext(), text + "", Toast.LENGTH_SHORT);
        toast.show();
    }

    /**
     * 弹出Toast,短时间
     *
     * @param resId
     */
    public static void showLong(int resId) {
        String toast = ResourceUtils.getString(resId);
        showLong(toast);
    }

    /**
     * Debug状态弹出Toast, 长时间
     *
     * @param text
     */
    public static void showDebugLong(String text) {
        if (Utils.isDebug()) {
            showLong(text);
        }
    }

    /**
     * Debug状态弹出Toast, 长时间
     *
     * @param resId
     */
    public static void showDebugLong(int resId) {
        if (Utils.isDebug()) {
            showLong(resId);
        }
    }

}
