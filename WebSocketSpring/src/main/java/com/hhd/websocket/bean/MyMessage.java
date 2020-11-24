package com.hhd.websocket.bean;

/**
 *
 * @author hengda.hu
 * @date 2020/11/9 16:49
 */
public class MyMessage {
    private Long fromUser;
    private String fromUsername;
    private Long toUser;
    private String message;
    private Integer type;

    public MyMessage() {
    }

    public MyMessage(Long fromUser, String fromUsername, Long toUser, String message, Integer type) {
        this.fromUser = fromUser;
        this.fromUsername = fromUsername;
        this.toUser = toUser;
        this.message = message;
        this.type = type;
    }

    public Long getFromUser() {
        return fromUser;
    }

    public void setFromUser(Long fromUser) {
        this.fromUser = fromUser;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public Long getToUser() {
        return toUser;
    }

    public void setToUser(Long toUser) {
        this.toUser = toUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
