CREATE DATABASE IF NOT EXISTS tracking;

CREATE TABLE tracking.points
(
    id UInt64,
    time DateTime,
    trackNumber String,
    latitude Double,
    longitude Double
)
    ENGINE = MergeTree()
PRIMARY KEY (time)
