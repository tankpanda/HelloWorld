package com.hhd.websocket.bean;

import java.util.List;

/**
 *
 * @author hengda.hu
 * @date 2020/11/10 14:38
 */
public class FriendVO {
    private User user;
    private List<Friend> friends;

    public FriendVO() {
    }

    public FriendVO(User user, List<Friend> friends) {
        this.user = user;
        this.friends = friends;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Friend> getFriends() {
        return friends;
    }

    public void setFriends(List<Friend> friends) {
        this.friends = friends;
    }
}
