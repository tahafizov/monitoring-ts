package ru.otus.trackprocess.websocket.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.otus.trackprocess.websocket.service.WebsocketSessionService;

@Component
@RequiredArgsConstructor
@Slf4j
public class SocketTextHandler extends TextWebSocketHandler {
    private final WebsocketSessionService websocketSessionService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        websocketSessionService.addSession(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        websocketSessionService.removeSession(session);
        super.afterConnectionClosed(session, status);
    }
}
