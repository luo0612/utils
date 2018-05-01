package luo.com.utils.view.textview.edittext;

import android.text.InputFilter;
import android.widget.EditText;

import luo.com.utils.java.ArrayUtils;
import luo.com.utils.view.textview.InputFilterUtils;

/**
 * Created by Administrator on 2018/5/1.
 * EditText工具类
 */

public class EditTextUtils {
    /**
     * 设置EditText的最大输入长度
     *
     * @param editText
     * @param maxLength
     */
    public static void setMaxLength(EditText editText, int maxLength) {
        InputFilter[] filters = editText.getFilters();
        filters = ArrayUtils.addAll(filters, InputFilterUtils.getLengthFilter(maxLength));
        editText.setFilters(filters);
    }

    /**
     * 设置EditText表情过滤
     *
     * @param editText
     */
    public static void setFilterEmoji(EditText editText) {
        InputFilter[] filters = editText.getFilters();
        filters = ArrayUtils.addAll(filters, InputFilterUtils.getEmojiFilter());
        editText.setFilters(filters);
    }

    /**
     * EditText添加过滤器
     *
     * @param editText
     * @param addFilters
     */
    public static void setFilters(EditText editText, InputFilter... addFilters) {
        InputFilter[] filters = editText.getFilters();
        filters = ArrayUtils.addAll(filters, addFilters);
        editText.setFilters(filters);
    }
}
