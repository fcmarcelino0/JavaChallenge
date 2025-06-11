package com.calculator.calculator.kafka;

import com.calculator.calculator.service.CalculatorService;
import com.challenge.javaCalculator.model.Calculator;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CalculatorKafka {

    private final CalculatorService calculatorService;

    public CalculatorKafka(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @KafkaListener(topics = "calculations-requests", groupId = "calculator-group")
    public Calculator listen(Calculator request) {
        return calculatorService.calculate(request);
    }
}
