package luo.com.utils.ui;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import luo.com.utils.Utils;

/**
 * Created by admin on 2016/8/31.
 * 屏幕工具类
 */
public abstract class ScreenUtils {

    /**
     * 获得屏幕宽度
     *
     * @return
     */
    public static int getScreenWidth() {
        WindowManager wm = (WindowManager) Utils.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;


    }

    /**
     * 获得屏幕高度
     *
     * @return
     */
    public static int getScreenHeight() {
        WindowManager wm = (WindowManager) Utils.getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }


    /**
     * dp转px
     *
     * @param dpVal
     * @return
     */
    public static int dpTopx(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, Utils.getContext().getResources().getDisplayMetrics());
    }

    /**
     * dp转px
     *
     * @param dpVal
     * @return
     */
    public static int dpToPx(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, Utils.getContext().getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param spVal
     * @return
     */
    public static int spTopx(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, Utils.getContext().getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param spVal
     * @return
     */
    public static int spToPx(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, Utils.getContext().getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param pxVal
     * @return
     */
    public static float pxTodp(float pxVal) {
        final float scale = Utils.getContext().getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转dp
     *
     * @param pxVal
     * @return
     */
    public static float pxToDp(float pxVal) {
        final float scale = Utils.getContext().getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float pxTosp(float pxVal) {
        return (pxVal / Utils.getContext().getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float pxToSp(float pxVal) {
        return (pxVal / Utils.getContext().getResources().getDisplayMetrics().scaledDensity);
    }
}
