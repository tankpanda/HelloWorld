package com.hhd.websocket.interceptor;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * TODO
 *
 * @author hengda.hu
 * @date 2020/11/11 15:12
 */
@Component
public class WebSocketInterceptor extends HttpSessionHandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        System.out.println("start handshake");
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
//            HttpSession session = serverRequest.getServletRequest().getSession(false);
//            if (session == null) {
//                return false;
//            }
//            Long userId = (Long) session.getAttribute("userId");
            Long userId = Long.valueOf(serverRequest.getServletRequest().getParameter("userId"));
            if (userId == null) {
                return false;
            }
            attributes.put("userId", userId);
        }
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception ex) {
        System.out.println("handshake success");
        super.afterHandshake(request, response, wsHandler, ex);
    }
}
