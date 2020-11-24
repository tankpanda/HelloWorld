package com.hhd.websocket.enums;

/**
 *
 * @author hengda.hu
 * @date 2020/11/9 18:30
 */
public enum MessageTypeEnum {
    MSG(1, "消息"),
    FRIENDS_MY(2, "我的好友列表"),
    FRIENDS_ONLINE(3, "好友上线"),
    FRIENDS_OFFLINE(4, "好友下线"),
    ;

    private Integer code;
    private String desc;

    MessageTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
