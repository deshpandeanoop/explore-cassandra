package com.explore.explorecassandra.kafka.producer;

import com.explore.explorecassandra.avro.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CourseKafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseKafkaProducer.class);
    private final KafkaTemplate<String, Course> kafkaTemplate;

    public CourseKafkaProducer(KafkaTemplate<String, Course> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(com.explore.explorecassandra.beans.model.Course course){
        Course data = constructKafkaBean(course);
        LOGGER.info("Publishing course data {}", data);
        kafkaTemplate.send("courses", data);
    }

    private Course constructKafkaBean(com.explore.explorecassandra.beans.model.Course course){
        LOGGER.info("Constructing KafkaCourse bean");

        return Course.newBuilder()
                .setId(course.getId())
                .setName(course.getName())
                .setAuthor(course.getAuthor())
                .build();
    }
}