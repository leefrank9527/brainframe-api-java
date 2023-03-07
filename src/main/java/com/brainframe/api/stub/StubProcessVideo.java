package com.brainframe.api.stub;

import com.brainframe.api.dto.ProcessVideoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

public class StubProcessVideo extends BaseStub {
    private static final String SERVICE_PATH = "/api/process_video";
    private final String aiIntervalType;
    private final Float aiIntervalVal;
    private final Float timeout;

    public StubProcessVideo(String baseUrl, String bfip, String aiIntervalType, Float aiIntervalVal, Float timeout) {
        super(baseUrl, bfip);
        this.timeout = timeout;
        this.aiIntervalType = aiIntervalType;
        this.aiIntervalVal = aiIntervalVal;
    }

    public boolean startProcessVideo(long streamId, String storageUri, ZoneStatusCallback callback) {
        ProcessVideoRequest pvr = new ProcessVideoRequest();
        pvr.setStream_id(streamId);
        pvr.setStorage_uri(storageUri);
        pvr.setAi_interval_type(this.aiIntervalType);
        pvr.setAi_interval_val(this.aiIntervalVal);
        pvr.setTimeout(this.timeout);

        try {
            ObjectMapper mapper = new ObjectMapper();
            //Converting the Object to JSONString
            String jsonString = mapper.writeValueAsString(pvr);

            URI uri = new URI(String.format("%s%s", baseUrl, SERVICE_PATH));
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestProperty("Content-Type", MIMETYPE_JSON);
            conn.setRequestProperty("BFIP", this.bfIP);
            conn.setRequestProperty("Content-Length", Integer.toString(jsonString.length()));

            BufferedOutputStream outputStream = new BufferedOutputStream(conn.getOutputStream());
            outputStream.write(jsonString.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();

            InputStream inputStream = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (reader.ready()) {
                String line = reader.readLine();
                log.debug(line);
                callback.receiveZoneStatus(null);
            }
//            byte[] buf = new byte[1024];
//            while (true) {
//                int len = inputStream.read(buf);
//                if (len < 0) {
//                    break;
//                }
//                if (len == 0) {
//                    continue;
//                }
//                log.debug(new String(buf, 0, len));
//            }

            return true;
        } catch (URISyntaxException e) {
            log.error("Failed to combine an URI", e);
            return false;
        } catch (IOException e) {
            log.error("Failed to request {}", SERVICE_PATH, e);
            return false;
        }
    }
}
