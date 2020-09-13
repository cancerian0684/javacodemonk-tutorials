package com.carvia.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class SlackClient {

    private final WebClient webClient;
    private final RestTemplate restTemplate;

    @Autowired
    public SlackClient(WebClient.Builder webClientBuilder, RestTemplateBuilder restTemplateBuilder) {
        this.webClient = webClientBuilder.build();
        this.restTemplate = restTemplateBuilder.build();
    }

    public String sendMessage(String text) {
        return webClient.post()
                .uri("https://hooks.slack.com/services/aaa/bbb/ccc")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new SlackPayload(text))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String sendMessageOld(String text) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        JsonObject obj = new JsonObject();
//        obj.addProperty("text", text);
        SlackPayload payload = new SlackPayload(text);
        HttpEntity<SlackPayload> request = new HttpEntity<>(payload, headers);
        final ResponseEntity<String> entity = restTemplate.exchange("https://hooks.slack.com/services/aaa/bbb/ccc", HttpMethod.POST, request, String.class);
        if(entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        }
        return null;
    }
}
