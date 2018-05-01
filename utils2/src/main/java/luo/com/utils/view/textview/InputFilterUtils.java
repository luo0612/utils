package luo.com.utils.view.textview;

import android.text.InputFilter;

import luo.com.utils.view.textview.filter.EmojiFilter;

/**
 * Created by Administrator on 2017/9/25.
 * EditText过滤器工具类
 */

public class InputFilterUtils {
    /**
     * 获取表情过滤器
     *
     * @return
     */
    public static InputFilter getEmojiFilter() {
        return new EmojiFilter();
    }

    /**
     * 获取文本输入长度的过滤器
     *
     * @param maxLength
     * @return
     */
    public static InputFilter getLengthFilter(int maxLength) {
        return new InputFilter.LengthFilter(maxLength);
    }

}
