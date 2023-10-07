package ru.otus.trackprocess.repository;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.otus.trackprocess.model.Point;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

@Repository
@Slf4j
@Getter
public class ClickHouseRepository implements DatabaseRepository {

    private final Connection connection;

    public ClickHouseRepository(@Value("${clickhouse.url}") String dbUrl) throws SQLException {
        connection = DriverManager.getConnection(dbUrl);
        log.info("Connected to: {}", connection.getMetaData().getURL());
    }

    @Override
    public void insertPoint(Point point) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO points (id, time, trackNumber, latitude, longitude) VALUES(?, ?, ?, ?, ?)")) {
            ps.setLong(1, point.id());
            ps.setTimestamp(2, Timestamp.valueOf(point.time().toLocalDateTime()));
            ps.setString(3, point.trackNumber());
            ps.setDouble(4, point.latitude());
            ps.setDouble(5, point.longitude());
            ps.executeUpdate();
        }
    }

}
