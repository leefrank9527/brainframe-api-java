package com.brainframe.api.stub;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.netty.http.client.HttpClient;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseStub {
    protected static final Logger log = LoggerFactory.getLogger(BaseStub.class);
    protected static final String MIMETYPE_JSON = "application/json";
    protected static final String MIMETYPE_MP4 = "video/mp4";
    protected String baseUrl;
    protected String bfIP;

    private int connTimeout = 30;
    private int readTimeout = 30;
    private int writeTimeout = 30;

    private int responseTimeout = 300;

    private HttpClient httpClient;

    public BaseStub(String baseUrl, String bfIP, int connTimeout, int readTimeout, int writeTimeout, int responseTimeout) {
        this.baseUrl = baseUrl;
        this.bfIP = bfIP;
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

    public BaseStub(String baseUrl, String bfIP) {
        this(baseUrl, bfIP, 30000, 30000, 30000, 300000);
    }

    public String get(String servicePath) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URI uri = new URI(String.format("%s%s", this.baseUrl, servicePath));
            final HttpGet request = new HttpGet(uri);
            request.addHeader(HttpHeaders.CONTENT_TYPE, MIMETYPE_JSON);
            request.addHeader("BFIP", this.bfIP);
            CloseableHttpResponse response = httpClient.execute(request);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != 200) {
                log.error("Failed to request {} {}", uri, status);
                return null;
            }

            HttpEntity entity = response.getEntity();
            if (entity == null || entity.isChunked()) {
                log.error("The response can not be reflected to object. {}", servicePath);
                return null;
            }
            return new String(IOUtils.readFully(entity.getContent(), (int) entity.getContentLength()));
        } catch (URISyntaxException e) {
            log.error("Failed to combine an URI", e);
            return null;
        } catch (IOException e) {
            log.error("Failed to request {}", servicePath, e);
            return null;
        }
    }

    public Object get(String servicePath, Class<?> rspElementClass) {
        try {
            String content = get(servicePath);
            if (StringUtils.isEmpty(content)) {
                log.error("The response can not be reflected to object. {}", servicePath);
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content, rspElementClass);
        } catch (IOException e) {
            log.error("Failed to request {}", servicePath, e);
            return null;
        }
    }

    public String post(String servicePath, Object reqParam) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ObjectMapper mapper = new ObjectMapper();
            //Converting the Object to JSONString
            String jsonString = mapper.writeValueAsString(reqParam);
            StringEntity requestEntity = new StringEntity(jsonString, "UTF-8");

            URI uri = new URI(String.format("%s%s", this.baseUrl, servicePath));
            final HttpPost request = new HttpPost(uri);
            request.addHeader(HttpHeaders.CONTENT_TYPE, MIMETYPE_JSON);
            request.addHeader("BFIP", this.bfIP);
            request.setEntity(requestEntity);
            CloseableHttpResponse response = httpClient.execute(request);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != 200) {
                log.error("Failed to request {} {}", uri, status);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null || entity.isChunked()) {
                log.error("The response can not be reflected to object. {}", servicePath);
                return null;
            }
            return new String(IOUtils.readFully(entity.getContent(), (int) entity.getContentLength()));
        } catch (URISyntaxException e) {
            log.error("Failed to combine an URI", e);
            return null;
        } catch (IOException e) {
            log.error("Failed to request {}", servicePath, e);
            return null;
        }
    }

    public Object post(String servicePath, Object reqParam, Class<?> rspElementClass) {
        try {
            String content = post(servicePath, reqParam);
            if (StringUtils.isEmpty(content)) {
                log.error("The response can not be reflected to object. {}", servicePath);
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content, rspElementClass);
        } catch (IOException e) {
            log.error("Failed to request {}", servicePath, e);
            return null;
        }
    }

    public String put(String servicePath, Object reqParam) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ObjectMapper mapper = new ObjectMapper();
            //Converting the Object to JSONString
            String jsonString = mapper.writeValueAsString(reqParam);
            StringEntity requestEntity = new StringEntity(jsonString, "UTF-8");

            URI uri = new URI(String.format("%s%s", this.baseUrl, servicePath));
            final HttpPut request = new HttpPut(uri);
            request.addHeader(HttpHeaders.CONTENT_TYPE, MIMETYPE_JSON);
            request.addHeader("BFIP", this.bfIP);
            request.setEntity(requestEntity);
            CloseableHttpResponse response = httpClient.execute(request);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != 200) {
                log.error("Failed to request {} {}", uri, status);
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null || entity.isChunked()) {
                log.error("The response can not be reflected to object. {}", servicePath);
                return null;
            }
            return new String(IOUtils.readFully(entity.getContent(), (int) entity.getContentLength()));
        } catch (URISyntaxException e) {
            log.error("Failed to combine an URI", e);
            return null;
        } catch (IOException e) {
            log.error("Failed to request {}", servicePath, e);
            return null;
        }
    }

    public Object put(String servicePath, Object reqParam, Class<?> rspElementClass) {
        try {
            String content = put(servicePath, reqParam);
            if (StringUtils.isEmpty(content)) {
                log.error("The response can not be reflected to object. {}", servicePath);
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content, rspElementClass);
        } catch (IOException e) {
            log.error("Failed to request {}", servicePath, e);
            return null;
        }
    }

    public String delete(String servicePath) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            URI uri = new URI(String.format("%s%s", this.baseUrl, servicePath));
            final HttpDelete request = new HttpDelete(uri);
            request.addHeader(HttpHeaders.CONTENT_TYPE, MIMETYPE_JSON);
            request.addHeader("BFIP", this.bfIP);
            CloseableHttpResponse response = httpClient.execute(request);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != 200) {
                log.error("Failed to request {} {}", uri, status);
                return null;
            }

            HttpEntity entity = response.getEntity();
            if (entity == null || entity.isChunked()) {
                log.error("The response can not be reflected to object. {}", servicePath);
                return null;
            }
            return new String(IOUtils.readFully(entity.getContent(), (int) entity.getContentLength()));
        } catch (URISyntaxException e) {
            log.error("Failed to combine an URI", e);
            return null;
        } catch (IOException e) {
            log.error("Failed to request {}", servicePath, e);
            return null;
        }
    }

    public Object delete(String servicePath, Class<?> rspElementClass) {
        try {
            String content = delete(servicePath);
            if (StringUtils.isEmpty(content)) {
                log.error("The response can not be reflected to object. {}", servicePath);
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content, rspElementClass);
        } catch (IOException e) {
            log.error("Failed to request {}", servicePath, e);
            return null;
        }
    }
}
