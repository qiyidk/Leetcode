package design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * <p>
 * DesignTwitter_355
 * </p>
 *
 * @author qiyi
 * @version 2016Äê6ÔÂ20ÈÕ
 */
public class DesignTwitter_355 {
    private HashMap<Integer, User> users;
    private long timestamp = 0L;
    public synchronized long getTimestamp(){
        return timestamp++;
    }
    private class Tweet implements Comparable<Tweet>{
        private int tweetId;
        private int userId;
        private long time;
        public Tweet(int userId, int tweetId){
            this.userId = userId;
            this.tweetId = tweetId;
            this.time = getTimestamp();
        }
        public int compareTo(Tweet that){
            if (this.time > that.time) return -1;
            else if (this.time < that.time) return 1;
            else return 0;
        }
    } 
    private class User{
        private int userId;
        private List<Tweet> tweets;
        private Set<Integer> follows;
        public User(int userId){
            this.userId = userId;
            tweets = new ArrayList<Tweet>();
            follows = new HashSet<Integer>();
            follows.add(userId);
        }
        public void postTweet(int tweetId){
            tweets.add(new Tweet(userId, tweetId));
        }
        // tweets for each user are ordered naturally, we don't need put all tweets into the heap
        public List<Integer> getNewsFeed(){
            PriorityQueue<Tweet> pq = new PriorityQueue<Tweet>();
            List<Integer> res = new ArrayList<Integer>();
            int n = follows.size();
            int[] p = new int[n]; // pointer for each tweet list
            List<List<Tweet>> userTweets = new ArrayList<List<Tweet>>();
            HashMap<Integer, Integer> locater = new HashMap<Integer, Integer>();// userid -> array index
            for (int userId : follows){
                List<Tweet> list = users.get(userId).getTweets();
                int index = userTweets.size();
                locater.put(userId, index);
                p[index] = list.size() - 1;
                if (p[index] >= 0) pq.add(list.get(p[index]));
                userTweets.add(list);
            }
            for (int i = 0; i < 10; i++){
                if (pq.isEmpty()) break;
                Tweet t = pq.remove();
                res.add(t.tweetId);
                int index = locater.get(t.userId);
                p[index]--;
                if (p[index] >= 0) pq.add(userTweets.get(index).get(p[index]));
            }
            return res;
        }
        public List<Tweet> getTweets(){
            return tweets;
        }
        public void follow(int userId){
            follows.add(userId);
        }
        public void unfollow(int userId){
            if (userId == this.userId) return;
            if (!follows.contains(userId)) return;
            follows.remove(userId);
        }
    }
    /** Initialize your data structure here. */
    public DesignTwitter_355() {
        users = new HashMap<Integer, User>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        User u = users.get(userId);
        if (u == null) {
            u = new User(userId);
            users.put(userId, u);
        }
        u.postTweet(tweetId);
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        User u = users.get(userId);
        if (u == null) {
            u = new User(userId);
            users.put(userId, u);
        }
        return u.getNewsFeed();
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        User u1 = users.get(followerId);
        if (u1 == null) {
            u1 = new User(followerId);
            users.put(followerId, u1);
        }
        User u2 = users.get(followeeId);
        if (u2 == null) {
            u2 = new User(followeeId);
            users.put(followeeId, u2);
        }
        u1.follow(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        User u1 = users.get(followerId);
        if (u1 == null) {
            u1 = new User(followerId);
            users.put(followerId, u1);
        }
        User u2 = users.get(followeeId);
        if (u2 == null) {
            u2 = new User(followeeId);
            users.put(followeeId, u2);
        }
        u1.unfollow(followeeId);
    }
}
