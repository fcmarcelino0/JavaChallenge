package com.calculator.rest.controller;

import com.calculator.rest.service.CalculatorService;
import com.challenge.javaCalculator.model.Calculator;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;
import org.springframework.kafka.requestreply.RequestReplyFuture;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/calculate")
public class CalculatorController {

    private final ReplyingKafkaTemplate<String, Calculator, Calculator> replyingKafkaTemplate;
    //private final CalculatorService clientService;

    //public CalculatorController(CalculatorService clientService) {
    //    this.clientService = clientService;
    //}

    public CalculatorController(ReplyingKafkaTemplate<String, Calculator, Calculator> replyingKafkaTemplate) {
        this.replyingKafkaTemplate = replyingKafkaTemplate;
    }

    private ResponseEntity<Calculator> sendRequest(Calculator request, String topic) throws Exception {
        ProducerRecord<String, Calculator> record = new ProducerRecord<>(topic, request.getId(), request);
        RequestReplyFuture<String, Calculator, Calculator> replyFuture = replyingKafkaTemplate.sendAndReceive(record);

        // max 5 seconds for reply
        ConsumerRecord<String, Calculator> consumerRecord = replyFuture.get(5, TimeUnit.SECONDS);
        return ResponseEntity.ok(consumerRecord.value());
    }

    @GetMapping("/sum")
    public ResponseEntity<Calculator> sum(@RequestParam("a") double a, @RequestParam("b") double b) {
        Calculator result = new Calculator();
        result.setA(a);
        result.setB(b);
        result.setResult(a + b);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/subtraction")
    public ResponseEntity<Calculator> subtraction(@RequestParam("a") double A, @RequestParam("b") double B) {
        Calculator result = new Calculator();
        result.setA(A);
        result.setB(B);
        result.setResult(A - B);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/multiplication")
    public ResponseEntity<Calculator> multiplication(@RequestParam("a") double A, @RequestParam("b") double B) {
        Calculator result = new Calculator();
        result.setA(A);
        result.setB(B);
        result.setResult(A * B);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/division")
    public ResponseEntity<Calculator> division(@RequestParam("a") double A, @RequestParam("b") double B) {
        Calculator result = new Calculator();
        result.setA(A);
        result.setB(B);
        if (B == 0) {
            return ResponseEntity.badRequest().build();
        }
        result.setResult(A / B);
        return ResponseEntity.ok(result);
    }

    /*kafka methods to test
    @GetMapping("/{operation}")
    public ResponseEntity<Calculator> sum(
            @PathVariable String operation,
            @RequestParam double a,
            @RequestParam double b
    ) {
        Calculator request = new Calculator();
        request.setA(a);
        request.setB(b);
        request.setOperation(operation);

        try {
            Calculator response = clientService.sendCalculationRequest(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
     */

}
