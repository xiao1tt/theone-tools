package com.theone.common.base.http;

import com.theone.common.base.json.JSON;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author chenxiaotong
 */
public class HttpClient {

    private final RestTemplate restTemplate = new RestTemplate();

    public <T> T get(String url, Map<String, String> param, Class<T> responseType) {
        String response = restTemplate.getForObject(url, String.class, param);
        return JSON.fromJson(response, responseType);
    }

    public <T> T post(String url, Map<String, String> param, Object body, Class<T> responseType) {
        String response = restTemplate.postForObject(url, body, String.class, param);
        return JSON.fromJson(response, responseType);
    }
}
