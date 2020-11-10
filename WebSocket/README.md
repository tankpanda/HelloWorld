# web socket demo
## 功能点
1. 好友上下线通知
2. 发送消息
## 具体流程
1. 修改com.hhd.websocket.service.UserService中的USER_LIST&FRIEND_LIST，自定义用户和好友
2. 启动服务，访问http://localhost:8080?userId=xx，userId必须在USER_LSIT中已配置
3. 打开新窗口，继续访问http://localhost:8080?userId=xx
4. 窗口下部会显在线好友列表，好友上下线好友列表会实时更新
5. 选择好友发送消息，消息只会显示在好友间
6. 选择all发送消息，消息会在所有在线用户的聊天框显示