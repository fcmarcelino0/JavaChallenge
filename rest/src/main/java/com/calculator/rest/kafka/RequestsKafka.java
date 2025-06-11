package com.calculator.rest.kafka;

import com.challenge.javaCalculator.model.Calculator;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RequestsKafka {

    private final ReplyingKafkaTemplate<String, Calculator, Calculator> replyingKafkaTemplate;

    public RequestsKafka(ReplyingKafkaTemplate<String, Calculator, Calculator> replyingKafkaTemplate) {
        this.replyingKafkaTemplate = replyingKafkaTemplate;
    }

    public Calculator sendCalculationRequest(Calculator request) throws Exception {
        ProducerRecord<String, Calculator> record = new ProducerRecord<>("calculations-requests", request.getId(), request);
        RequestReplyFuture<String, Calculator, Calculator> replyFuture = replyingKafkaTemplate.sendAndReceive(record);
        // max 5 seconds
        ConsumerRecord<String, Calculator> consumerRecord = replyFuture.get(5, TimeUnit.SECONDS);
        // Return the reply value (calculator result)
        return consumerRecord.value();
    }
}
