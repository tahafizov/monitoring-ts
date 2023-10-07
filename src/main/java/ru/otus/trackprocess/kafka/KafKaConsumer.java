package ru.otus.trackprocess.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.otus.trackprocess.model.Point;
import ru.otus.trackprocess.service.TrackService;

import java.sql.SQLException;


@Component
@Slf4j
@RequiredArgsConstructor
public class KafKaConsumer {
    private final TrackService trackService;

    @KafkaListener(topics = "MyTopic")
    public void consume(Point point) throws SQLException {
        log.info("receive message: {}", point);
        trackService.pointProcess(point);
    }
}
