package luo.com.utils.view.textview.filter;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import luo.com.utils.Utils;

/**
 * Created by Administrator on 2018/5/1.
 * EditText的表情过滤器
 */

public class EmojiFilter implements InputFilter {

    Pattern emoji = Pattern.compile(
            "[\ud83c\udc00-\ud83c\udfff]|" +
                    "[\ud83d\udc00-\ud83d\udfff]|" +
                    "[\u2600-\u27ff]|" +
                    "[\ud83e\udd10]|" +
                    "[\ud83e\udd12-\ud83e\udd18]|" +
                    "[\ud83e\udd80-\ud83e\udd84]|" +
                    "[\ud83e\uddc0]",
            Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher emojiMatcher = emoji.matcher(source);
        if (emojiMatcher.find()) {
            //TODO 不需要提示, 将此处进行注销
            Toast.makeText(Utils.getContext(), "不支持输入表情", Toast.LENGTH_SHORT).show();
            return "";
        }
        return null;
    }
}
