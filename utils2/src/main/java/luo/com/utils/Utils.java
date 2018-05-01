package luo.com.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2018/5/1.
 */

public class Utils {
    private static Utils instance = null;
    private Context mContext;
    private boolean mDebug;

    private Utils(Context context, boolean debug) {
        mContext = context;
        mDebug = debug;
    }

    private static Utils getInstance(Application context, boolean debug) {
        if (instance == null) {
            synchronized (Utils.class) {
                Utils tempUtils = instance;
                if (tempUtils == null) {
                    synchronized (Utils.class) {
                        tempUtils = new Utils(context, debug);
                    }
                    instance = tempUtils;
                }
            }
        }
        return instance;
    }

    /**
     * 进行初始化操作
     *
     * @param context
     */
    public static void init(Application context, boolean debug) {
        getInstance(context, debug);
    }

    /**
     * 获取上下文
     *
     * @return
     */
    public static Context getContext() {
        if (instance == null) {
            throw new RuntimeException(Utils.class.getCanonicalName() + "为进行初始化");
        } else {
            return instance.mContext;
        }
    }

    /**
     * 是否是Debug状态
     *
     * @return
     */
    public static boolean isDebug() {
        return instance.mDebug;
    }
}
