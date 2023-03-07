package com.brainframe.api.stub;

import com.brainframe.api.dto.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            ObjectMapper mapper = new ObjectMapper();
            //Converting the Object to JSONString
            String jsonString = mapper.writeValueAsString(pvr);
            StringEntity requestEntity = new StringEntity(jsonString, "UTF-8");

            URI uri = new URI(String.format("%s%s", this.baseUrl, SERVICE_PATH));
            final HttpPost request = new HttpPost(uri);
            request.addHeader(HttpHeaders.CONTENT_TYPE, MIMETYPE_JSON);
            request.addHeader("BFIP", this.bfIP);
            request.setEntity(requestEntity);
            CloseableHttpResponse response = httpClient.execute(request);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() != 200) {
                log.error("Failed to request {} {}", uri, status);
                return false;
            }

            HttpEntity entity = response.getEntity();
            if (entity == null || !entity.isChunked()) {
                log.error("The response can not be reflected to object. {}", SERVICE_PATH);
                return false;
            }

            InputStream inputStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String line = reader.readLine();
                log.debug(line);

                ObjectMapper objectMapper = new ObjectMapper();
                Map<String,Map<String,Object>> zoneStatusesMap=objectMapper.readValue(line, new TypeReference<Map<String,Map<String,Object>>>() {
                });

                if (zoneStatusesMap==null || zoneStatusesMap.size()!=1){
                    log.error("Failed to parse line: {}" ,line);
                    return false;
                }

                Iterator<Map.Entry< String,Map<String,Object>>> iterator = zoneStatusesMap.entrySet().iterator();
                Map.Entry<String,Map<String,Object>> actualValue = iterator.next();
                actualValue.getKey();

                long sessionId=Long.getLong(zoneStatusesMap.keySet().)

                final ZoneStatuses zoneStatuses=new ZoneStatuses();
                zoneStatuses.setStream_id(streamId);



                zoneStatusesMap.forEach((sessionId,zoneStatusListMap)->{

                    Zone zone=new Zone();
                    Map<String,Object> zoneMap=zoneStatusMap
                    ZoneStatus zoneStatus=new ZoneStatus();
                    zoneStatus.
                });



                callback.receiveZoneStatus(null);
                if (line == null) {
                    break;
                }
            }

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
