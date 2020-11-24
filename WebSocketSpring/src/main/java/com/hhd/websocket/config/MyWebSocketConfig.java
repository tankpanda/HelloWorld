package com.hhd.websocket.config;

import com.hhd.websocket.handler.MyWebSocketHandler;
import com.hhd.websocket.interceptor.WebSocketInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * TODO
 *
 * @author hengda.hu
 * @date 2020/11/11 15:09
 */
@Configuration
@EnableWebSocket
public class MyWebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(webSocketHandler(), "ws").addInterceptors(new WebSocketInterceptor());
        webSocketHandlerRegistry.addHandler(webSocketHandler(), "sockjs").addInterceptors(new WebSocketInterceptor()).withSockJS();
    }

    @Bean
    public WebSocketHandler webSocketHandler() {
        return new MyWebSocketHandler();
    }
}
