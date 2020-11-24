package com.hhd.websocket.service;

import com.hhd.websocket.bean.Friend;
import com.hhd.websocket.bean.FriendVO;
import com.hhd.websocket.bean.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author hengda.hu
 * @date 2020/11/10 11:00
 */
@Service
public class UserService {

    private static final List<User> USER_LIST;
    private static final List<Friend> FRIEND_LIST;

    static {
        // TODO 可以改造成mysql
        USER_LIST = new ArrayList<>();
        User system = new User(0L, "system", null);
        User hhd = new User(1L, "hhd", "hhd");
        User zs = new User(2L, "zhangsan" ,"zhangsan");
        User ls = new User(3L, "lisi" ,"lisi");
        User ww = new User(4L, "wangwu" ,"wangyu");
        USER_LIST.add(system);
        USER_LIST.add(hhd);
        USER_LIST.add(zs);
        USER_LIST.add(ls);
        USER_LIST.add(ww);

        FRIEND_LIST = new ArrayList<>();
        Friend f1 = new Friend(1L, 1L, 2L);
        Friend f2 = new Friend(2L, 1L, 3L);
        Friend f3 = new Friend(3L, 1L, 4L);
        Friend f4 = new Friend(4L, 2L, 1L);
        Friend f5 = new Friend(5L, 2L, 3L);
        Friend f6 = new Friend(6L, 2L, 4L);
        Friend f7 = new Friend(7L, 3L, 1L);
        Friend f8 = new Friend(8L, 3L, 2L);
        Friend f9 = new Friend(9L, 3L, 4L);
        Friend f10 = new Friend(10L, 4L, 1L);
        Friend f11 = new Friend(11L, 4L, 2L);
        Friend f12 = new Friend(12L, 4L, 3L);
        FRIEND_LIST.add(f1);
        FRIEND_LIST.add(f2);
        FRIEND_LIST.add(f3);
        FRIEND_LIST.add(f4);
        FRIEND_LIST.add(f5);
        FRIEND_LIST.add(f6);
        FRIEND_LIST.add(f7);
        FRIEND_LIST.add(f8);
        FRIEND_LIST.add(f9);
        FRIEND_LIST.add(f10);
        FRIEND_LIST.add(f11);
        FRIEND_LIST.add(f12);
    }

    public User login(String username, String password) {
        User user = USER_LIST.stream().filter(x -> username.equals(x.getUsername()) && password.equals(x.getPassword())).findAny().orElse(null);
        return user;
    }

    public User getUserByUserId(Long userId) {
        User user = USER_LIST.stream().filter(x -> userId.equals(x.getId())).findFirst().get();
        return user;
    }

    public List<User> getFriendUserListByUserId(Long userId) {
        List<User> users = FRIEND_LIST.stream().filter(x -> userId.equals(x.getUserId())).map(x -> USER_LIST.stream().filter(y -> x.getFriendId().equals(y.getId())).findFirst().orElse(null)).collect(Collectors.toList());
        return users;
    }

    public FriendVO getFriendByFriendId(Long friendId) {
        List<Friend> friendList = FRIEND_LIST.stream().filter(x -> friendId.equals(x.getFriendId())).collect(Collectors.toList());
        User user = USER_LIST.stream().filter(x -> friendId.equals(x.getId())).findFirst().get();
        return new FriendVO(user, friendList);
    }

}
