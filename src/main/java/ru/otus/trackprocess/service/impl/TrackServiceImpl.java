package ru.otus.trackprocess.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.trackprocess.model.Point;
import ru.otus.trackprocess.repository.DatabaseRepository;
import ru.otus.trackprocess.service.TrackService;
import ru.otus.trackprocess.websocket.service.WebsocketSessionService;

import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class TrackServiceImpl implements TrackService {
    private final DatabaseRepository repository;
    private final WebsocketSessionService websocketSessionService;

    @Override
    public void pointProcess(Point point) throws SQLException {
        repository.insertPoint(point);
        websocketSessionService.sendMessage(point);
    }
}
