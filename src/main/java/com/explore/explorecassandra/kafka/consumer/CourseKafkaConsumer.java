package com.explore.explorecassandra.kafka.consumer;

import com.explore.explorecassandra.avro.Course;
import com.explore.explorecassandra.repository.CoursesRepository;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class CourseKafkaConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseKafkaConsumer.class);
    private final CoursesRepository coursesRepository;

    public CourseKafkaConsumer(CoursesRepository coursesRepository) {

        this.coursesRepository = coursesRepository;
        Runnable runnable = () -> {
            LOGGER.info("Initializing Consumer");
            final Map<String, Object> configMap = new HashMap<>();
            configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
            configMap.put(ConsumerConfig.GROUP_ID_CONFIG, "test");
            configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
            configMap.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
            configMap.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

            KafkaConsumer<String, Course> consumer = new KafkaConsumer<>(configMap);
            consumer.subscribe(Collections.singletonList("courses"));
            while (true) {
                ConsumerRecords<String, Course> records = consumer.poll(Duration.ofMillis(100));
                for (ConsumerRecord<String, Course> record : records) {
                    LOGGER.info("Processing request {}", record);
                    String key = record.key();
                    Course value = record.value();
                    coursesRepository.saveAll(Collections.singletonList(constructCourseBean(value)));
                    LOGGER.info("Persisted course into database successfully");
                }
            }
        };

        new Thread(runnable).start();
        LOGGER.info("Started Consumer Thread");
    }

    private com.explore.explorecassandra.beans.model.Course constructCourseBean(Course course){
        LOGGER.info("Constructing course database bean");
        com.explore.explorecassandra.beans.model.Course bean = new com.explore.explorecassandra.beans.model.Course();
        bean.setId(course.getId().toString());
        bean.setAuthor(course.getAuthor().toString());
        bean.setName(course.getName().toString());

        return bean;
    }
}