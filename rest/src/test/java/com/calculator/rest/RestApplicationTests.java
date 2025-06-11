package com.calculator.rest;

import com.challenge.javaCalculator.model.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String url(String path) {
        return "http://localhost:" + port + "/api" + path;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void testSum() {
        ResponseEntity<Calculator> response = restTemplate.getForEntity(url("/sum?a=2&b=3"), Calculator.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody().getResult()).isEqualTo(5.0);
    }

    @Test
    void testSubtraction() {
        ResponseEntity<Calculator> response = restTemplate.getForEntity(url("/subtraction?a=10&b=4"), Calculator.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody().getResult()).isEqualTo(6.0);
    }

    @Test
    void testMultiplication() {
        ResponseEntity<Calculator> response = restTemplate.getForEntity(url("/multiplication?a=3&b=5"), Calculator.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody().getResult()).isEqualTo(15.0);
    }

    @Test
    void testDivision() {
        ResponseEntity<Calculator> response = restTemplate.getForEntity(url("/division?a=10&b=2"), Calculator.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody().getResult()).isEqualTo(5.0);
    }

    @Test
    void testDivisionByZero() {
        ResponseEntity<String> response = restTemplate.getForEntity(url("/division?a=10&b=0"), String.class);
        assertThat(response.getStatusCode().is4xxClientError()).isTrue();
    }

}
