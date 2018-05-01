package luo.com.utils.ui;

import android.graphics.drawable.Drawable;

import luo.com.utils.Utils;

/**
 * Created by admin on 2016/8/31.
 * 资源工具类
 */
public abstract class ResourceUtils {

    /**
     * 通过资源id获取到颜色值,在应用中使用时,建议对其进行再次封装
     *
     * @param colorResId 颜色的资源id
     * @return
     */
    public static int getColor(int colorResId) {
        return Utils.getContext().getResources().getColor(colorResId);
    }

    /**
     * 通过资源id获取到图片对象,在应用中使用时,建议对其进行再次封装
     *
     * @param drawableResId 图片的资源id
     * @return
     */
    public static Drawable getDrawable(int drawableResId) {
        Drawable drawable = Utils.getContext().getResources().getDrawable(drawableResId);
        return drawable;
    }

    /**
     * 通过资源id获取到String对象,在应用中使用时,建议对其进行再次封装
     *
     * @param stringResId
     * @return
     * @describe 获取String资源文件的字段
     * @call
     */
    public static String getString(int stringResId) {
        return Utils.getContext().getResources().getString(stringResId);
    }
}
