package ru.otus.kafkaproducer.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.otus.kafkaproducer.service.KafkaSendService;

@Component
@RequiredArgsConstructor
public class ScheduledTasks {
    private final KafkaSendService kafkaSendService;

    @Scheduled(fixedRateString = "1000")
    public void scheduledSendMessage() {
        kafkaSendService.send();
    }
}
