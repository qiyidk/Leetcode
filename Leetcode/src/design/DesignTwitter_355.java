package design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * DesignTwitter_355
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ20ÈÕ
 */
public class DesignTwitter_355 {
    // one thing should be noticed is that one's followers may be very big, but followees usually a relatively small number
    // therefore it would be better to search all the followees' twitters rather than storing current user's twitters for each of his followers when posting a twitter.
    private class Msg{
        private long tid;
        private int id;
        public Msg(long tid, int id){
            this.tid = tid;
            this.id = id;
        }
    }
    
    private Map<Integer, List<Msg>> twitters = new HashMap<Integer, List<Msg>>();
    private Map<Integer, HashSet<Integer>> follows = new HashMap<Integer, HashSet<Integer>>();
    private long tid;// millis may have duplicate value
    /** Initialize your data structure here. */
    public DesignTwitter_355() {
        tid = 0;
    }
    
    /** Compose a new tweet. */
    public synchronized void postTweet(int userId, int tweetId) {
        Msg m = new Msg(tid++, tweetId);
        List<Msg> list = twitters.get(userId);
        if (list == null) {
            list = new ArrayList<Msg>();
            twitters.put(userId, list);
        }
        list.add(m);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        HashSet<Integer> followees1 = follows.get(userId);
        HashSet<Integer> followees = new HashSet<Integer>();
        if (followees1 != null) for (Integer i : followees1) followees.add(i);
        followees.add(userId);
        Msg[] msg = new Msg[10 * followees.size()];
        int p = 0;
        for (int id : followees){
            List<Msg> m = twitters.get(id);
            if (m == null) continue;
            for (int i = m.size() - 1; i >= 0 && i >= m.size() - 10; i--) msg[p++] = m.get(i); //reverse order to get the lastest records
        }
        Msg[] aux = new Msg[p];
        System.arraycopy(msg, 0, aux, 0, p);
        Arrays.sort(aux, (Msg m1, Msg m2) -> {return m2.tid == m1.tid ? 0 : m2.tid > m1.tid ? 1 : -1;});
        List<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < Math.min(10, p); i++) res.add(aux[i].id);// only get 10 lastest records
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        HashSet<Integer> followees = follows.get(followerId);
        if (followees == null) {
            followees = new HashSet<Integer>();
            follows.put(followerId, followees);
        }
        followees.add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        HashSet<Integer> followees = follows.get(followerId);
        if (followees == null) return;
        followees.remove(followeeId);
    }
}
