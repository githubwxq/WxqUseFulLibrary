package emoji;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;

import java.util.Set;

/**
 * @author Army
 * @version V_5.0.0
 * @date 2016/5/28
 * @description 表情过滤器
 */
public class EmojiFilter implements InputFilter {

    // --Commented out by Inspection (2017/2/10 0010 上午 10:15):private Set<String> filterSet = null;

    private void addUnicodeRangeToSet(Set<String> set, int start, int end) {
        if (set == null) {
            return;
        }
        if (start > end) {
            return;
        }

    }

    public EmojiFilter(Context context) {
        super();
//        filterSet = new HashSet<String>();

        // See http://apps.timwhitlock.info/emoji/tables/unicode

        // 1F601 - 1F64F
//        addUnicodeRangeToSet(filterSet, 0x1F601, 0X1F64F);

        // 2702 - 27B0
//        addUnicodeRangeToSet(filterSet, 0x2702, 0X27B0);

        // 1F680 - 1F6C0
//        addUnicodeRangeToSet(filterSet, 0X1F680, 0X1F6C0);

        // 24C2 - 1F251
//        addUnicodeRangeToSet(filterSet, 0X24C2, 0X1F251);

        // 1F600 - 1F636
//        addUnicodeRangeToSet(filterSet, 0X1F600, 0X1F636);

        // 1F681 - 1F6C5
//        addUnicodeRangeToSet(filterSet, 0X1F681, 0X1F6C5);

        // 1F30D - 1F567
//        addUnicodeRangeToSet(filterSet, 0X1F30D, 0X1F567);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart,
                               int dend) {
        // check black-list set
        if (containsEmoji(source.toString())) {
            return "";
        }
        return source;
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000));
    }

}
