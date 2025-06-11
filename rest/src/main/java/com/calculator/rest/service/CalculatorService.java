package com.calculator.rest.service;

import com.challenge.javaCalculator.model.Calculator;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Service
public class CalculatorService {

    private final ReplyingKafkaTemplate<String, Calculator, Calculator> kafkaTemplate;

    public CalculatorService(ReplyingKafkaTemplate<String, Calculator, Calculator> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public Calculator sendCalculationRequest(Calculator request) throws Exception {
        ProducerRecord<String, Calculator> record = new ProducerRecord<>(
                "calculations-requests", request.getId(), request
        );
        record.headers().add(new RecordHeader(
                "kafka_replyTopic",
                "calculations-replies".getBytes(StandardCharsets.UTF_8)
        ));

        RequestReplyFuture<String, Calculator, Calculator> future = kafkaTemplate.sendAndReceive(record);
        return future.get(10, TimeUnit.SECONDS).value();
    }
}
