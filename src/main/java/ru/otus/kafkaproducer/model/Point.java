package ru.otus.kafkaproducer.model;

import java.time.OffsetDateTime;

public record Point(Long id, OffsetDateTime time, String trackNumber, Double latitude, Double longitude) {
}
