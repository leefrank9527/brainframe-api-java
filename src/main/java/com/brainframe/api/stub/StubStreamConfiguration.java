package com.brainframe.api.stub;

import com.brainframe.api.dto.StreamConfiguration;

import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

import org.apache.http.HttpHeaders;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class StubStreamConfiguration extends BaseStub {
    public StubStreamConfiguration(String baseUrl, String bfip) {
        super(baseUrl, bfip);
    }

    public StreamConfiguration setStreamConfiguration(StreamConfiguration streamConf) {
//        WebClient client = WebClient.create("http://localhost:8080");
//        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.post();
//        WebClient.RequestBodySpec bodySpec = uriSpec.uri("/api/streams");
//        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.body(Mono.just(streamConf), StreamConfiguration.class);
//        WebClient.ResponseSpec responseSpec = headersSpec.header(
//                        HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
//                .acceptCharset(StandardCharsets.UTF_8)
//                .ifNoneMatch("*")
//                .ifModifiedSince(ZonedDateTime.now())
//                .retrieve();
//        Mono<StreamConfiguration> response = headersSpec.retrieve().bodyToMono(StreamConfiguration.class);
//
//        return response.block();
        return (StreamConfiguration) this.post("/api/streams", streamConf, StreamConfiguration.class, StreamConfiguration.class);
    }
}
