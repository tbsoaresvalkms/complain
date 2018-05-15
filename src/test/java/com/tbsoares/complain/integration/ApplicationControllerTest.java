package com.tbsoares.complain.integration;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static java.util.Collections.singletonList;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public abstract class ApplicationControllerTest {
    @Autowired
    protected TestRestTemplate restTemplate;

    <T> ResponseEntity<T> get(String uri, Class<T> clazz) {
        return execute(uri, HttpMethod.GET, null, clazz);
    }

    <T> ResponseEntity<T> get(String uri, ParameterizedTypeReference<T> responseType) {
        return execute(uri, HttpMethod.GET, responseType);
    }

    <T, K> ResponseEntity<T> post(String uri, K body, Class<T> clazz) {
        return execute(uri, HttpMethod.POST, body, clazz);
    }

    <T, K> ResponseEntity<T> put(String uri, K body, Class<T> clazz) {
        return execute(uri, HttpMethod.PUT, body, clazz);
    }

    <T> ResponseEntity<T> delete(String uri, Class<T> clazz) {
        return execute(uri, HttpMethod.DELETE, null, clazz);
    }

    private <T, K> ResponseEntity<T> execute(String uri, HttpMethod method, K body, Class<T> clazz) {
        HttpEntity requestEntity = Optional.ofNullable(body)
                .map(this::builderBody)
                .orElse(new HttpEntity<>(jsonHeaders()));

        return restTemplate.exchange(uri, method, requestEntity, clazz);
    }

    private <T> ResponseEntity<T> execute(String uri, HttpMethod method, ParameterizedTypeReference<T> responseType) {
        HttpEntity requestEntity = new HttpEntity<>(jsonHeaders());

        return restTemplate.exchange(uri, method, requestEntity, responseType);
    }

    private <K> HttpEntity<K> builderBody(K body) {
        return new HttpEntity<>(body, jsonHeaders());
    }

    private HttpHeaders jsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.put("Content-Type", singletonList(MediaType.APPLICATION_JSON_VALUE));
        headers.put("Accept", singletonList(MediaType.APPLICATION_JSON_VALUE));

        return headers;
    }
}
