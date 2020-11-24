package com.hhd.websocket.bean;

/**
 *
 * @author hengda.hu
 * @date 2020/11/10 11:04
 */
public class Friend {
    private Long id;
    private Long userId;
    private Long friendId;

    public Friend() {
    }

    public Friend(Long id, Long userId, Long friendId) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }
}
