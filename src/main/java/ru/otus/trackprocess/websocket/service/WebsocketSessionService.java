package ru.otus.trackprocess.websocket.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.otus.trackprocess.model.Point;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class WebsocketSessionService {
    private final ObjectMapper objectMapper;

    private final Map<String, WebSocketSession> activeSession = new HashMap<>();

    public void addSession(WebSocketSession session) {
        log.info("ADD new session: {}", session.getId());
        activeSession.put(session.getId(), session);
    }

    public void removeSession(WebSocketSession session) {
        log.info("Remove session: {}", session.getId());
        activeSession.remove(session.getId());
    }

    public void sendMessage(Point point) {
        activeSession.forEach((key, session) -> {
            try {
                log.info("Send point `{}` to {}", point.id(), session.getId());
                session.sendMessage(new TextMessage(objectMapper.writeValueAsString(point)));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
    }
}
