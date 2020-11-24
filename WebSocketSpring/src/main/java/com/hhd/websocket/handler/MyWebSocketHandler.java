package com.hhd.websocket.handler;

import com.google.gson.Gson;
import com.hhd.websocket.bean.Friend;
import com.hhd.websocket.bean.FriendVO;
import com.hhd.websocket.bean.MyMessage;
import com.hhd.websocket.bean.User;
import com.hhd.websocket.enums.MessageTypeEnum;
import com.hhd.websocket.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 *
 * @author hengda.hu
 * @date 2020/11/11 9:41
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {

    private static final Map<Long, WebSocketSession> SESSIONS_MAP;

    static {
        SESSIONS_MAP = new ConcurrentHashMap<>();
    }

    @Resource
    private UserService userService;

    /**
     * 建立连接后
     * @param webSocketSession
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        Long userId = (Long) webSocketSession.getAttributes().get("userId");
        SESSIONS_MAP.put(userId, webSocketSession);
        MyMessage myMessage = new MyMessage(0L, "system", userId, "hello", MessageTypeEnum.MSG.getCode());
        webSocketSession.sendMessage(new TextMessage(new Gson().toJson(myMessage)));
        sendOnlineMessage(userId);
    }

    /**
     * 处理消息
     * @param webSocketSession
     * @param webSocketMessage
     * @throws Exception
     */
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        String payload = (String) webSocketMessage.getPayload();
        MyMessage msg = new Gson().fromJson(payload, MyMessage.class);
        if (Long.valueOf(0L).equals(msg.getToUser())) {
            sendMessage2All(webSocketSession, msg.getMessage());
        } else {
            sendMessage2One(webSocketSession, msg.getMessage(), msg.getToUser(), MessageTypeEnum.MSG.getCode());
        }
    }

    /**
     * 处理异常
     * @param webSocketSession
     * @param throwable
     * @throws Exception
     */
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.err.println("error" + throwable.getMessage());
        throwable.printStackTrace();
    }

    /**
     * 连接关闭后
     * @param webSocketSession
     * @param closeStatus
     * @throws Exception
     */
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        Long userId = (Long) webSocketSession.getAttributes().get("userId");
        sendOfflineMessage(webSocketSession);
        SESSIONS_MAP.remove(userId);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    private void sendOnlineMessage(Long userId) {
        List<User> myFriendList = userService.getFriendUserListByUserId(userId);
        myFriendList = myFriendList.stream().filter(x -> SESSIONS_MAP.keySet().contains(x.getId())).collect(Collectors.toList());
        sendMessage2One(0L, new Gson().toJson(myFriendList), userId, MessageTypeEnum.FRIENDS_MY.getCode());
        FriendVO friendVO = userService.getFriendByFriendId(userId);
        List<Friend> collect = friendVO.getFriends().stream().filter(x -> SESSIONS_MAP.keySet().contains(x.getUserId())).collect(Collectors.toList());
        friendVO.getFriends().stream().filter(x -> SESSIONS_MAP.keySet().contains(x.getUserId())).forEach(x -> {
            sendMessage2One(0L, new Gson().toJson(friendVO.getUser()), x.getUserId(), MessageTypeEnum.FRIENDS_ONLINE.getCode());
        });
    }

    private void sendOfflineMessage(WebSocketSession session) {
        Long userId = getUserIdBySession(session);
        FriendVO friendVO = userService.getFriendByFriendId(userId);
        friendVO.getFriends().stream().filter(x -> SESSIONS_MAP.keySet().contains(x.getUserId())).forEach(x -> {
            sendMessage2One(0L, new Gson().toJson(friendVO.getUser()), x.getUserId(), MessageTypeEnum.FRIENDS_OFFLINE.getCode());
        });
    }

    public void sendMessage2One(Long fromUser, String message, Long toUser, Integer type) {
        try {
            WebSocketSession toUserSession = getSessionByUserId(toUser);
            WebSocketSession fromUserSession = getSessionByUserId(fromUser);
            MyMessage msg = new MyMessage(fromUser, userService.getUserByUserId(fromUser).getUsername(), toUser, message, type);
            if (fromUserSession != null) {
                fromUserSession.sendMessage(new TextMessage(new Gson().toJson(msg)));
            }
            if (toUserSession != null) {
                toUserSession.sendMessage(new TextMessage(new Gson().toJson(msg)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage2One(WebSocketSession session, String message, Long toUser, Integer type) {
        try {
            Long fromUser = getUserIdBySession(session);
            WebSocketSession toUserSession = getSessionByUserId(toUser);
            MyMessage msg = new MyMessage(fromUser, userService.getUserByUserId(fromUser).getUsername(), toUser, message, type);
            if (toUserSession != null) {
                toUserSession.sendMessage(new TextMessage(new Gson().toJson(msg)));
            }
            session.sendMessage(new TextMessage(new Gson().toJson(msg)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage2All(WebSocketSession session, String message) {
        SESSIONS_MAP.entrySet().forEach(entry -> {
            try {
                Long fromUser = getUserIdBySession(session);
                Long toUser = entry.getKey();
                WebSocketSession toUserSession = getSessionByUserId(toUser);
                MyMessage msg = new MyMessage(fromUser, userService.getUserByUserId(fromUser).getUsername(), toUser, message, MessageTypeEnum.MSG.getCode());
                toUserSession.sendMessage(new TextMessage(new Gson().toJson(msg)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private WebSocketSession getSessionByUserId(Long userId) {
        Set<Map.Entry<Long, WebSocketSession>> entries = SESSIONS_MAP.entrySet();
        for (Map.Entry<Long, WebSocketSession> entry: entries) {
            if (entry.getKey().equals(userId)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private Long getUserIdBySession(WebSocketSession session) {
        Set<Map.Entry<Long, WebSocketSession>> entries = SESSIONS_MAP.entrySet();
        for (Map.Entry<Long, WebSocketSession> entry: entries) {
            if (entry.getValue().equals(session)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
