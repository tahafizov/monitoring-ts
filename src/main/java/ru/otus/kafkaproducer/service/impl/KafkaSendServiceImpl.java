package ru.otus.kafkaproducer.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.otus.kafkaproducer.model.Point;
import ru.otus.kafkaproducer.service.KafkaSendService;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaSendServiceImpl implements KafkaSendService {
    private final KafkaTemplate<String, Point> kafkaTemplate;
    @Value(value = "${kafka.producer.topic.name}")
    private String topicName;

    private static final String TRACK_NUMBER = "9876543212";

    @Override
    public void send() {
        Point testPoint = getPoint();
        log.info("sending payload='{}'", testPoint);
        var future = kafkaTemplate.send(topicName, testPoint);
        future.whenCompleteAsync((result, throwable) -> {
            if (throwable != null) {
                log.info("Unable to send message='{}' due to : {}", testPoint, throwable.getMessage());
            } else {
                log.info("Sent message='{}' successfully with offset={}", testPoint, result.getRecordMetadata().offset());
            }
        });
    }

    private Point getPoint() {
        return new Point(8452L, OffsetDateTime.now(), TRACK_NUMBER,43.603760D, 39.716172);
    }
}
