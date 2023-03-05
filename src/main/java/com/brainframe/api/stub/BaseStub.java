package com.brainframe.api.stub;

import com.brainframe.api.dto.StreamConfiguration;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.apache.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

public class BaseStub {
    private String baseUrl;
    private String bfip;

    private int connTimeout = 30;
    private int readTimeout = 30;
    private int writeTimeout = 30;

    private int responseTimeout = 300;

    private HttpClient httpClient;

    public BaseStub(String baseUrl, String bfip, int connTimeout, int readTimeout, int writeTimeout, int responseTimeout) {
        this.baseUrl = baseUrl;
        this.bfip = bfip;
        this.connTimeout = connTimeout;
        this.readTimeout = readTimeout;
        this.writeTimeout = writeTimeout;
        this.responseTimeout = responseTimeout;
        this.httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connTimeout * 1000)
                .responseTimeout(Duration.ofSeconds(responseTimeout))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.SECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.SECONDS)));
    }

    public BaseStub(String baseUrl, String bfip) {
        this(baseUrl, bfip, 30000, 30000, 30000, 300000);
    }

    public Object post(String servicePath, Object reqParam, Class<?> reqElementClass, Class<?> rspElementClass) {
//        WebClient client = WebClient.create(this.baseUrl);
        WebClient client = WebClient.builder().baseUrl(this.baseUrl).clientConnector(new ReactorClientHttpConnector(this.httpClient)).build();
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.post();
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(servicePath);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.body(Mono.just(reqParam), reqElementClass);
        WebClient.ResponseSpec responseSpec = headersSpec.header(
                        HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("BFIP", this.bfip)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();
        Mono<?> response = headersSpec.retrieve().bodyToMono(rspElementClass);

        return response.block();
    }

    public Object get(String servicePath, Class<?> rspElementClass) {
        WebClient client = WebClient.builder().baseUrl(this.baseUrl).clientConnector(new ReactorClientHttpConnector(this.httpClient)).build();
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.get();
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(servicePath);
        WebClient.RequestHeadersSpec<?> headersSpec = bodySpec.body(Mono.just(reqParam), reqElementClass);
        WebClient.ResponseSpec responseSpec = headersSpec.header(
                        HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header("BFIP", this.bfip)
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .acceptCharset(StandardCharsets.UTF_8)
                .ifNoneMatch("*")
                .ifModifiedSince(ZonedDateTime.now())
                .retrieve();
        Mono<?> response = headersSpec.retrieve().bodyToMono(rspElementClass);

        return response.block();
    }

}
