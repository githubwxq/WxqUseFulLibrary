package timer;

public abstract class TimerProcessor {
    /** 
     * 在TimerTask中执行的操作，由调用者定制 
     */  
    public abstract void process();  
} 