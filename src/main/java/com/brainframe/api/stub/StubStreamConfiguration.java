package com.brainframe.api.stub;

import com.brainframe.api.dto.StreamConfiguration;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;


public class StubStreamConfiguration extends BaseStub {
    public StubStreamConfiguration(String baseUrl, String bfip) {
        super(baseUrl, bfip);
    }

    public StreamConfiguration setStreamConfiguration(StreamConfiguration streamConf) {
        return (StreamConfiguration) this.post("/api/streams", streamConf, StreamConfiguration.class);
    }

    public StreamConfiguration getStreamConfiguration(long streamId) {
        return (StreamConfiguration) this.get(String.format("/api/streams/%s", streamId), StreamConfiguration.class);
    }

    public List<StreamConfiguration> getStreamConfigurations() {
        String servicePath = "/api/streams";
        try {
            String content = this.get(servicePath);
            if (StringUtils.isEmpty(content)) {
                log.error("Failed to request from: {}", servicePath);
                return null;
            }
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(content, new TypeReference<List<StreamConfiguration>>() {
            });
        } catch (IOException e) {
            log.error("Failed to read from http entity", e);
            return null;
        }
    }

    public void deleteStreamConfiguration(long streamId) {
        this.delete(String.format("/api/streams/%s", streamId), StreamConfiguration.class);
    }
}
