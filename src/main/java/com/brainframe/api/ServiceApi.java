package com.brainframe.api;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServiceApi {
    private final String baseUrl;

    public ServiceApi(String baseUrl) {
        this.baseUrl = baseUrl;
    }


    public String getServerVersion() {
//        return _restGet(ConstantPaths.SERVER_INFORMATION_VERSION);
        Mono<String> mono = WebClient.create().get().uri(this.baseUrl + ConstantPaths.SERVER_INFORMATION_VERSION).retrieve().bodyToMono(String.class);
        String rspJson = mono.block();
        System.out.println(rspJson);
        return rspJson;
    }

    public String getLicenseInfo() {
        Mono<String> mono = WebClient.create().get().uri(this.baseUrl + ConstantPaths.LICENSE_CONTROL).retrieve().bodyToMono(String.class);
        String rspJson = mono.block();
        System.out.println(rspJson);
        return rspJson;
    }

    public String putLicenseInfo(String reqJson) {
        Mono<String> mono = WebClient.create().put().uri(this.baseUrl + ConstantPaths.LICENSE_CONTROL).bodyValue(reqJson).retrieve().bodyToMono(String.class);
        String rspJson = mono.block();
        System.out.println(rspJson);
        return rspJson;
    }

    private String _restGet(String path) {
        return _restGet(path, null);
    }

    private String _restGet(String path, String reqJson) {
        return _rest(path, "GET", reqJson);
    }

    private String _restPut(String path) {
        return _restPut(path, null);
    }

    private String _restPut(String path, String reqJson) {
        return _rest(path, "PUT", reqJson);
    }

    private String _rest(String path, String reqMethod, String reqJson) {
        try {
            URL url = new URL(this.baseUrl + path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod(reqMethod);
            conn.setRequestProperty("Content-Type", "application/json");

            conn.setDoInput(true);
            conn.setDoOutput(true);

            if (reqJson != null) {
                OutputStream outputStream = conn.getOutputStream();
                outputStream.write(reqJson.getBytes());
                outputStream.close();
            }

            InputStream inputStream = conn.getInputStream();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            while (true) {
                int n = inputStream.read(buf);
                if (n < 0) {
                    break;
                } else if (n == 0) {
                    continue;
                }

                byteArrayOutputStream.write(buf, 0, n);
            }

            String rspJson = new String(byteArrayOutputStream.toByteArray());

            if (reqJson != null) {
                System.out.println(reqJson);
            } else {
                System.out.println("{}");
            }
            System.out.println();
            System.out.println(rspJson);
            System.out.println("-----------------------------------------------------------------------");

            return rspJson;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
