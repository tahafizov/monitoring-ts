package ru.otus.trackprocess.repository;

import ru.otus.trackprocess.model.Point;

import java.sql.SQLException;

public interface DatabaseRepository {
    void insertPoint(Point point) throws SQLException;
}
