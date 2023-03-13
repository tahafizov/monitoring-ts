package ru.otus.kafkaproducer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Point {
    private Long id;
    private OffsetDateTime time;
    private String trackNumber;
    private Double latitude;
    private Double longitude;
}
