package timer;

import java.util.Timer;
import java.util.TimerTask;

public class TimerHelper {
      
    private TimerProcessor mProcessor;  
      
    private int mDelayMs;  
      
    private Timer mTimer;
      
    private TimerTask mTimerTask;

    private int mPeriod;
    /** 
     * 构造函数 
     * @param delayMs 延时 
     * @param processor 定时处理器，由调用者定制实现 
     */  
    public TimerHelper(int delayMs, TimerProcessor processor) {  
        mProcessor = processor;  
        mDelayMs = delayMs;  
    }
    public TimerHelper(int delayMs, TimerProcessor processor,int period) {
        mProcessor = processor;
        mDelayMs = delayMs;
        mPeriod=period;
    }

    /** 
     * 启动定时器 
     */  
    public void startTimer() {  
        mTimer = new Timer(true);  
        mTimerTask = new TimerTask() {  
  
            @Override  
            public void run() {  
                if (mProcessor != null) {  
                    mProcessor.process();  
                }  
            }  
              
        };  

        if(mPeriod==0){
          //  、、延时任务
            mTimer.schedule(mTimerTask, mDelayMs);
        }else{
         //、定时任务
            mTimer.schedule(mTimerTask, mDelayMs,mPeriod);
        }
    }  
      
    /** 
     * 停止定时器 
     */  
    public void stopTimer() {  
        if (mTimer != null) {  
            mTimer.cancel();  
            mTimer = null;  
        }  
        if (mTimerTask != null) {  
            mTimerTask.cancel();  
            mTimerTask = null;  
        }  
    }  
  
}  