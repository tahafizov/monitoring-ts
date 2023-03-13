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

    private static final Point testPoint = Point.builder()
            .id(8452L)
            .time(OffsetDateTime.now())
            .trackNumber(TRACK_NUMBER)
            .latitude(43.603760D)
            .longitude(39.716172)
            .build();

    @Override
    public void send() {
        log.info("sending payload='{}'", testPoint);
        kafkaTemplate.send(topicName, testPoint);
    }
}
