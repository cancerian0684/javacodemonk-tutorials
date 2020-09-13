package com.carvia.upload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.File;

@RestController
public class ReactiveUploadClient {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @GetMapping(value = "/client")
    public void upload() {
        final WebClient webClient = webClientBuilder.build();
        webClient.post()
                .uri("http://localhost:8080/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(fromFile(new File("/home/munish/Desktop/upload-tutorial/HELP.md"))))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public MultiValueMap<String, HttpEntity<?>> fromFile(File file) {
        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", new FileSystemResource(file));
        return builder.build();
    }
}
