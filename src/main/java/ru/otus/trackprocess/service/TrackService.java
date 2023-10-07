package ru.otus.trackprocess.service;

import ru.otus.trackprocess.model.Point;

import java.sql.SQLException;

public interface TrackService {
    void pointProcess(Point point) throws SQLException;
}
