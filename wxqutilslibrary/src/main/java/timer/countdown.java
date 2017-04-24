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
