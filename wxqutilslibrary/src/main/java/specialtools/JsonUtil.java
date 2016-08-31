
package specialtools;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ��Ŀ���ƣ�UtilsLib
 * ���ߣ�lb291
 * ���䣺 lb291700351@live.cn
 * ʱ�䣺2016/5/25 16:29
 * ��������Json��������ع����࣬�ײ�ʹ�õ���Gson����ֹʵ��������
 */
public class JsonUtil {
    private static final String TAG = "JsonUtil";

    /**
     * ��ֹʵ��������
     */
    private JsonUtil() {
        throw new UnsupportedOperationException(
                "The class " + getClass().getSimpleName() + " can not be instantiated!");
    }

    /**
     * ��һ��map���json�ַ���
     *
     * @param map ��װ�����ݵ�map����
     * @return ���ݶ�Ӧ��Json�ַ�����ʽ
     */
    public static String parseMapToJson(Map<?, ?> map) {
        try {
            Gson gson = new Gson();
            return gson.toJson(map);
        } catch (Exception e) {
           // LogUtil.log(TAG, e);
        }
        return "";
    }


    /**
     * ��һ��json�ַ���ת����ָ����JavaBean����
     *
     * @param json ��Ҫת����json�ַ���
     * @param cls  json�ַ�����Ӧ��JavBean
     * @param <T>  ָ������������
     * @return ��װ�����ݵ�JavaBean����
     */
    public static <T> T jsonToBean(String json, Class<T> cls) {
        Gson gson = new Gson();
        T t = null;
        try {
            t = gson.fromJson(json, cls);
        } catch (Exception e) {
           // LogUtil.log(TAG, e);
        }
        return t;
    }

    /**
     * ��json�ַ������map��ʽ
     *
     * @param json ��Ҫת����Json
     * @return map��װ������
     */
    public static HashMap<String, Object> parseJsonToMap(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        HashMap<String, Object> map = null;
        try {
            map = gson.fromJson(json, type);
        } catch (Exception e) {
         //   LogUtil.log(TAG, e);
        }
        return map;
    }


    /**
     * ��json�ַ�����ɼ���<br>
     * ����ʾ����new TypeToken&lt;List&lt;YourBean&gt;&gt;(){}.getType()
     *
     * @param json ��Ҫת����Json�ַ���
     * @param type ʾ����new TypeToken&lt;List&lt;YourBean&gt;&gt;(){}.getType()
     * @return ���ݼ���
     */
    public static List<?> parseJsonToList(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    /**
     * ��ȡjson����ĳ���ֶε�ֵ��ע�⣬ֻ�ܻ�ȡͬһ�㼶��value
     *
     * @param json json�ַ���
     * @param key  json����һ����bean
     * @return key��Ӧ��ֵ
     */
    public static String getFieldValue(String json, String key) {
        if (TextUtils.isEmpty(json))
            return null;
        if (!json.contains(key))
            return "";
        JSONObject jsonObject;
        String value = null;
        try {
            jsonObject = new JSONObject(json);
            value = jsonObject.getString(key);
        } catch (JSONException e) {
            //LogUtil.log(TAG, e);
        }
        return value;
    }

}
