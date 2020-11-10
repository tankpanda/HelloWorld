package com.hhd.websocket.controller;

import com.google.gson.Gson;
import com.hhd.websocket.bean.Friend;
import com.hhd.websocket.bean.MyMessage;
import com.hhd.websocket.bean.User;
import com.hhd.websocket.enums.MessageTypeEnum;
import com.hhd.websocket.service.UserService;
import com.hhd.websocket.vo.FriendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author hengda.hu
 * @date 2020/11/9 13:29
 */
@ServerEndpoint("/ws/{userId}")
@Component
public class WebSocketServer {

    private static UserService userService;

    private static final AtomicInteger ONLINE_COUNT;
    private static final Map<Long, Session> SESSIONS_MAP;

    static {
        ONLINE_COUNT = new AtomicInteger(0);
        SESSIONS_MAP = new ConcurrentHashMap<>();
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId) {
        SESSIONS_MAP.put(userId, session);
        int current = ONLINE_COUNT.incrementAndGet();
        System.out.println("open 当前在线数量：" + current);
        sendMessage2One(0L, "hello", userId, MessageTypeEnum.MSG.getCode());
        sendOnlineMessage(userId);
    }

    @OnClose
    public void onClose(Session session) {
        sendOfflineMessage(session);
        SESSIONS_MAP.remove(getUserIdBySession(session));
        int current = ONLINE_COUNT.decrementAndGet();
        System.out.println("close 当前在线数量：" + current);
    }


    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println("get msg：" + message);
        MyMessage msg = new Gson().fromJson(message, MyMessage.class);
        if (Long.valueOf(0L).equals(msg.getToUser())) {
            sendMessage2All(session, msg.getMessage());
        } else {
            sendMessage2One(session, msg.getMessage(), msg.getToUser(), MessageTypeEnum.MSG.getCode());
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.err.println("error:" + error.getMessage());
        error.printStackTrace();
    }

    public void sendMessage2One(Long fromUser, String message, Long toUser, Integer type) {
        try {
            Session toUserSession = getSessionByUserId(toUser);
            Session fromUserSession = getSessionByUserId(fromUser);
            MyMessage msg = new MyMessage(fromUser, toUser, message, type);
            if (fromUserSession != null) {
                fromUserSession.getBasicRemote().sendText(new Gson().toJson(msg));
            }
            if (toUserSession != null) {
                toUserSession.getBasicRemote().sendText(new Gson().toJson(msg));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage2One(Session session, String message, Long toUser, Integer type) {
        try {
            Long fromUser = getUserIdBySession(session);
            Session toUserSession = getSessionByUserId(toUser);
            MyMessage msg = new MyMessage(fromUser, toUser, message, type);
            if (toUserSession != null) {
                toUserSession.getBasicRemote().sendText(new Gson().toJson(msg));
            }
            session.getBasicRemote().sendText(new Gson().toJson(msg));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage2All(Session session, String message) {
        SESSIONS_MAP.entrySet().forEach(entry -> {
            try {
                Long fromUser = getUserIdBySession(session);
                Long toUser = entry.getKey();
                Session toUserSession = getSessionByUserId(toUser);
                MyMessage msg = new MyMessage(fromUser, toUser, message, MessageTypeEnum.MSG.getCode());
                toUserSession.getBasicRemote().sendText(new Gson().toJson(msg));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Session getSessionByUserId(Long userId) {
        Set<Map.Entry<Long, Session>> entries = SESSIONS_MAP.entrySet();
        for (Map.Entry<Long, Session> entry: entries) {
            if (entry.getKey().equals(userId)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private Long getUserIdBySession(Session session) {
        Set<Map.Entry<Long, Session>> entries = SESSIONS_MAP.entrySet();
        for (Map.Entry<Long, Session> entry: entries) {
            if (entry.getValue().equals(session)) {
               return entry.getKey();
            }
        }
        return null;
    }

    private void sendOnlineMessage(Long userId) {
        List<User> myFriendList = userService.getUserListByUserId(userId);
        myFriendList = myFriendList.stream().filter(x -> SESSIONS_MAP.keySet().contains(x.getId())).collect(Collectors.toList());
        sendMessage2One(0L, new Gson().toJson(myFriendList), userId, MessageTypeEnum.FRIENDS_MY.getCode());
        FriendVO friendVO = userService.getFriendByFriendId(userId);
        List<Friend> collect = friendVO.getFriends().stream().filter(x -> SESSIONS_MAP.keySet().contains(x.getUserId())).collect(Collectors.toList());
        friendVO.getFriends().stream().filter(x -> SESSIONS_MAP.keySet().contains(x.getUserId())).forEach(x -> {
            sendMessage2One(0L, new Gson().toJson(friendVO.getUser()), x.getUserId(), MessageTypeEnum.FRIENDS_ONLINE.getCode());
        });
    }

    private void sendOfflineMessage(Session session) {
        Long userId = getUserIdBySession(session);
        FriendVO friendVO = userService.getFriendByFriendId(userId);
        friendVO.getFriends().stream().filter(x -> SESSIONS_MAP.keySet().contains(x.getUserId())).forEach(x -> {
            sendMessage2One(0L, new Gson().toJson(friendVO.getUser()), x.getUserId(), MessageTypeEnum.FRIENDS_OFFLINE.getCode());
        });


//        List<Friend> friendMyList = userService.getFriendByFriendId(userId);
//        friendMyList = friendMyList.stream().filter(x -> SESSIONS_MAP.keySet().contains(x.getFriendId())).collect(Collectors.toList());
//        friendMyList.forEach(x -> {
//            sendMessage2One(0L, new Gson().toJson(x), x.getUserId(), MessageTypeEnum.FRIENDS_OFFLINE.getCode());
//        });
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
