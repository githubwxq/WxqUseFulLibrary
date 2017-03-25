package klog;


import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * This is a Log tool，with this you can the following
 * <ol>
 * <li>use KLog.d(),you could print whether the method execute,and the default tag is current class's name</li>
 * <li>use KLog.d(msg),you could print log as before,and you could location the method with a click in Android Studio Logcat</li>
 * <li>use KLog.json(),you could print json string with well format automatic</li>
 * </ol>
 *
 * @author zhaokaiqiang
 *         github https://github.com/ZhaoKaiQiang/KLog
 *         15/11/17 扩展功能，添加对文件的支持
 *         15/11/18 扩展功能，增加对XML的支持，修复BUG
 *         15/12/8  扩展功能，添加对任意参数的支持
 *         15/12/11 扩展功能，增加对无限长字符串支持
 *         16/6/13  扩展功能，添加对自定义全局Tag的支持,修复内部类不能点击跳转的BUG
 *         16/6/15  扩展功能，添加不能关闭的KLog.debug(),用于发布版本的Log打印,优化部分代码
 *         16/6/20  扩展功能，添加堆栈跟踪功能KLog.trace()
 */
public final class KLog {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final String NULL_TIPS = "Log with null object";

    private static final String DEFAULT_MESSAGE = "execute";
    private static final String PARAM = "Param";
    private static final String NULL = "null";
    private static final String TAG_DEFAULT = "KLog";
    private static final String SUFFIX = ".java";

    public static final int JSON_INDENT = 4;

    public static final int V = 0x1;
    public static final int D = 0x2;
    public static final int I = 0x3;
    public static final int W = 0x4;
    public static final int E = 0x5;
    public static final int A = 0x6;

    private static final int JSON = 0x7;
    private static final int XML = 0x8;

    private static final int STACK_TRACE_INDEX_5 = 5;
    private static final int STACK_TRACE_INDEX_4 = 4;

    private static String mGlobalTag;
    private static boolean mIsGlobalTagEmpty = true;
    private static boolean IS_SHOW_LOG = true;

    public static void init(boolean isShowLog) {
        IS_SHOW_LOG = isShowLog;
    }

    public static void init(boolean isShowLog, @Nullable String tag) {
        IS_SHOW_LOG = isShowLog;
        mGlobalTag = tag;
        mIsGlobalTagEmpty = TextUtils.isEmpty(mGlobalTag);
    }

    public static void v() {
        printLog(V, null, DEFAULT_MESSAGE);
    }

    public static void v(Object msg) {
        printLog(V, null, msg);
    }

    public static void v(String tag, Object... objects) {
        printLog(V, tag, objects);
    }

    public static void d() {
        printLog(D, null, DEFAULT_MESSAGE);
    }

    public static void d(Object msg) {
        printLog(D, null, msg);
    }

    public static void d(String tag, Object... objects) {
        printLog(D, tag, objects);
    }

    public static void i() {
        printLog(I, null, DEFAULT_MESSAGE);
    }

    public static void i(Object msg) {
        printLog(I, null, msg);
    }

    public static void i(String tag, Object... objects) {
        printLog(I, tag, objects);
    }

    public static void w() {
        printLog(W, null, DEFAULT_MESSAGE);
    }

    public static void w(Object msg) {
        printLog(W, null, msg);
    }

    public static void w(String tag, Object... objects) {
        printLog(W, tag, objects);
    }

    public static void e() {
        printLog(E, null, DEFAULT_MESSAGE);
    }

    public static void e(Object msg) {
        printLog(E, null, msg);
    }

    public static void e(String tag, Object... objects) {
        printLog(E, tag, objects);
    }

    public static void a() {
        printLog(A, null, DEFAULT_MESSAGE);
    }

    public static void a(Object msg) {
        printLog(A, null, msg);
    }

    public static void a(String tag, Object... objects) {
        printLog(A, tag, objects);
    }

    public static void json(String jsonFormat) {
        printLog(JSON, null, jsonFormat);
    }

    public static void json(String tag, String jsonFormat) {
        printLog(JSON, tag, jsonFormat);
    }

    public static void xml(String xml) {
        printLog(XML, null, xml);
    }

    public static void xml(String tag, String xml) {
        printLog(XML, tag, xml);
    }

    public static void file(File targetDirectory, Object msg) {
        printFile(null, targetDirectory, null, msg);
    }

    public static void file(String tag, File targetDirectory, Object msg) {
        printFile(tag, targetDirectory, null, msg);
    }

    public static void file(String tag, File targetDirectory, String fileName, Object msg) {
        printFile(tag, targetDirectory, fileName, msg);
    }

    public static void debug() {
        printDebug(null, DEFAULT_MESSAGE);
    }

    public static void debug(Object msg) {
        printDebug(null, msg);
    }

    public static void debug(String tag, Object... objects) {
        printDebug(tag, objects);
    }

    public static void trace() {
        printStackTrace();
    }

    private static void printStackTrace() {

        if (!IS_SHOW_LOG) {
            return;
        }

        Throwable tr = new Throwable();
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        String message = sw.toString();

        String traceString[] = message.split("\\n\\t");
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (String trace : traceString) {
            if (trace.contains("at com.socks.library.KLog")) {
                continue;
            }
            sb.append(trace).append("\n");
        }
        String[] contents = wrapperContent(STACK_TRACE_INDEX_4, null, sb.toString());
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];
        BaseLog.printDefault(D, tag, headString + msg);
    }

    private static void printLog(int type, String tagStr, Object... objects) {

        if (!IS_SHOW_LOG) {
            return;
        }

        String[] contents = wrapperContent(STACK_TRACE_INDEX_5, tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        switch (type) {
            case V:
            case D:
            case I:
            case W:
            case E:
            case A:
                BaseLog.printDefault(type, tag, headString + msg);
                break;
            case JSON:
                JsonLog.printJson(tag, msg, headString);
                break;
            case XML:
                XmlLog.printXml(tag, msg, headString);
                break;
        }

    }

    private static void printDebug(String tagStr, Object... objects) {
        String[] contents = wrapperContent(STACK_TRACE_INDEX_5, tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];
        BaseLog.printDefault(D, tag, headString + msg);
    }


    private static void printFile(String tagStr, File targetDirectory, String fileName, Object objectMsg) {

        if (!IS_SHOW_LOG) {
            return;
        }

        String[] contents = wrapperContent(STACK_TRACE_INDEX_5, tagStr, objectMsg);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];

        FileLog.printFile(tag, targetDirectory, fileName, headString, msg);
    }

    private static String[] wrapperContent(int stackTraceIndex, String tagStr, Object... objects) {

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        StackTraceElement targetElement = stackTrace[stackTraceIndex];
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
        }

        if (className.contains("$")) {
            className = className.split("\\$")[0] + SUFFIX;
        }

        String methodName = targetElement.getMethodName();
        int lineNumber = targetElement.getLineNumber();

        if (lineNumber < 0) {
            lineNumber = 0;
        }

        String tag = (tagStr == null ? className : tagStr);

        if (mIsGlobalTagEmpty && TextUtils.isEmpty(tag)) {
            tag = TAG_DEFAULT;
        } else if (!mIsGlobalTagEmpty) {
            tag = mGlobalTag;
        }

        String msg = (objects == null) ? NULL_TIPS : getObjectsString(objects);
        String headString = "[ (" + className + ":" + lineNumber + ")#" + methodName + " ] ";

        return new String[]{tag, msg, headString};
    }

    private static String getObjectsString(Object... objects) {

        if (objects.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object == null) {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(NULL).append("\n");
                } else {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
                }
            }
            return stringBuilder.toString();
        } else {
            Object object = objects[0];
            return object == null ? NULL : object.toString();
        }
    }

}

// shi yong


//
//public class KLogApplication extends Application {
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
////        KLog.init(BuildConfig.LOG_DEBUG);
//        KLog.init(BuildConfig.LOG_DEBUG, "Kai");
//    }
//}

//    public void logTraceStack(View view) {
//        TestTraceUtil.testTrace();
//    }
//
//    public void logDebug(View view) {
//        KLog.debug();
//        KLog.debug("This is a debug message");
//        KLog.debug("DEBUG", "params1", "params2", this);
//    }
//
//    public void log(View view) {
//        KLog.v();
//        KLog.d();
//        KLog.i();
//        KLog.w();
//        KLog.e();
//        KLog.a();
//    }
//
//    public void logWithNull(View view) {
//        KLog.v(null);
//        KLog.d(null);
//        KLog.i(null);
//        KLog.w(null);
//        KLog.e(null);
//        KLog.a(null);
//    }
//
//    public void logWithMsg(View view) {
//        KLog.v(LOG_MSG);
//        KLog.d(LOG_MSG);
//        KLog.i(LOG_MSG);
//        KLog.w(LOG_MSG);
//        KLog.e(LOG_MSG);
//        KLog.a(LOG_MSG);
//    }
//
//    public void logWithTag(View view) {
//        KLog.v(TAG, LOG_MSG);
//        KLog.d(TAG, LOG_MSG);
//        KLog.i(TAG, LOG_MSG);
//        KLog.w(TAG, LOG_MSG);
//        KLog.e(TAG, LOG_MSG);
//        KLog.a(TAG, LOG_MSG);
//    }
//
//    public void logWithLong(View view) {
//        KLog.d(TAG, STRING_LONG);
//    }
//
//    public void logWithParams(View view) {
//        KLog.v(TAG, LOG_MSG, "params1", "params2", this);
//        KLog.d(TAG, LOG_MSG, "params1", "params2", this);
//        KLog.i(TAG, LOG_MSG, "params1", "params2", this);
//        KLog.w(TAG, LOG_MSG, "params1", "params2", this);
//        KLog.e(TAG, LOG_MSG, "params1", "params2", this);
//        KLog.a(TAG, LOG_MSG, "params1", "params2", this);
//    }
//
//
//    public void logWithJson(View view) {
//        KLog.json("12345");
//        KLog.json(null);
//        KLog.json(JSON);
//    }
//
//    public void logWithLongJson(View view) {
//        KLog.json(JSON_LONG);
//    }
//
//    public void logWithJsonTag(View view) {
//        KLog.json(TAG, JSON);
//    }
//
//    public void logWithFile(View view) {
//        KLog.file(Environment.getExternalStorageDirectory(), JSON_LONG);
//        KLog.file(TAG, Environment.getExternalStorageDirectory(), JSON_LONG);
//        KLog.file(TAG, Environment.getExternalStorageDirectory(), "test.txt", JSON_LONG);
//    }
//
//    public void logWithXml(View view) {
//        KLog.xml("12345");
//        KLog.xml(null);
//        KLog.xml(XML);
//    }
//
//    public void logWithXmlFromNet(View view) {
//        httpClient.get(this, URL_XML, new TextHttpResponseHandler() {
//            @Override
//            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                KLog.e(responseString);
//            }
//
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                KLog.xml(responseString);
//            }
//        });
//    }