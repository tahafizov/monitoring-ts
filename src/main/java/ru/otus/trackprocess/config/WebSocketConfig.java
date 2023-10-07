package ru.otus.trackprocess.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.otus.trackprocess.websocket.handler.SocketTextHandler;
import ru.otus.trackprocess.websocket.service.WebsocketSessionService;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebsocketSessionService websocketSessionService;

    @Value("${websocket.endpoint}")
    private String endpoint;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketTextHandler(websocketSessionService), endpoint)
                .setAllowedOrigins("*");
    }

}
