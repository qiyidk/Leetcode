package design;

import java.util.HashMap;

/**
 * <p>
 * LoggerRateLimiter_359
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ16ÈÕ
 */
public class LoggerRateLimiter_359 {
    
    private HashMap<String, Integer> lastTime;
    /** Initialize your data structure here. */
    public LoggerRateLimiter_359() {
        lastTime = new HashMap<String, Integer>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false. The timestamp is in seconds granularity. */
    public synchronized boolean shouldPrintMessage(int timestamp, String message) {
        Integer lastTime = this.lastTime.get(message);
        if (lastTime == null || timestamp - lastTime >= 10) {
            this.lastTime.put(message, timestamp);
            return true;
        }
        return false;
    }
}
