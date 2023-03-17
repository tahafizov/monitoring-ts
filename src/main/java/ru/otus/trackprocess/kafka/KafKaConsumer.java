package ru.otus.trackprocess.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.otus.trackprocess.model.Point;


@Component
@Slf4j
public class KafKaConsumer {

    @KafkaListener(topics = "MyTopic")
    public void consume(Point point) {
        log.info("receive message: {}", point);
    }
}
