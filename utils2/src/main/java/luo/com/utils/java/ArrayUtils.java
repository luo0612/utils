package luo.com.utils.java;

import java.lang.reflect.Array;

/**
 * Created by Administrator on 2018/5/1.
 * 数组工具类
 */

public class ArrayUtils {
    /**
     * 合并两个数组,来做commons-long3包
     *
     * @param array1
     * @param array2
     * @param <T>
     * @return
     */
    public static <T> T[] addAll(T[] array1, T... array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        } else {
            // getComponentType():
            // 返回数组内部的数据类型, 只作用于Array
            Class<?> type1 = array1.getClass().getComponentType();
            T[] joinedArray = (T[]) Array.newInstance(type1, array1.length + array2.length);
            System.arraycopy(array1, 0, joinedArray, 0, array1.length);

            try {
                System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
                return joinedArray;
            } catch (ArrayStoreException var6) {
                Class<?> type2 = array2.getClass().getComponentType();
                // isAssignableFrom():
                // 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口。
                // 如果是则返回 true；否则返回 false。
                // 如果该 Class 表示一个基本类型，且指定的 Class 参数正是该 Class 对象，则该方法返回 true；否则返回 false
                if (!type1.isAssignableFrom(type2)) {
                    throw new IllegalArgumentException("Cannot store " + type2.getName() + " in an array of " + type1.getName(), var6);
                } else {
                    throw var6;
                }
            }
        }
    }

    static <T> T[] clone(T[] array) {
        return array == null ? null : array.clone();
    }
}
