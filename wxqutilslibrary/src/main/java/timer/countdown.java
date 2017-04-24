//package timer;
//
//import android.os.Handler;
//import android.os.Message;
//
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.TimerTask;
//
///**
// * Created by Administrator on 2017/4/24 0024.
// */
//
//public class countdown {
//
//
//    final Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 1:
//                    String currenttime = getDistanceTime2(timelong);
//                    tv_time.setText("" + currenttime);
//                    if (timelong < 0) {
//                        timer.cancel();
//                        tv_time.setText("00:00");
//
//                    }
//                    break;
//
//                case 3:
//
//                    tv_time2.setText("延时开始了");
//                    if (timelong < 0) {
//                        timer.cancel();
//                        tv_time.setText("00:00");
//
//                    }
//                    break;
//            }
//        }
//    };
//    /**
//     * 两个时间相差距离多少天多少小时多少分多少秒
//     *
//     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
//     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
//     * @return String 返回值为：xx天xx小时xx分xx秒
//     */
//    public static String getDistanceTime(String str1, String str2) {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date one;
//        Date two;
//        long day = 0;
//        long hour = 0;
//        long min = 0;
//        long sec = 0;
//        try {
//            one = df.parse(str1);
//            two = df.parse(str2);
//            long time1 = one.getTime();
//            long time2 = two.getTime();
//            long diff;
//            if (time1 < time2) {
//                diff = time2 - time1;
//            } else {
//                diff = time1 - time2;
//            }
//            day = diff / (24 * 60 * 60 * 1000);
//            hour = (diff / (60 * 60 * 1000) - day * 24);
//            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
//            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
//    }
//
//
//    public static String getDistanceTime2(long diff) {
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date one;
//        Date two;
//        long day = 0;
//        long hour = 0;
//        long min = 0;
//        long sec = 0;
//        try {
//
//            day = diff / (24 * 60 * 60 * 1000);
//            hour = (diff / (60 * 60 * 1000) - day * 24);
//            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
//            sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        String mintime="";
//        if(min<10){
//            mintime="0"+min;
//        }else{
//            mintime=min+"";
//        }
//
//
//        String secTime="";
//        if(min<10){
//            secTime="0"+sec;
//        }else{
//            secTime=sec+"";
//        }
//
//        return   mintime + ":" + secTime + "";
//    }
//
//    private class MyTimerTask2 extends TimerTask {
//        @Override
//        public void run() {
//            timelong=timelong-1000;
//            Message message = new Message();
//            message.what = 2;
//            handler.sendMessage(message);
//        }
//    }
//
//    timer.schedule(new MyTimerTask(),0, 1000);
//}
//==========================================================================================================================================================================================

//方法一
//
//        Timer与TimerTask（Java实现）
//public class timerTask extends Activity{
//
//    private int recLen = 11;
//    private TextView txtView;
//    Timer timer = new Timer();
//
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.timertask);
//        txtView = (TextView)findViewById(R.id.txttime);
//
//        timer.schedule(task, 1000, 1000);       // timeTask
//    }
//
//    TimerTask task = new TimerTask() {
//        @Override
//        public void run() {
//
//            runOnUiThread(new Runnable() {      // UI thread
//                @Override
//                public void run() {
//                    recLen--;
//                    txtView.setText(""+recLen);
//                    if(recLen < 0){
//                        timer.cancel();
//                        txtView.setVisibility(View.GONE);
//                    }
//                }
//            });
//        }
//    };
//}
//
//
//
//方法二
//        TimerTask与Handler（不用Timer的改进型）
//public class timerTask extends Activity{
//    private int recLen = 11;
//    private TextView txtView;
//    Timer timer = new Timer();
//
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.timertask);
//        txtView = (TextView)findViewById(R.id.txttime);
//
//        timer.schedule(task, 1000, 1000);       // timeTask
//    }
//
//    final Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg){
//            switch (msg.what) {
//                case 1:
//                    txtView.setText(""+recLen);
//                    if(recLen < 0){
//                        timer.cancel();
//                        txtView.setVisibility(View.GONE);
//                    }
//            }
//        }
//    };
//
//    TimerTask task = new TimerTask() {
//        @Override
//        public void run() {
//            recLen--;
//            Message message = new Message();
//            message.what = 1;
//            handler.sendMessage(message);
//        }
//    };
//}
//
//
//方法三
//        Handler与Message（不用TimerTask）
//
//public class timerTask extends Activity{
//    private int recLen = 11;
//    private TextView txtView;
//
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.timertask);
//        txtView = (TextView)findViewById(R.id.txttime);
//
//        Message message = handler.obtainMessage(1);     // Message
//        handler.sendMessageDelayed(message, 1000);
//    }
//
//    final Handler handler = new Handler(){
//
//        public void handleMessage(Message msg){         // handle message
//            switch (msg.what) {
//                case 1:
//                    recLen--;
//                    txtView.setText("" + recLen);
//
//                    if(recLen > 0){
//                        Message message = handler.obtainMessage(1);
//                        handler.sendMessageDelayed(message, 1000);      // send message
//                    }else{
//                        txtView.setVisibility(View.GONE);
//                    }
//            }
//
//            super.handleMessage(msg);
//        }
//    };
//}
//
//
//方法四
//
//        Handler与Thread（不占用UI线程）
//public class timerTask extends Activity{
//    private int recLen = 0;
//    private TextView txtView;
//
//    public void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.timertask);
//        txtView = (TextView)findViewById(R.id.txttime);
//
//        new Thread(new MyThread()).start();         // start thread
//    }
//
//    final Handler handler = new Handler(){          // handle
//        public void handleMessage(Message msg){
//            switch (msg.what) {
//                case 1:
//                    recLen++;
//                    txtView.setText("" + recLen);
//            }
//            super.handleMessage(msg);
//        }
//    };
//
//    public class MyThread implements Runnable{      // thread
//        @Override
//        public void run(){
//            while(true){
//                try{
//                    Thread.sleep(1000);     // sleep 1000ms
//                    Message message = new Message();
//                    message.what = 1;
//                    handler.sendMessage(message);
//                }catch (Exception e) {
//                }
//
//
//                方法五
//                Handler与Runnable（最简单型）
//
//                public class timerTask extends Activity{
//                    private int recLen = 0;
//                    private TextView txtView;
//
//                    public void onCreate(Bundle savedInstanceState){
//                        super.onCreate(savedInstanceState);
//
//                        setContentView(R.layout.timertask);
//                        txtView = (TextView)findViewById(R.id.txttime);
//
//                        handler.postDelayed(runnable, 1000);
//                    }
//
//                    Handler handler = new Handler();
//                    Runnable runnable = new Runnable() {
//                        @Override
//                        public void run() {
//                            recLen++;
//                            txtView.setText("" + recLen);
//                            handler.postDelayed(this, 1000);
//                        }
//                    };
//                }